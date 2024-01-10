package nl.fontys.s3.museumticket.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "price")
    private Double price;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "quantity_per_day")
    private Integer quantity;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "museum_id")
    private MuseumEntity museumEntity;
}
