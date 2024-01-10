package nl.fontys.s3.museumticket.domain.museum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMuseumRequest {
   private Long id;
    @NotNull
    private String name;
    @NotNull
    private String location;
    @NotNull
    @NotBlank
    private String phone;

    private String description;
}
