package nl.fontys.s3.museumticket.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.*;
import nl.fontys.s3.museumticket.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.museumticket.domain.museum.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/museums")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:3000/")
public class MuseumsController {
    private final GetAllMuseumsUseCase getAllMuseumsUseCase;
    private final CreateMuseumUseCase createMuseumUseCase;
    private final DeleteMuseumUseCase deleteMuseumUseCase;
    private final UpdateMuseumUseCase updateMuseumUseCase;
    private final GetMuseumUseCase getMuseumUseCase;
    @GetMapping()
    public ResponseEntity<GetAllMuseumsResponse> getMuseumsResponseEntity(){
        return ResponseEntity.ok(getAllMuseumsUseCase.getMuseums());
    }
    @PostMapping()
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<CreateMuseumResponse> createMuseum(@RequestBody @Valid CreateMuseumRequest request) {
        CreateMuseumResponse response = createMuseumUseCase.createMuseum(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteMuseum(@PathVariable int id) {
        deleteMuseumUseCase.deleteMuseum(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Void> updateMuseum(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateMuseumRequest request){
        request.setId(id);
        updateMuseumUseCase.updateMuseum(request);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    public ResponseEntity<Museum> getMuseumById(@PathVariable(value = "id") final long id){
        final Optional<Museum> museum = getMuseumUseCase.getMuseumById(id);
        if(museum.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(museum.get());

    }
    @GetMapping("/search")
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    public ResponseEntity<GetAllMuseumsResponse> getMuseumsByName(@RequestParam String name){
        GetAllMuseumsResponse response = getAllMuseumsUseCase.getMuseumsByName(name);
        return ResponseEntity.ok(response);
    }




}
