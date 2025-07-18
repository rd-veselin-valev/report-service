package com.example.report_service.service.kafka;

import com.example.report_service.data.repository.MissionRepository;
import com.example.report_service.dto.mission.MissionMessageDto;
import com.example.report_service.mapper.MissionMapper;
import com.example.report_service.message.Message;
import com.example.report_service.message.enums.ActionType;
import com.example.report_service.service.MessageValidatorService;
import com.example.report_service.util.kafka.KafkaTopics;
import com.example.report_service.util.message.ErrorMessages;
import com.example.report_service.util.message.InfoMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionConsumer {
    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;
    private final ObjectMapper objectMapper;
    private final MessageValidatorService messageValidatorService;

    @KafkaListener(topics = KafkaTopics.INVENTORY_MISSION_TOPIC, groupId = "${kafka.consumer.inventory.group-id}")
    public void listen(ConsumerRecord<String, String> record) {
        log.info(InfoMessages.CONSUMING_MESSAGE, record.key(), record.value());

        try {
            messageValidatorService.validate(KafkaTopics.INVENTORY_MISSION_TOPIC, record.value());
            var message = objectMapper.readValue(record.value(), Message.class);
            var missionMessageDto = objectMapper.convertValue(message.getPayload(), MissionMessageDto.class);
            var actionType = message.getActionType();

            if (Objects.requireNonNull(actionType) == ActionType.CREATE) {
                createMission(missionMessageDto);
            } else {
                throw new IllegalArgumentException(String.format(ErrorMessages.UNEXPECTED_VALUE, actionType));
            }
        } catch (Exception e) {
            log.error(ErrorMessages.CONSUMER_ERROR, e);
        }
    }

    private void createMission(MissionMessageDto missionMessageDto) {
        var mission = missionMapper.toMission(missionMessageDto);
        missionRepository.save(mission);
    }
}

