package nl.fontys.s3.museumticket.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Administration {
    private Long id;
    private String fullName;
    private LocalDate dob;
    private String address;
}
