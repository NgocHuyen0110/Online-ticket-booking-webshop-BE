package nl.fontys.s3.museumticket.business.impl.order;

import nl.fontys.s3.museumticket.business.impl.customer.CustomerConverter;
import nl.fontys.s3.museumticket.business.impl.orderItem.OrderItemConverter;
import nl.fontys.s3.museumticket.domain.order.Order;
import nl.fontys.s3.museumticket.persistence.entity.OrderEntity;

public class OrderConverter {
    private OrderConverter(){

    }
    public static Order convert(OrderEntity orderEntity){
        return Order.builder()
                .id(orderEntity.getId())
                .orderDate(orderEntity.getOrderDate())
                .customer(CustomerConverter.convert(orderEntity.getCustomer()))
                .orderItemList(OrderItemConverter.convertList(orderEntity.getOrderItemList()))
                .build();
    }
}
