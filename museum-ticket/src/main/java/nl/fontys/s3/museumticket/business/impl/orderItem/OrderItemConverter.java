package nl.fontys.s3.museumticket.business.impl.orderItem;

import nl.fontys.s3.museumticket.business.impl.ticket.TicketConverter;
import nl.fontys.s3.museumticket.domain.OrderItem.OrderItem;
import nl.fontys.s3.museumticket.persistence.entity.OrderItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemConverter {
    private OrderItemConverter(){

    }
    public static OrderItem convert(OrderItemEntity orderItemEntity){
        return  OrderItem.builder()
                .id(orderItemEntity.getId())
                .ticket(TicketConverter.covert(orderItemEntity.getTicket()))
                .amount(orderItemEntity.getAmount())
                .price(orderItemEntity.getPrice())
                .build();
    }
    public static List<OrderItem> convertList(List<OrderItemEntity> orderItemEntities) {
        return orderItemEntities.stream()
                .map(OrderItemConverter::convert)
                .collect(Collectors.toList());
    }
}
