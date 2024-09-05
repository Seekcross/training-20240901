package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;

public record GetCurrentDateTimeResponse(
        String currentTime
) implements Serializable {}
