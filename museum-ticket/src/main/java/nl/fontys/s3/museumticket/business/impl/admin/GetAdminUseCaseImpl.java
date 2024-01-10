package nl.fontys.s3.museumticket.business.impl.admin;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.GetAdminUseCase;
import nl.fontys.s3.museumticket.business.exception.UnauthorizedDataAccessException;
import nl.fontys.s3.museumticket.domain.AccessToken;
import nl.fontys.s3.museumticket.domain.admin.Administration;
import nl.fontys.s3.museumticket.persistence.AdministrationRepository;
import nl.fontys.s3.museumticket.persistence.entity.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAdminUseCaseImpl implements GetAdminUseCase {
    private AdministrationRepository administrationRepository;
    private AccessToken requestAccessToken;

    @Override
    public Optional<Administration> getAdmin(long adminId) {
        System.out.println("aminId "+ adminId);
//        if (!requestAccessToken.hasRole(RoleEnum.CUSTOMER.name())) {
//           if (requestAccessToken.getAdminId() != adminId) {
//                throw new UnauthorizedDataAccessException("ADMIN_ID_NOT_FROM_LOGGED_IN_USER");
//            }
//        }
//        return administrationRepository.findById(adminId).map(AdminConverter::convert);

            if (requestAccessToken.getAdminId() != adminId) {
                throw new UnauthorizedDataAccessException("ADMIN_ID_NOT_FROM_LOGGED_IN_USER");
            }
        return administrationRepository.findById(adminId).map(AdminConverter::convert);
    }
}
