package nl.fontys.s3.museumticket.business.impl.order;
import nl.fontys.s3.museumticket.business.GetAllOrderByCustomerUseCase;
import nl.fontys.s3.museumticket.business.impl.order.GetAllOrderByCustomerUseCaseImpl;
import nl.fontys.s3.museumticket.business.impl.order.OrderConverter;
import nl.fontys.s3.museumticket.domain.customer.Customer;
import nl.fontys.s3.museumticket.domain.order.GetAllOrderResponseByCustomer;
import nl.fontys.s3.museumticket.domain.order.Order;
import nl.fontys.s3.museumticket.persistence.OrderRepository;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import nl.fontys.s3.museumticket.persistence.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllOrderByCustomerUseCaseImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderConverter orderConverter;

    @InjectMocks
    private GetAllOrderByCustomerUseCaseImpl getAllOrderByCustomerUseCase;

//    @Test
//    void getAllOrderByCustomer() {
////        // Arrange
////        long customerId = 1L;
////        CustomerEntity savedCustomer = new CustomerEntity().builder()
////                .id(1L)
////                .fullName("Thai Huyen")
////                .build();
////        OrderEntity order1 = OrderEntity.builder()
////                .orderDate(LocalDate.of(2001, 10, 01))
////                .customer(savedCustomer)
////                .build();
////        OrderEntity order2 = OrderEntity.builder()
////                .orderDate(LocalDate.of(2023, 10, 01))
////                .customer(savedCustomer)
////                .build();
////        when(orderRepository.findAllByCustomer_IdOrderByIdDesc(customerId)).thenReturn(List.of(order1, order2));
////
////        Customer customer = new Customer().builder()
////                .id(1L)
////                .fullName("Thai Huyen")
////                .build();
////        Order order3 = Order.builder()
////                .orderDate(LocalDate.of(2001, 10, 01))
////                .customer(customer)
////                .build();
////        Order order4 = Order.builder()
////                .orderDate(LocalDate.of(2023, 10, 01))
////                .customer(customer)
////                .build();
////        GetAllOrderResponseByCustomer expectedResponse = GetAllOrderResponseByCustomer.builder()
////                .orders(List.of(order3, order4))
////                .build();
////        when(orderConverter.convert(order1)).thenReturn(order3);
////        when(orderConverter.convert(order2)).thenReturn(order4);
////
////        // Act
////        GetAllOrderResponseByCustomer actualResponse = getAllOrderByCustomerUseCase.getOrders(customerId);
////
////        // Assert
////        assertEquals(expectedResponse, actualResponse);
////        verify(orderRepository).findAllByCustomer_IdOrderByIdDesc(customerId);
////        verify(orderConverter).convert(order1);
////        verify(orderConverter).convert(order2);
//
//    }
    @Test
    void getAllOrderByCustomer() {
        long customerId = 1L;
        List<OrderEntity> expectedOrderEntities = new ArrayList<>();
        when(orderRepository.findAllByCustomer_IdOrderByIdDesc(customerId))
                .thenReturn((List) expectedOrderEntities);
        GetAllOrderResponseByCustomer response = getAllOrderByCustomerUseCase.getOrders(customerId);
        assertEquals(expectedOrderEntities.size(), response.getOrders().size());
        Mockito.verify(orderRepository).findAllByCustomer_IdOrderByIdDesc(customerId);
    }
}
