package org.amr.ecommerce.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.amr.ecommerce.customer.CustomerClient;
import org.amr.ecommerce.exception.BusinessException;
import org.amr.ecommerce.kafka.OrderConfirmation;
import org.amr.ecommerce.kafka.OrderProducer;
import org.amr.ecommerce.orderline.OrderLineRequest;
import org.amr.ecommerce.orderline.OrderLineService;
import org.amr.ecommerce.payment.PaymentClient;
import org.amr.ecommerce.payment.PaymentRequest;
import org.amr.ecommerce.product.ProductClient;
import org.amr.ecommerce.product.PurchaseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        // first check the customer --> OpenFeign
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException(
                        "Cannot create order:: Customer not found with id: " + request.customerId()
                ));
        // purchase the products => product-service (RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        // persist the order
        var order = this.orderRepository.save(
                mapper.toOrder(request)
        );
        // persist the order lines
        for (PurchaseRequest product : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            product.productId(),
                            product.quantity()
                    )
            );
        }
        // start the payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);
        // send order confirmation => notification-service (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::toOrderResponse)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order not found with id: " + orderId
                ));
    }
}
