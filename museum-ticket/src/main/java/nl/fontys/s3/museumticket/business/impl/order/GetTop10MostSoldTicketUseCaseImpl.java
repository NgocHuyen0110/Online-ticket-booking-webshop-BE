package nl.fontys.s3.museumticket.business.impl.order;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.GetTop10MostSoldTicketUseCase;
import nl.fontys.s3.museumticket.domain.order.GetTop10MostSoldTicketRequest;
import nl.fontys.s3.museumticket.domain.order.GetTop10MostSoldTicketResponse;
import nl.fontys.s3.museumticket.domain.order.SoldTicketDetails;
import nl.fontys.s3.museumticket.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class GetTop10MostSoldTicketUseCaseImpl implements GetTop10MostSoldTicketUseCase {
    private final OrderRepository orderRepository;
    @Override
    public GetTop10MostSoldTicketResponse getTop10MostSoldTickets(GetTop10MostSoldTicketRequest request ) {
        LocalDate start;
        LocalDate end;
        start = LocalDate.parse(request.getStart());
        end = LocalDate.parse(request.getEnd());
        List<Object[]> results = orderRepository.getTop10MostSoldTicketForPastXMonths(start,end);

        List<SoldTicketDetails> soldTickets = results.stream()
                .limit(10)
                .map(objects -> SoldTicketDetails.builder()
                        .name((String) objects[0])
                        .museumName((String) objects[1])
                        .totalAmount((Long) objects[2])
                        .build())
                .collect(Collectors.toList());

        return GetTop10MostSoldTicketResponse.builder().soldTickets(soldTickets).build();






    }

}
