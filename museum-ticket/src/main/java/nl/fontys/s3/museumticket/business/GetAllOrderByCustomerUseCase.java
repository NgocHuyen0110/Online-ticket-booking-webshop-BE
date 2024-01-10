package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.order.GetAllOrderResponseByCustomer;

public interface GetAllOrderByCustomerUseCase {
    GetAllOrderResponseByCustomer getOrders(Long customerId);

}
