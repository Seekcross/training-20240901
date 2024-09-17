package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;

public record GetScheduleResponse(
        Integer scheduleId,
        String ScheduleTitle,
        String fromDatetime,
        String toDatetime
) implements Serializable {}
