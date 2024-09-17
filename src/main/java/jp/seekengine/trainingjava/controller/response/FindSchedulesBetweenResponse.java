package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;
import java.util.List;

public record FindSchedulesBetweenResponse(
        List<Schedule> Schedules
) implements Serializable {
    public record Schedule(
      Integer scheduleId,
      String scheduleTitle,
      String fromDatetime,
      String toDatetime
    ){}
}
