package jp.seekengine.trainingjava.controller.request;

import java.io.Serializable;

public record CalculateEndTimeRequest(
        StartTime startTime,
        Duration duration
) implements Serializable {
    public record StartTime(
            Integer year,
            Integer month,
            Integer date,
            Integer hour,
            Integer minute,
            Integer second,
            String requestTimeZoneId,
            String responseTimeZoneId
    ) {}

    public record Duration(
            Integer hour,
            Integer minute,
            Integer second
    ) {}
}
