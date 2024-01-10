package nl.fontys.s3.museumticket.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateOrderItemUseCase;
import nl.fontys.s3.museumticket.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.museumticket.domain.OrderItem.CreateOrderItemRequest;
import nl.fontys.s3.museumticket.domain.OrderItem.CreateOrderItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/orderItems")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:3000/")
public class OrderItemController {
    private CreateOrderItemUseCase createOrderItemUseCase;
    @PostMapping
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    public ResponseEntity<CreateOrderItemResponse> createOrderItem(@RequestBody @Valid CreateOrderItemRequest request){
        CreateOrderItemResponse response = createOrderItemUseCase.createOrderItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
