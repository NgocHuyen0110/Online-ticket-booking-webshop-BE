package nl.fontys.s3.museumticket.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoldTicketDetails {
    private String name;
    private String museumName;
    private Long totalAmount;
}
