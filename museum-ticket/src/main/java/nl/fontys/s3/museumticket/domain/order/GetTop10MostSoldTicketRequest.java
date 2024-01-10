package nl.fontys.s3.museumticket.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTop10MostSoldTicketRequest {
    private String start;
    private String end;
}
