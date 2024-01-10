package nl.fontys.s3.museumticket.domain.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAdminResponse {
    private Long adminId;
}
