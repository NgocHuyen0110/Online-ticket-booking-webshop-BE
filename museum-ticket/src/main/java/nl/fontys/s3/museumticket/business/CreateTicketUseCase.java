package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.Ticket.CreateTicketRequest;
import nl.fontys.s3.museumticket.domain.Ticket.CreateTicketResponse;

public interface CreateTicketUseCase {
    CreateTicketResponse createTicket (CreateTicketRequest request);
}
