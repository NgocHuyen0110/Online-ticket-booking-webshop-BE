package nl.fontys.s3.museumticket.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateAdminUseCase;
import nl.fontys.s3.museumticket.business.GetAdminUseCase;
import nl.fontys.s3.museumticket.business.GetAllAdminUseCase;
import nl.fontys.s3.museumticket.business.DeleteAdminUseCase;
import nl.fontys.s3.museumticket.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.museumticket.domain.admin.Administration;
import nl.fontys.s3.museumticket.domain.admin.CreateAdminRequest;
import nl.fontys.s3.museumticket.domain.admin.CreateAdminResponse;
import nl.fontys.s3.museumticket.domain.admin.GetAllAdminResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000/")
public class AdminController {
    private final CreateAdminUseCase createAdminUseCase;
    private final GetAdminUseCase getAdminUseCase;
    private final DeleteAdminUseCase deleteAdminUseCase;
    private final GetAllAdminUseCase getAllAdminUseCase;

    @PostMapping()
    public ResponseEntity<CreateAdminResponse> createAdmin(@RequestBody @Valid CreateAdminRequest request) {
        CreateAdminResponse response = createAdminUseCase.createAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Administration> getAdmin(@PathVariable(value = "id") final long id) {
        System.out.println("aminIdController "+ id);
        final Optional<Administration> adminOptional = getAdminUseCase.getAdmin(id);
        if (adminOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(adminOptional.get());
    }
    @DeleteMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id){
        deleteAdminUseCase.deleteAdminById(id);
        return ResponseEntity.noContent().build();
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping()
    public ResponseEntity<GetAllAdminResponse> getAdminsResponseEntity(){
        return ResponseEntity.ok(getAllAdminUseCase.getAdmins());
    }
}
