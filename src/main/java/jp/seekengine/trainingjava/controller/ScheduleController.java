package jp.seekengine.trainingjava.controller;

import jp.seekengine.trainingjava.controller.request.MessageRequest;
import jp.seekengine.trainingjava.controller.request.SampleRequest;
import jp.seekengine.trainingjava.controller.request.yearMonthDateRequest;
import jp.seekengine.trainingjava.controller.response.ConvertedTimeResponse;
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
    public ConvertedTimeResponse convertTime(@RequestBody yearMonthDateRequest convertTimeRequest) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        String convertedTime = formatter.format(
                OffsetDateTime.of(
                    convertTimeRequest.year(),
                    convertTimeRequest.month(),
                    convertTimeRequest.date(),
                    convertTimeRequest.hour(),
                    convertTimeRequest.minute(),
                    convertTimeRequest.second(),
                    0,
                    ZoneOffset.ofHours(9)
                )
        );
        return new ConvertedTimeResponse(convertedTime);
    }
}
