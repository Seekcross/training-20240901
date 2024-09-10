package jp.seekengine.trainingjava.controller;

import jp.seekengine.trainingjava.controller.request.CalculateEndTimeRequest;
import jp.seekengine.trainingjava.controller.request.ConvertTimesRequest;
import jp.seekengine.trainingjava.controller.request.MessageRequest;
import jp.seekengine.trainingjava.controller.request.SampleRequest;
import jp.seekengine.trainingjava.controller.response.CalculateEndTimeResponse;
import jp.seekengine.trainingjava.controller.response.ConvertTimesResponse;
import jp.seekengine.trainingjava.controller.request.YearMonthDateRequest;
import jp.seekengine.trainingjava.controller.response.ConvertTimeResponse;
import jp.seekengine.trainingjava.controller.response.SampleResponse;
import jp.seekengine.trainingjava.domain.ScheduleService;
import jp.seekengine.trainingjava.infrastructure.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/sample")
    public SampleResponse sample(@RequestBody SampleRequest sampleRequest) {
        String message = scheduleService.createSampleMessage(sampleRequest.sampleField1(), sampleRequest.sampleField2());
        return new SampleResponse(message);
    }

    @PostMapping("/message")
    public MessageEntity sample(@RequestBody MessageRequest message) {
        return scheduleService.createMessage(message.message());
    }

    @GetMapping("/messages/{id}")
    public MessageEntity samples(@PathVariable Integer id) {
        return scheduleService.getMessageById(id);
    }

    @GetMapping("/messages/search")
    public List<MessageEntity> sampleSearch(@RequestParam String message) {
        return scheduleService.searchMessage(message);
    }

    @GetMapping("/times/current/convert")
    public ConvertTimeResponse convertTime(@RequestBody YearMonthDateRequest yearMonthDateRequest) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        ZonedDateTime requestTime =
                ZonedDateTime.of(
                        yearMonthDateRequest.year(),
                        yearMonthDateRequest.month(),
                        yearMonthDateRequest.date(),
                        yearMonthDateRequest.hour(),
                        yearMonthDateRequest.minute(),
                        yearMonthDateRequest.second(),
                        0,
                        ZoneId.of(yearMonthDateRequest.requestTimeZoneId())
                );
        var responseTimeZoneId = ZoneId.of(yearMonthDateRequest.responseTimeZoneId());
        var convertedTime = requestTime.withZoneSameInstant(responseTimeZoneId);

        return new ConvertTimeResponse(formatter.format(convertedTime));
    }

    @GetMapping("/times/convert")
    public ConvertTimesResponse convertTimes(@RequestBody ConvertTimesRequest convertTimeRequest) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        List<String> convertedTimes = convertTimeRequest.times().stream().map(it ->
                ZonedDateTime.of(
                        it.year(),
                        it.month(),
                        it.date(),
                        it.hour(),
                        it.minute(),
                        it.second(),
                        0,
                        ZoneId.of(it.requestTimeZoneId())
                ).withZoneSameInstant(ZoneId.of(it.responseTimeZoneId())).format(formatter)
        ).toList();

        return new ConvertTimesResponse(convertedTimes);
    }

    @GetMapping("times/calculate/end")
    public CalculateEndTimeResponse calculateEndTime(@RequestBody CalculateEndTimeRequest calculateEndTimeRequest) {
        return new CalculateEndTimeResponse(scheduleService.calculateEndTime(calculateEndTimeRequest));
    }
}
