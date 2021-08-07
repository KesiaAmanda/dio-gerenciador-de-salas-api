package one.digitalinnovation.saladereuniao.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="meetingroom")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "startHour", nullable = false)
    private LocalDateTime startHour;

    @Column(name = "endHour", nullable = false)
    private LocalDateTime endHour;
}
