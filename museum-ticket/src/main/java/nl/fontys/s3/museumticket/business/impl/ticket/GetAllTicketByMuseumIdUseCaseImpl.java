package nl.fontys.s3.museumticket.business.impl.ticket;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.GetAllTicketByMuseumIdUseCase;
import nl.fontys.s3.museumticket.domain.Ticket.GetAllTicketResponse;
import nl.fontys.s3.museumticket.domain.Ticket.Ticket;
import nl.fontys.s3.museumticket.persistence.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GetAllTicketByMuseumIdUseCaseImpl implements GetAllTicketByMuseumIdUseCase {
    private final TicketRepository ticketRepository;
    @Override
    public GetAllTicketResponse getTicketsByMuseumId(long id) {
        List<Ticket> tickets = ticketRepository.findAllByMuseumId(id)
                .stream()
                .map(TicketConverter::covert)
                .toList();
        return GetAllTicketResponse.builder()
                .tickets(tickets)
                .build();
    }
}
