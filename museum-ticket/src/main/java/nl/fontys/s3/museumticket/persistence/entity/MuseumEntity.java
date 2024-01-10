package nl.fontys.s3.museumticket.persistence.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "museum")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MuseumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "phone")
    @NotBlank
    private String phone;
    @Column(name = "description")
    private String description;
}