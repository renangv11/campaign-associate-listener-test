package com.visconde.campaignassociatelistener.kafka.listener;

import com.visconde.campaignassociatelistener.datacontract.ClubMemberDataContract;
import com.visconde.campaignassociatelistener.service.AssociateService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import static com.visconde.campaignassociatelistener.utils.MapperUtils.desserializer;

@AllArgsConstructor
@Component
public class AssociateClubMemberConsumer {

    private final AssociateService associateService;

    @KafkaListener(topics = "${associateTopic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String payload, Acknowledgment ack) {
        ClubMemberDataContract clubMemberDataContract = desserializer(payload, ClubMemberDataContract.class);
        associateService.associateClubMemberWithCampaigns(clubMemberDataContract);
        ack.acknowledge();
    }

}
