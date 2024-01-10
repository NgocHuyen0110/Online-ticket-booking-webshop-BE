package nl.fontys.s3.museumticket.business.impl.admin;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.exception.InvalidAdminException;
import nl.fontys.s3.museumticket.business.DeleteAdminUseCase;
import nl.fontys.s3.museumticket.persistence.AdministrationRepository;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class DeleteAdminUseCaseImpl implements DeleteAdminUseCase {
    private final AdministrationRepository administrationRepository;

    @Override
    public void deleteAdminById(long id) {
        Optional<AdminEntity> admin = administrationRepository.findById(id);
        if(admin.isEmpty()){
            throw new InvalidAdminException("ADMIN_ID_INVALID");
        }
        this.administrationRepository.deleteById(id);
    }
}
