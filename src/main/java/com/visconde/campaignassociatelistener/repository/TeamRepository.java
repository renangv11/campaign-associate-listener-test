package com.visconde.campaignassociatelistener.repository;

import com.visconde.campaignassociatelistener.entity.Campaign;
import com.visconde.campaignassociatelistener.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    public Team findByTeamName(String teamName);

    @Query(value = "select t.id_time, t.nome_time, c.id_campanha," +
            " c.nome_campanha, c.data_final, c.data_inicial from time t " +
            "inner join campanha c on (t.id_time = c.id_time) " +
            "where t.nome_time = ?1 and c.data_inicial < ?2 and c.data_final > ?2", nativeQuery = true)
    public Team findByTeamNameAndEffectiveDate(String teamName, LocalDate now);

}
