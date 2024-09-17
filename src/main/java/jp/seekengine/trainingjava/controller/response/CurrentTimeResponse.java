package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;

public record CurrentTimeResponse(
        String currentTime
) implements Serializable {}
