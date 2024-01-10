package nl.fontys.s3.museumticket.domain.museum;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class GetAllMuseumsResponse {
    private List<Museum> museums;
}
