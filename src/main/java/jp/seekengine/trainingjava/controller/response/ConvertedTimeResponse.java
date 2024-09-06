package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;

public record ConvertedTimeResponse(
        String convertedTimes
) implements Serializable {}
