package jp.seekengine.trainingjava.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Data
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "schedule_title")
    private String scheduleTitle;

    @Column(name = "from_datetime")
    private LocalDateTime fromDatetime;

    @Column(name = "to_datetime")
    private LocalDateTime toDatetime;
}
