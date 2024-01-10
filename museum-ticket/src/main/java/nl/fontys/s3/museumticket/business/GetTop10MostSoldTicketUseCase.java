package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.order.GetTop10MostSoldTicketRequest;
import nl.fontys.s3.museumticket.domain.order.GetTop10MostSoldTicketResponse;

public interface GetTop10MostSoldTicketUseCase {
    GetTop10MostSoldTicketResponse getTop10MostSoldTickets(GetTop10MostSoldTicketRequest request);
}
