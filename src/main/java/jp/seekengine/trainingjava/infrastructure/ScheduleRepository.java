package jp.seekengine.trainingjava.infrastructure;

import jp.seekengine.trainingjava.infrastructure.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    List<ScheduleEntity> findByFromDatetimeGreaterThanAndToDatetimeLessThan(
            LocalDateTime fromDatetime,
            LocalDateTime toDatetime
    );
}
