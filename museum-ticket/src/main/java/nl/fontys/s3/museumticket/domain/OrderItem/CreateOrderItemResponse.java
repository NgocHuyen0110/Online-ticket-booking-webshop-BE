package nl.fontys.s3.museumticket.domain.OrderItem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderItemResponse {
    private Long orderItemId;
}
