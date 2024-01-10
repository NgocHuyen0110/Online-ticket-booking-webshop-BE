package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.admin.CreateAdminRequest;
import nl.fontys.s3.museumticket.domain.admin.CreateAdminResponse;

public interface CreateAdminUseCase {
    CreateAdminResponse createAdmin(CreateAdminRequest request);
}
