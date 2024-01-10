package nl.fontys.s3.museumticket.domain.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequest {
    @NotNull
    private String name;
    @NotNull

    private Double price;
    private String description;
    @NotNull
    private Integer quantity;
    @NotNull
    private String museumName;
}
