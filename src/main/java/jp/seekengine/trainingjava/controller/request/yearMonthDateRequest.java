package jp.seekengine.trainingjava.controller.request;

import java.io.Serializable;

public record yearMonthDateRequest(
        Integer year,
        Integer month,
        Integer date,
        Integer hour,
        Integer minute,
        Integer second
) implements Serializable {}
