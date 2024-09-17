package jp.seekengine.trainingjava.domain;

import jp.seekengine.trainingjava.controller.request.CalculateEndTimeRequest;
import jp.seekengine.trainingjava.infrastructure.SampleRepository;
import jp.seekengine.trainingjava.infrastructure.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ScheduleService {

    private final SampleRepository sampleRepository;

    @Autowired
    public ScheduleService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public String createSampleMessage(String message1, String message2) {
        return "Messageとして「%s」と「%s」を受け取りました。".formatted(message1, message2);
    }

    public MessageEntity createMessage(String message) {
        MessageEntity entity = new MessageEntity();
        entity.setMessage(message);
        return sampleRepository.save(entity);
    }

    public MessageEntity getMessageById(Integer id) {
        return sampleRepository.findById(id).get();
    }

    public List<MessageEntity> searchMessage(String message) {
        return sampleRepository.findByMessageContaining(message);
    }

    public String calculateEndTime(CalculateEndTimeRequest request) {
        ZonedDateTime startTime = ZonedDateTime.of(
                request.startTime().year(),
                request.startTime().month(),
                request.startTime().date(),
                request.startTime().hour(),
                request.startTime().minute(),
                request.startTime().second(),
                0,
                ZoneId.of(request.startTime().requestTimeZoneId())
        ).withZoneSameInstant(ZoneId.of(request.startTime().responseTimeZoneId()));

        startTime = startTime
                .plusHours(request.duration().hour())
                .plusMinutes(request.duration().minute())
                .plusSeconds(request.duration().second());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ssXXX");

        return formatter.format(startTime);
    }
}
