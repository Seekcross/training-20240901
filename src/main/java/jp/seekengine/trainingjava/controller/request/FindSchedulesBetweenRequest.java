package jp.seekengine.trainingjava.controller.request;

import java.io.Serializable;
import java.time.LocalDateTime;

public record FindSchedulesBetweenRequest(
        LocalDateTime fromDatetime,
        LocalDateTime toDatetime
) implements Serializable {}
