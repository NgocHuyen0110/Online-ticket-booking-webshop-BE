package nl.fontys.s3.museumticket.business.impl.order;

import nl.fontys.s3.museumticket.domain.order.GetTop10MostSoldTicketRequest;
import nl.fontys.s3.museumticket.domain.order.GetTop10MostSoldTicketResponse;
import nl.fontys.s3.museumticket.domain.order.SoldTicketDetails;
import nl.fontys.s3.museumticket.persistence.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTop10MostSoldTicketUseCaseImplTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private GetTop10MostSoldTicketUseCaseImpl getTop10MostSoldTickets;
    @Test
    void getTop10MostSoldTickets_WithValidRequest_ReturnsTop10SoldTickets() {

        LocalDate start = LocalDate.parse("2023-01-01");
        LocalDate end = LocalDate.parse("2023-06-01");

        GetTop10MostSoldTicketRequest request = GetTop10MostSoldTicketRequest.builder()
                .start("2023-01-01")
                .end("2023-06-01")
                .build();
        List<Object[]> results = new ArrayList<>();
        results.add(new Object[]{"Ticket 1", "Museum A", 10L});
        results.add(new Object[]{"Ticket 2", "Museum B", 8L});
        results.add(new Object[]{"Ticket 3", "Museum C", 5L});
        when(orderRepository.getTop10MostSoldTicketForPastXMonths(start, end)).thenReturn(results);

        List<SoldTicketDetails> expectedSoldTickets = new ArrayList<>();
        expectedSoldTickets.add(SoldTicketDetails.builder()
                .name("Ticket 1")
                .museumName("Museum A")
                .totalAmount(10L)
                .build());
        expectedSoldTickets.add(SoldTicketDetails.builder()
                .name("Ticket 2")
                .museumName("Museum B")
                .totalAmount(8L)
                .build());
        expectedSoldTickets.add(SoldTicketDetails.builder()
                .name("Ticket 3")
                .museumName("Museum C")
                .totalAmount(5L)
                .build());
        GetTop10MostSoldTicketResponse expectedResponse = GetTop10MostSoldTicketResponse.builder()
                .soldTickets(expectedSoldTickets)
                .build();
        GetTop10MostSoldTicketResponse actualResponse = getTop10MostSoldTickets.getTop10MostSoldTickets(request);

        assertEquals(expectedResponse, actualResponse);
        verify(orderRepository).getTop10MostSoldTicketForPastXMonths(start,end);
    }

}