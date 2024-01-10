package nl.fontys.s3.museumticket.business.impl.order;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateOrderUseCase;
import nl.fontys.s3.museumticket.domain.order.CreateOrderRequest;
import nl.fontys.s3.museumticket.domain.order.CreateOrderResponse;
import nl.fontys.s3.museumticket.persistence.CustomerRepository;
import nl.fontys.s3.museumticket.persistence.OrderRepository;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import nl.fontys.s3.museumticket.persistence.entity.OrderEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        OrderEntity savedOrder = saveOrder(request);
        return  CreateOrderResponse.builder()
                .orderId(savedOrder.getId())
                .build();
    }
    public OrderEntity saveOrder(CreateOrderRequest request){
        CustomerEntity customer = customerRepository.findCustomerEntityById(request.getCustomerId());
        OrderEntity newOrder = OrderEntity.builder()
                .orderDate(request.getOrderDate())
                .customer(customer)
                .build();
        return orderRepository.save(newOrder);
    }
}
