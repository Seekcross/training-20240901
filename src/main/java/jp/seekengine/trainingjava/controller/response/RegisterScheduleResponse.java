package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;

public record RegisterScheduleResponse(
        int scheduleId
) implements Serializable {}
