package nl.fontys.s3.museumticket.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateTicketUseCase;
import nl.fontys.s3.museumticket.business.GetAllTicketByMuseumIdUseCase;
import nl.fontys.s3.museumticket.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.museumticket.domain.Ticket.CreateTicketRequest;
import nl.fontys.s3.museumticket.domain.Ticket.CreateTicketResponse;
import nl.fontys.s3.museumticket.domain.Ticket.GetAllTicketResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:3000/")
public class TicketController {
    private final CreateTicketUseCase createTicketUseCase;

    private final GetAllTicketByMuseumIdUseCase getAllTicketByMuseumIdUseCase;
    @PostMapping()
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<CreateTicketResponse> createTicket(@RequestBody @Valid CreateTicketRequest request){
        CreateTicketResponse response = createTicketUseCase.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAllTicketResponse> getTicketsByMuseumId(@PathVariable("id") long id){
        return ResponseEntity.ok(getAllTicketByMuseumIdUseCase.getTicketsByMuseumId(id));

    }

}

