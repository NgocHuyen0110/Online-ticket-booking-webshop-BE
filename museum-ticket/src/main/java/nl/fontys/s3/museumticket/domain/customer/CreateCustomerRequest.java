package nl.fontys.s3.museumticket.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {
    @NotNull
    private String fullName;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    @Length(max = 50)
    private String password;
}
