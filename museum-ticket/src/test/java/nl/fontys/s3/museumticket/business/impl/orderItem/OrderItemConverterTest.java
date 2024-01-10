package nl.fontys.s3.museumticket.business.impl.orderItem;
import nl.fontys.s3.museumticket.business.impl.orderItem.OrderItemConverter;
import nl.fontys.s3.museumticket.domain.OrderItem.OrderItem;
import nl.fontys.s3.museumticket.domain.Ticket.Ticket;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import nl.fontys.s3.museumticket.persistence.entity.OrderItemEntity;
import nl.fontys.s3.museumticket.persistence.entity.TicketEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemConverterTest {

    @Test
    public void testConvert() {
        TicketEntity ticketEntity = createTicketEntity(1L, "Sample Ticket", 10.0, "Sample Description", 5);

        OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                .id(1L)
                .ticket(ticketEntity)
                .amount(2)
                .price(20.0)
                .build();

        OrderItem orderItem = OrderItemConverter.convert(orderItemEntity);

        assertEquals(1L, orderItem.getId());
        assertEquals(2, orderItem.getAmount());
       assertEquals(20.0, orderItem.getPrice());

        Ticket ticket = orderItem.getTicket();
        assertNotNull(ticket);
        assertEquals(1L, ticket.getId());
        assertEquals("Sample Ticket", ticket.getName());
        assertEquals(10.0, ticket.getPrice());
        assertEquals("Sample Description", ticket.getDescription());
        assertEquals(5, ticket.getQuantity());
    }

    @Test
    public void testConvertList() {
        List<OrderItemEntity> orderItemEntities = Arrays.asList(
                createOrderItemEntity(1L, createTicketEntity(1L, "Ticket 1", 10.0, "Description 1", 5), 2, 20.0),
                createOrderItemEntity(2L, createTicketEntity(2L, "Ticket 2", 15.0, "Description 2", 3), 3, 30.0),
                createOrderItemEntity(3L, createTicketEntity(3L, "Ticket 3", 20.0, "Description 3", 4), 4, 40.0)
        );

        List<OrderItem> orderItems = OrderItemConverter.convertList(orderItemEntities);

        Assertions.assertEquals(3, orderItems.size());

        OrderItem firstOrderItem = orderItems.get(0);
        assertEquals(1L, firstOrderItem.getId());
        assertEquals(2, firstOrderItem.getAmount());
        assertEquals(20.0, firstOrderItem.getPrice());
        assertEquals(1L, firstOrderItem.getTicket().getId());

        OrderItem secondOrderItem = orderItems.get(1);
        assertEquals(2L, secondOrderItem.getId());
       assertEquals(3, secondOrderItem.getAmount());
        assertEquals(30.0, secondOrderItem.getPrice());
        assertEquals(2L, secondOrderItem.getTicket().getId());

        OrderItem thirdOrderItem = orderItems.get(2);
        assertEquals(3L, thirdOrderItem.getId());
       assertEquals(4, thirdOrderItem.getAmount());
        assertEquals(40.0, thirdOrderItem.getPrice());
        assertEquals(3L, thirdOrderItem.getTicket().getId());
    }

    private OrderItemEntity createOrderItemEntity(long id, TicketEntity ticket, int amount, double price) {
        return OrderItemEntity.builder()
                .id(id)
                .ticket(ticket)
                .amount(amount)
                .price(price)
                .build();
    }

    private TicketEntity createTicketEntity(long id, String name, double price, String description, int quantity) {
        return TicketEntity.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .quantity(quantity)
                .museumEntity(createMuseumEntity())
                .build();
    }

    private MuseumEntity createMuseumEntity() {
        return MuseumEntity.builder()
                .id(1L)
                .name("Sample Museum")
                .location("Sample Location")
                .build();
    }
}
