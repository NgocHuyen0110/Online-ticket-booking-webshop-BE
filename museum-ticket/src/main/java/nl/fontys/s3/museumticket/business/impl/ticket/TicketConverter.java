package nl.fontys.s3.museumticket.business.impl.ticket;

import nl.fontys.s3.museumticket.business.impl.museum.MuseumConverter;
import nl.fontys.s3.museumticket.domain.Ticket.Ticket;
import nl.fontys.s3.museumticket.persistence.entity.TicketEntity;

public class TicketConverter {
    private TicketConverter(){

    }
    public static Ticket covert(TicketEntity ticketEntity){
        return Ticket.builder()
                .id(ticketEntity.getId())
                .name(ticketEntity.getName())
                .price(ticketEntity.getPrice())
                .description(ticketEntity.getDescription())
                .quantity(ticketEntity.getQuantity())
                .museum(MuseumConverter.convert(ticketEntity.getMuseumEntity()))
                .build();
    }
}
