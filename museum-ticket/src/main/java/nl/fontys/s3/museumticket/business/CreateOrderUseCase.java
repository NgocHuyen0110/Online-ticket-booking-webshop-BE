package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.order.CreateOrderRequest;
import nl.fontys.s3.museumticket.domain.order.CreateOrderResponse;

public interface CreateOrderUseCase {
    CreateOrderResponse createOrder(CreateOrderRequest request);
}
