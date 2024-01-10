package nl.fontys.s3.museumticket.business.impl.order;

import nl.fontys.s3.museumticket.domain.order.CreateOrderRequest;
import nl.fontys.s3.museumticket.domain.order.CreateOrderResponse;
import nl.fontys.s3.museumticket.persistence.CustomerRepository;
import nl.fontys.s3.museumticket.persistence.OrderRepository;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import nl.fontys.s3.museumticket.persistence.entity.OrderEntity;
import nl.fontys.s3.museumticket.persistence.entity.TicketEntity;
import nl.fontys.s3.museumticket.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CreateOrderUseCaseImpl createOrderUseCase;
    @Test
    void create_Order(){
        CustomerEntity saveCustomer = new CustomerEntity().builder()
                .id(1L)
                .fullName("Tom Nguyen")
                .build();
        CreateOrderRequest request = CreateOrderRequest.builder().orderDate(LocalDate.of(2001,10,01)).customerId(1L).build();
       OrderEntity savedOrder = OrderEntity.builder()
               .orderDate(request.getOrderDate())
               .customer(saveCustomer)
               .build();
       when(customerRepository.findCustomerEntityById(request.getCustomerId())).thenReturn(saveCustomer);
       when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedOrder);
        CreateOrderResponse actual = createOrderUseCase.createOrder(request);
        CreateOrderResponse expect = CreateOrderResponse.builder().orderId(savedOrder.getId()).build();
        assertEquals(actual,expect);
        verify(orderRepository).save(any(OrderEntity.class));
        verify(customerRepository).findCustomerEntityById(request.getCustomerId());

    }

}