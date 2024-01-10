package nl.fontys.s3.museumticket.business.impl.ticket;

import nl.fontys.s3.museumticket.domain.Ticket.GetAllTicketResponse;
import nl.fontys.s3.museumticket.domain.Ticket.Ticket;
import nl.fontys.s3.museumticket.domain.museum.Museum;
import nl.fontys.s3.museumticket.persistence.TicketRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import nl.fontys.s3.museumticket.persistence.entity.TicketEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllTicketByMuseumIdUseCaseImplTest {
    @Mock
    private TicketRepository ticketRepository;
    @InjectMocks
    private GetAllTicketByMuseumIdUseCaseImpl getAllTicketByMuseumIdUseCase;
    @Test
    public void testGetTicketsByMuseumId() {
        long museumId=1L;
        MuseumEntity bel = MuseumEntity.builder().id(1L).name("bel").location("123 Amsterdam").phone("0123456789").build();
        TicketEntity t1= TicketEntity.builder().id(1L).name("Children").price(3.5).description("Above 15").quantity(300).museumEntity(bel).build();
        TicketEntity t2= TicketEntity.builder().id(2L).name("Adult").price(7.0).description("From 15").quantity(300).museumEntity(bel).build();
        when(ticketRepository.findAllByMuseumId(museumId)).thenReturn(List.of(t1,t2));
        GetAllTicketResponse actual = getAllTicketByMuseumIdUseCase.getTicketsByMuseumId(museumId);
        Museum bel1 = Museum.builder().id(1L).name("bel").location("123 Amsterdam").phone("0123456789").build();
        Ticket t3= Ticket.builder().id(1L).name("Children").price(3.5).description("Above 15").quantity(300).museum(bel1).build();
        Ticket t4= Ticket.builder().id(2L).name("Adult").price(7.0).description("From 15").quantity(300).museum(bel1).build();
        GetAllTicketResponse expect = GetAllTicketResponse.builder().tickets(List.of(t3,t4)).build();
        assertEquals(actual,expect);
        verify(ticketRepository).findAllByMuseumId(museumId);
    }
}



