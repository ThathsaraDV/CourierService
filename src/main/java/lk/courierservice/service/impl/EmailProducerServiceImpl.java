package lk.courierservice.service.impl;

import lk.courierservice.dto.EmailDto;
import lk.courierservice.service.EmailProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/16/2022
 **/
@Service
public class EmailProducerServiceImpl implements EmailProducerService {

    private static final String TOPIC = "email";

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public EmailProducerServiceImpl(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendEmail(EmailDto emailDto) {
        kafkaTemplate.send(TOPIC, emailDto);
    }
}
