package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.admin.Administration;

import java.util.Optional;

public interface GetAdminUseCase {
    Optional<Administration> getAdmin(long adminId);
}
