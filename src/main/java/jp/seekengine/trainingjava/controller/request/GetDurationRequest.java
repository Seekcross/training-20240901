package jp.seekengine.trainingjava.controller.request;

import java.io.Serializable;

public record GetDurationRequest(
        StartTime startTime,
        EndTime endTime
) implements Serializable {
    public record StartTime(
            Integer year,
            Integer month,
            Integer date,
            Integer hour,
            Integer minute,
            Integer second
    ) {}

    public record EndTime(
            Integer year,
            Integer month,
            Integer date,
            Integer hour,
            Integer minute,
            Integer second
    ) {}
}
