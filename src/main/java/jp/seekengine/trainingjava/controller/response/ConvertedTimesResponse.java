package jp.seekengine.trainingjava.controller.response;

import java.io.Serializable;
import java.util.List;

public record ConvertedTimesResponse(
        List<String> convertedTimes
) implements Serializable {}
