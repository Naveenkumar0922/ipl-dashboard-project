package com.learning.ipldashboard.batch;

import com.learning.ipldashboard.entity.MatchEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchData, MatchEntity> {
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);
    @Override
    public MatchEntity process(MatchData matchData) throws Exception {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(Long.parseLong(matchData.getId()));
        matchEntity.setCity(matchData.getCity());
        matchEntity.setDate(LocalDate.parse(matchData.getDate()));
        matchEntity.setPlayerOfMatch(matchData.getPlayer_of_match());
        matchEntity.setVenue(matchData.getVenue());
        String firstInningsTeam,secondInningsTeam;
        // Set Team 1 and Team 2 depending on the innings order
        if(matchData.getToss_decision().equalsIgnoreCase("bat")){
            firstInningsTeam = matchData.getToss_winner();
            secondInningsTeam = matchData.getToss_winner().equalsIgnoreCase(matchData.getTeam1()) ?
                    matchData.getTeam2() : matchData.getTeam1();
        } else {
            secondInningsTeam = matchData.getToss_winner();
            firstInningsTeam = matchData.getToss_winner().equalsIgnoreCase(matchData.getTeam1()) ?
                    matchData.getTeam2() : matchData.getTeam1();
        }
        matchEntity.setTeam1(firstInningsTeam);
        matchEntity.setTeam2(secondInningsTeam);
        matchEntity.setTossWinner(matchData.getToss_winner());
        matchEntity.setTossDecision(matchData.getToss_decision());
        matchEntity.setMatchWinner(matchData.getWinner());
        matchEntity.setResult(matchData.getResult());
        matchEntity.setResultMargin(matchData.getResult_margin());
        matchEntity.setUmpire1(matchData.getUmpire1());
        matchEntity.setUmpire2(matchData.getUmpire2());
        return matchEntity;
    }
}
