package com.visconde.campaignassociatelistener.service.imp;

import com.visconde.campaignassociatelistener.converter.ClubMemberConverter;
import com.visconde.campaignassociatelistener.datacontract.ClubMemberDataContract;
import com.visconde.campaignassociatelistener.entity.Campaign;
import com.visconde.campaignassociatelistener.entity.ClubMember;
import com.visconde.campaignassociatelistener.entity.Team;
import com.visconde.campaignassociatelistener.repository.ClubMemberRepository;
import com.visconde.campaignassociatelistener.repository.TeamRepository;
import com.visconde.campaignassociatelistener.service.AssociateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AssociateServiceImp implements AssociateService {

    private final ClubMemberRepository clubMemberRepository;
    private final TeamRepository teamRepository;
    private final ClubMemberConverter clubMemberConverter;

    @Override
    public void associateClubMemberWithCampaigns(ClubMemberDataContract clubMemberDataContract) {
        Optional<ClubMember> optionalClubMember = clubMemberRepository.findByClubMemberId(clubMemberDataContract.getClubMemberId());
        Team team = teamRepository.findByTeamNameAndEffectiveDate(clubMemberDataContract.getClubMemberTeam(), LocalDate.now());
        if(optionalClubMember.isPresent()){
            List<Campaign> newCampaignsForClubMember = getNewCampaignsForClubMember(optionalClubMember.get().getCampaigns(), team.getCampaigns());
            optionalClubMember.get().getCampaigns().addAll(newCampaignsForClubMember);
            clubMemberRepository.save(optionalClubMember.get());
        } else {
            ClubMember clubMember = clubMemberConverter.convertDataContractToEntity(clubMemberDataContract);
            clubMember.setCampaigns(team.getCampaigns());
            clubMemberRepository.save(clubMember);
        }
    }

    private List<Campaign>  getNewCampaignsForClubMember(List<Campaign> clubMemberCampaigns, List<Campaign> actualCampaigns) {
        List<Campaign> newCampaignsForClubMember = new ArrayList<>(actualCampaigns);
        newCampaignsForClubMember.removeAll(clubMemberCampaigns);
        return newCampaignsForClubMember;
    }

}
