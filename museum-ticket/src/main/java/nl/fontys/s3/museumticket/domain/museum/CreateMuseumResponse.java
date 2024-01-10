package nl.fontys.s3.museumticket.domain.museum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMuseumResponse {
    private Long museumId;
}
