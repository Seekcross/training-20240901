package jp.seekengine.trainingjava.controller;

import jp.seekengine.trainingjava.controller.request.ConvertTimesRequest;
import jp.seekengine.trainingjava.controller.request.MessageRequest;
import jp.seekengine.trainingjava.controller.request.SampleRequest;
import jp.seekengine.trainingjava.controller.response.ConvertTimesResponse;
import jp.seekengine.trainingjava.controller.request.YearMonthDateRequest;
import jp.seekengine.trainingjava.controller.response.ConvertTimeResponse;
import jp.seekengine.trainingjava.controller.response.SampleResponse;
import jp.seekengine.trainingjava.domain.ScheduleService;
import jp.seekengine.trainingjava.infrastructure.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
        String convertedTime = formatter.format(
                OffsetDateTime.of(
                        yearMonthDateRequest.year(),
                        yearMonthDateRequest.month(),
                        yearMonthDateRequest.date(),
                        yearMonthDateRequest.hour(),
                        yearMonthDateRequest.minute(),
                        yearMonthDateRequest.second(),
                        0,
                        ZoneOffset.ofHours(9)
                )
        );
        return new ConvertTimeResponse(convertedTime);
    }

    @GetMapping("/times/convert")
    public ConvertTimesResponse convertTimes(@RequestBody ConvertTimesRequest convertTimeRequest) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        List<String> convertedTime = convertTimeRequest.times().stream().map(it ->
                OffsetDateTime.of(
                        it.year(),
                        it.month(),
                        it.date(),
                        it.hour(),
                        it.minute(),
                        it.second(),
                        0,
                        ZoneOffset.ofHours(9)
                ).format(formatter)).toList();

        return new ConvertTimesResponse(convertedTime);
    }
}
