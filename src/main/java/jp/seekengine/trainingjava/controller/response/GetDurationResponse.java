package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;

public record GetDurationResponse(
        String duration
) implements Serializable {}
