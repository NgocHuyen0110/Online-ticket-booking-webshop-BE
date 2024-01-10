package nl.fontys.s3.museumticket.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateOrderUseCase;
import nl.fontys.s3.museumticket.business.GetAllOrderByCustomerUseCase;
import nl.fontys.s3.museumticket.business.GetTop10MostSoldTicketUseCase;
import nl.fontys.s3.museumticket.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.museumticket.domain.order.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:3000/")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetAllOrderByCustomerUseCase getAllOrderByCustomerUseCase;
    private final GetTop10MostSoldTicketUseCase getTop10MostSoldTickets;
    @PostMapping()
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
   public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request){
        CreateOrderResponse response = createOrderUseCase.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    public ResponseEntity<GetAllOrderResponseByCustomer> getOrdersByCustomer(@PathVariable(value = "id") final long id){
       return ResponseEntity.ok(getAllOrderByCustomerUseCase.getOrders(id));
    }


    @GetMapping("/top10mostsoldtickets")
    public ResponseEntity<GetTop10MostSoldTicketResponse> getTop10MostSoldTickets(@RequestParam(value = "start") String start, @RequestParam(value="end") String end)
    {
        GetTop10MostSoldTicketRequest request= new GetTop10MostSoldTicketRequest(start,end);

        GetTop10MostSoldTicketResponse response = getTop10MostSoldTickets.getTop10MostSoldTickets(request);
        return ResponseEntity.ok(response);
    }




}
