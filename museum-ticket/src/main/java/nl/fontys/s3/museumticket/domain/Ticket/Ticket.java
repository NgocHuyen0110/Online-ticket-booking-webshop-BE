package nl.fontys.s3.museumticket.domain.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.museumticket.domain.museum.Museum;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private Museum museum;
}
