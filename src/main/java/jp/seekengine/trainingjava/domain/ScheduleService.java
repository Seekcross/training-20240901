package jp.seekengine.trainingjava.domain;

import jp.seekengine.trainingjava.controller.request.RegisterScheduleRequest;
import jp.seekengine.trainingjava.infrastructure.SampleRepository;
import jp.seekengine.trainingjava.infrastructure.ScheduleRepository;
import jp.seekengine.trainingjava.infrastructure.entity.MessageEntity;
import jp.seekengine.trainingjava.infrastructure.entity.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final SampleRepository sampleRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(
            SampleRepository sampleRepository,
            ScheduleRepository scheduleRepository
    ) {
        this.sampleRepository = sampleRepository;
        this.scheduleRepository = scheduleRepository;
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

    public ScheduleEntity registerSchedule(RegisterScheduleRequest registerScheduleRequest) {
        ScheduleEntity entity = new ScheduleEntity();
        entity.setScheduleTitle(registerScheduleRequest.scheduleTitle());
        entity.setFromDatetime(registerScheduleRequest.fromDatetime());
        entity.setToDatetime(registerScheduleRequest.toDatetime());
        return scheduleRepository.save(entity);
    }

}
