package com.learning.ipldashboard.controller;

import com.learning.ipldashboard.entity.MatchEntity;
import com.learning.ipldashboard.entity.Team;
import com.learning.ipldashboard.repository.MatchRepository;
import com.learning.ipldashboard.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        Iterable<Team> teams = teamRepository.findAll();
        return teams;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeamPage(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName,4));
        return team;
    }
    @GetMapping("/team/{teamName}/matches")
    public List<MatchEntity> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
       LocalDate startDate = LocalDate.of(year,1,1);
       LocalDate endDate = LocalDate.of(year+1,1,1);
//       return matchRepository.findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
//                 teamName,
//                 startDate,
//                 endDate,
//                 teamName,
//                 startDate,
//                 endDate
//        );
        return matchRepository.findMatchesByTeamForYear(teamName,startDate,endDate);
    }
}
