package com.learning.ipldashboard.repository;

import com.learning.ipldashboard.entity.MatchEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity,Long> {
    List<MatchEntity> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    //Using jpql Query
    @Query("select m from MatchEntity m where (m.team1 = :teamName or m.team2 = :teamName) and " +
            "m.date between :startDate and :endDate order by date desc")
    List<MatchEntity> findMatchesByTeamForYear(
           @Param("teamName") String teamName,
           @Param("startDate") LocalDate startDate,
           @Param("endDate") LocalDate endDate);

    // using JPA method
    List<MatchEntity> findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
            String teamName1,
            LocalDate startDate1,
            LocalDate endDate1,
            String teamName2,
            LocalDate startDate2,
            LocalDate endDate2
    );

    default List<MatchEntity> findLatestMatchesByTeam(String teamName,int matchCount) {
        return findByTeam1OrTeam2OrderByDateDesc(teamName,teamName, PageRequest.of(0,matchCount));
    }
}
