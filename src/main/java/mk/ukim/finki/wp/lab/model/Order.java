package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shop_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Balloon balloon;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    public Order() {

    }

    public Order(Balloon balloon, User user, LocalDateTime dateCreated) {
        this.balloon = balloon;
        this.user = user;
        this.dateCreated = dateCreated;
    }
}
