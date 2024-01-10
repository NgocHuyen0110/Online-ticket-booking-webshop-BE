package nl.fontys.s3.museumticket.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.*;
import nl.fontys.s3.museumticket.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.museumticket.domain.customer.CreateCustomerRequest;
import nl.fontys.s3.museumticket.domain.customer.CreateCustomerResponse;
import nl.fontys.s3.museumticket.domain.customer.Customer;
import nl.fontys.s3.museumticket.domain.customer.GetAllCustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000/")
public class CustomersController {
    private final GetCustomerUseCase getCustomerUseCase;
    private final GetAllCustomerUseCase getAllCustomerUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    @GetMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") final long id) {
        final Optional<Customer> customerOptional = getCustomerUseCase.getCustomer(id);
        if (customerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customerOptional.get());
    }
    @GetMapping
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<GetAllCustomerResponse> getAllCustomers() {
        return ResponseEntity.ok(getAllCustomerUseCase.getCustomers());
    }

    @PostMapping()
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        CreateCustomerResponse response = createCustomerUseCase.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
