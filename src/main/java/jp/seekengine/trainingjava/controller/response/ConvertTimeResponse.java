package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;

public record ConvertTimeResponse(
        String convertedTimes
) implements Serializable {}
