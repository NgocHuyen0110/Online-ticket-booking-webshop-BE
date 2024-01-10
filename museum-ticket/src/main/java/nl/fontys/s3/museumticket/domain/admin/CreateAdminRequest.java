package nl.fontys.s3.museumticket.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdminRequest {
    @NotNull
    private String fullName;

    private LocalDate dob;
    @NotNull
    private String address;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    @Length(max = 50)
    private String password;
}

