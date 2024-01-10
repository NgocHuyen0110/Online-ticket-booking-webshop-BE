package nl.fontys.s3.museumticket.business.impl.ticket;

import nl.fontys.s3.museumticket.domain.Ticket.CreateTicketRequest;
import nl.fontys.s3.museumticket.domain.Ticket.CreateTicketResponse;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.TicketRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import nl.fontys.s3.museumticket.persistence.entity.TicketEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTicketUseCaseImplTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private MuseumRepository museumRepository;
    @InjectMocks
    private CreateTicketUseCaseImpl createTicketUseCase;
    @Test
    void create_Ticket(){
        CreateTicketRequest request = CreateTicketRequest.builder()
                .name("Adult")
                .price(3.0)
                .description("From 24")
                .quantity(300)
                .museumName("Van Gogh")
                .build();
        MuseumEntity museumEntity = MuseumEntity.builder().id(3L).name("Van gogh").location("Amsterdam").phone("064542567").description("beautiful").build();
        TicketEntity savedTicket = TicketEntity.builder()
                .id(1L)
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .quantity(request.getQuantity())
                .museumEntity(museumEntity)
                .build();
        when(museumRepository.findMuseumByName(request.getMuseumName())).thenReturn(museumEntity);
        when(ticketRepository.save(any(TicketEntity.class))).thenReturn(savedTicket);
        CreateTicketResponse actual = createTicketUseCase.createTicket(request);
        CreateTicketResponse expect = CreateTicketResponse.builder().ticketId(savedTicket.getId()).build();
        assertEquals(expect,actual);
        verify(ticketRepository).save(any(TicketEntity.class));
        verify(museumRepository).findMuseumByName(request.getMuseumName());
    }

}