package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.OrderItem.CreateOrderItemRequest;
import nl.fontys.s3.museumticket.domain.OrderItem.CreateOrderItemResponse;

public interface CreateOrderItemUseCase {
    CreateOrderItemResponse createOrderItem(CreateOrderItemRequest request);
}
