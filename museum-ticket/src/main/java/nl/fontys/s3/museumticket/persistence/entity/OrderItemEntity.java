package nl.fontys.s3.museumticket.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_item")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(optional = true)
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "price")
    private double price;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderEntity order;
}
