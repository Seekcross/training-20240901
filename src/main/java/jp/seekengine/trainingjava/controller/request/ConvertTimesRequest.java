package jp.seekengine.trainingjava.controller.request;


import java.io.Serializable;
import java.util.List;

public record ConvertTimesRequest(
        List<time> times
) implements Serializable {
    public record time(
            Integer year,
            Integer month,
            Integer date,
            Integer hour,
            Integer minute,
            Integer second
    ) {}
}
