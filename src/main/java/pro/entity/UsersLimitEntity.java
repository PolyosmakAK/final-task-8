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
    @Column(name = "day_limit")
    private Float dayLimit;
    @Column(name = "current_limit")
    private Float currentLimit;
    @Column(name = "last_reservation")
    private Float lastReservation;
}
