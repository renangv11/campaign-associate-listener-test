package com.visconde.campaignassociatelistener.repository;

import com.visconde.campaignassociatelistener.entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

    Optional<ClubMember> findByClubMemberId(Long clubMemberId);

}
