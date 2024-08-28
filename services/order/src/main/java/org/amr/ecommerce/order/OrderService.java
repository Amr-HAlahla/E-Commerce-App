package org.amr.ecommerce.order;

import lombok.RequiredArgsConstructor;
import org.amr.ecommerce.customer.CustomerClient;
import org.amr.ecommerce.exception.BusinessException;
import org.amr.ecommerce.orderline.OrderLineRequest;
import org.amr.ecommerce.orderline.OrderLineService;
import org.amr.ecommerce.product.ProductClient;
import org.amr.ecommerce.product.PurchaseRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {
        // first check the customer --> OpenFeign
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException(
                        "Cannot create order:: Customer not found with id: " + request.customerId()
                ));
        // purchase the products => product-service (RestTemplate)
        this.productClient.purchaseProducts(request.products());
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
        // todo start the payment process

        // todo send order confirmation => notification-service (kafka)
        return null;
    }
}
