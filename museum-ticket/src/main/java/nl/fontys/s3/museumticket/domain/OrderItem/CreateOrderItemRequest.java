package nl.fontys.s3.museumticket.domain.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequest {

    private Long id;
    @NotNull
    private Long ticketId;
    @NotNull
    private Integer amount;
    @NotNull
    private Double price;
    @NotNull
    private Long orderId;

}
