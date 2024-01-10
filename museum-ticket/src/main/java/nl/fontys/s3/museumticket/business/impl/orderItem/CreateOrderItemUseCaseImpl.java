package nl.fontys.s3.museumticket.business.impl.orderItem;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateOrderItemUseCase;
import nl.fontys.s3.museumticket.domain.OrderItem.CreateOrderItemRequest;
import nl.fontys.s3.museumticket.domain.OrderItem.CreateOrderItemResponse;
import nl.fontys.s3.museumticket.domain.order.Order;
import nl.fontys.s3.museumticket.persistence.OrderItemRepository;
import nl.fontys.s3.museumticket.persistence.OrderRepository;
import nl.fontys.s3.museumticket.persistence.TicketRepository;
import nl.fontys.s3.museumticket.persistence.entity.OrderEntity;
import nl.fontys.s3.museumticket.persistence.entity.OrderItemEntity;
import nl.fontys.s3.museumticket.persistence.entity.TicketEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderItemUseCaseImpl implements CreateOrderItemUseCase {
    private final TicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public CreateOrderItemResponse createOrderItem(CreateOrderItemRequest request) {
       OrderItemEntity savedOrderItem =saveOrderItem(request);
       return CreateOrderItemResponse.builder()
               .orderItemId(savedOrderItem.getId())
               .build();
    }
    private OrderItemEntity saveOrderItem(CreateOrderItemRequest request){
        TicketEntity ticket = ticketRepository.findTicketById(request.getTicketId());
        OrderEntity order = orderRepository.findOrderById(request.getOrderId());
        OrderItemEntity newOrderItem = OrderItemEntity.builder()
                .ticket(ticket)
                .amount(request.getAmount())
                .price(ticket.getPrice())
                .order(order)
                .build();
        return orderItemRepository.save(newOrderItem);

    }
}
