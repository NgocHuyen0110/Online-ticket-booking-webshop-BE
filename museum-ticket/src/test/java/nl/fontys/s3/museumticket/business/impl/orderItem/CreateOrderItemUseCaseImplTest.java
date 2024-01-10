package nl.fontys.s3.museumticket.business.impl.orderItem;

import nl.fontys.s3.museumticket.domain.OrderItem.CreateOrderItemRequest;
import nl.fontys.s3.museumticket.domain.OrderItem.CreateOrderItemResponse;
import nl.fontys.s3.museumticket.persistence.OrderItemRepository;
import nl.fontys.s3.museumticket.persistence.OrderRepository;
import nl.fontys.s3.museumticket.persistence.TicketRepository;
import nl.fontys.s3.museumticket.persistence.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderItemUseCaseImplTest {
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private CreateOrderItemUseCaseImpl createOrderItemUseCase;
    @Test
    void create_order_item(){
        CustomerEntity savedCustomer = new CustomerEntity().builder()
                .id(1L)
                .fullName("Thai Huyen")
                .build();
        OrderEntity order = OrderEntity.builder()
                .id(1L)
                .orderDate(LocalDate.of(2001, 10, 01))
                .customer(savedCustomer)
                .build();
        MuseumEntity bel = MuseumEntity.builder().id(2L).name("bel").location("123 Amsterdam").phone("0123456789").build();
        TicketEntity t1= TicketEntity.builder().id(3L).name("Children").price(3.5).description("Above 15").quantity(300).museumEntity(bel).build();
        CreateOrderItemRequest orderItemRequest = CreateOrderItemRequest.builder()
                .ticketId(3L)
                .amount(3)
                .orderId(1L)
                .build();
        OrderItemEntity savedOrderItem = OrderItemEntity.builder()
                .id(4L)
                .ticket(t1)
                .amount(3)
                .price(t1.getPrice())
                .order(order)
                .build();
        when(ticketRepository.findTicketById(3L)).thenReturn(t1);
        when(orderRepository.findOrderById(1L)).thenReturn(order);
        when(orderItemRepository.save(any(OrderItemEntity.class))).thenReturn(savedOrderItem);
        CreateOrderItemResponse actual = createOrderItemUseCase.createOrderItem(orderItemRequest);
        CreateOrderItemResponse expect = CreateOrderItemResponse.builder().orderItemId(4L).build();
        assertEquals(actual,expect);
        verify(ticketRepository).findTicketById(3L);
        verify(orderRepository).findOrderById(1L);
        verify(orderItemRepository).save(any(OrderItemEntity.class));
    }

}