package nl.fontys.s3.museumticket.business.impl.order;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.GetAllOrderByCustomerUseCase;
import nl.fontys.s3.museumticket.domain.order.GetAllOrderResponseByCustomer;
import nl.fontys.s3.museumticket.domain.order.Order;
import nl.fontys.s3.museumticket.persistence.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
@AllArgsConstructor

public class GetAllOrderByCustomerUseCaseImpl implements GetAllOrderByCustomerUseCase {
    private final OrderRepository orderRepository;
    @Override
    public GetAllOrderResponseByCustomer getOrders(Long customerId) {
        List<Order> orders = orderRepository.findAllByCustomer_IdOrderByIdDesc(customerId)
                .stream()
                .map(OrderConverter::convert)
                .toList();
        return GetAllOrderResponseByCustomer.builder()
                .orders(orders)
                .build();
    }

}
