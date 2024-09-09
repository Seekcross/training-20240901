package jp.seekengine.trainingjava.domain;

import jp.seekengine.trainingjava.controller.request.GetDurationRequest;
import jp.seekengine.trainingjava.controller.response.GetDurationResponse;
import jp.seekengine.trainingjava.infrastructure.SampleRepository;
import jp.seekengine.trainingjava.infrastructure.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public GetDurationResponse calculateDuration(GetDurationRequest getDurationRequest) {
        LocalDateTime startTime = LocalDateTime.of(
                getDurationRequest.startTime().year(),
                getDurationRequest.startTime().month(),
                getDurationRequest.startTime().date(),
                getDurationRequest.startTime().hour(),
                getDurationRequest.startTime().minute(),
                getDurationRequest.startTime().second()
        );

        LocalDateTime endTime = LocalDateTime.of(
                getDurationRequest.endTime().year(),
                getDurationRequest.endTime().month(),
                getDurationRequest.endTime().date(),
                getDurationRequest.endTime().hour(),
                getDurationRequest.endTime().minute(),
                getDurationRequest.endTime().second()
        );

        long daysBetween = ChronoUnit.DAYS.between(startTime, endTime);
        long hoursBetween = ChronoUnit.HOURS.between(startTime, endTime) % 24;
        long minutesBetween = ChronoUnit.MINUTES.between(startTime, endTime) % 60;
        long secondsBetween = ChronoUnit.SECONDS.between(startTime, endTime) % 60;

        String duration = String.format("P%dDT%dH%dM%dS", daysBetween, hoursBetween, minutesBetween, secondsBetween);

        return new GetDurationResponse(duration);
    }
}
