package nl.fontys.s3.museumticket.domain.museum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Museum {
    private Long id;
    private String name;
    private String location;
    private String phone;
    private String description;


}
