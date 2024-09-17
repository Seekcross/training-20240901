package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;

public record CalculateEndTimeResponse(
        String endTime
) implements Serializable {}
