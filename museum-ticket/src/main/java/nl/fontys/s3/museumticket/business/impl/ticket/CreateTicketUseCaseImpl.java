package nl.fontys.s3.museumticket.business.impl.ticket;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateMuseumUseCase;
import nl.fontys.s3.museumticket.business.CreateTicketUseCase;
import nl.fontys.s3.museumticket.business.MuseumNameValidation;
import nl.fontys.s3.museumticket.domain.Ticket.CreateTicketRequest;
import nl.fontys.s3.museumticket.domain.Ticket.CreateTicketResponse;
import nl.fontys.s3.museumticket.domain.museum.CreateMuseumRequest;
import nl.fontys.s3.museumticket.domain.museum.CreateMuseumResponse;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.TicketRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import nl.fontys.s3.museumticket.persistence.entity.TicketEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTicketUseCaseImpl implements CreateTicketUseCase {
    private final TicketRepository ticketRepository;
    private final MuseumRepository museumRepository;
    //private final MuseumNameValidation museumNameValidation;

    @Override
    public CreateTicketResponse createTicket(CreateTicketRequest request) {
       // museumNameValidation.validatedName(request.getMuseumName());
        TicketEntity savedTicket = saveTicket(request);
        return CreateTicketResponse.builder()
                .ticketId(savedTicket.getId())
                .build();
    }

    private TicketEntity saveTicket(CreateTicketRequest request){
       MuseumEntity museumEntity = museumRepository.findMuseumByName(request.getMuseumName());
       TicketEntity newTicket = TicketEntity.builder()
               .name(request.getName())
               .price(request.getPrice())
               .description(request.getDescription())
               .quantity(request.getQuantity())
               .museumEntity(museumEntity)
               .build();
       return ticketRepository.save(newTicket);

    }


}
