package com.visconde.campaignassociatelistener.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "time")
@NamedEntityGraph(name = "Team.detail",
        attributeNodes = @NamedAttributeNode("campaigns"))
public class Team {

    @Id
    @Column(name = "id_time")
    private Long teamId;

    @Column(name = "nome_time", unique = true)
    private String teamName;

    @OneToMany
    @JoinColumn(name = "id_time")
    private List<Campaign> campaigns;

}
