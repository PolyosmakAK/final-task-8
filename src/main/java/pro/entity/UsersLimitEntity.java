package pro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users_limit")
@AllArgsConstructor
@NoArgsConstructor
public class UsersLimitEntity {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(name = "day_limit", nullable = false)
    private Double dayLimit;
    @Column(name = "current_limit")
    private Double currentLimit;
    @Column(name = "last_reservation")
    private Double lastReservation;
}
