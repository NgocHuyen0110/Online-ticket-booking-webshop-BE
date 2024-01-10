package nl.fontys.s3.museumticket.domain.museum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMuseumRequest {
    @NotNull
    private String name;
    @NotNull
    private String location;
    @NotNull
    @NotBlank
    private String phone;

    private String description;
}
