package nl.fontys.s3.museumticket.business.impl.admin;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.GetAllAdminUseCase;
import nl.fontys.s3.museumticket.domain.admin.Administration;
import nl.fontys.s3.museumticket.domain.admin.GetAllAdminResponse;
import nl.fontys.s3.museumticket.persistence.AdministrationRepository;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllAdminUseCaseImpl implements GetAllAdminUseCase {
    private AdministrationRepository administrationRepository;
    @Override
    public GetAllAdminResponse getAdmins() {
        List<AdminEntity> results;
        results = administrationRepository.findAll();
        final GetAllAdminResponse response = new GetAllAdminResponse();
        List<Administration> administrationsOTD = results
                .stream()
                .map(AdminConverter::convert)
                .toList();
        response.setAdmins(administrationsOTD);
        return response;
    }
}
