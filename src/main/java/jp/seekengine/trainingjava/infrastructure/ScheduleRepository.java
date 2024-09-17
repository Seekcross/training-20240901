package jp.seekengine.trainingjava.infrastructure;

import jp.seekengine.trainingjava.infrastructure.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
}
