package com.visconde.campaignassociatelistener.converter;

import com.visconde.campaignassociatelistener.datacontract.ClubMemberDataContract;
import com.visconde.campaignassociatelistener.entity.ClubMember;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class ClubMemberConverter {
    public ClubMember convertDataContractToEntity(ClubMemberDataContract clubMemberDataContract){
        ClubMember clubMember = new ClubMember();
        copyProperties(clubMemberDataContract, clubMember);
        return clubMember;
    }
}
