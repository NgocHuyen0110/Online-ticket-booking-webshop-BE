package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.Ticket.GetAllTicketResponse;

public interface GetAllTicketByMuseumIdUseCase {
    GetAllTicketResponse getTicketsByMuseumId(long ids);
}
