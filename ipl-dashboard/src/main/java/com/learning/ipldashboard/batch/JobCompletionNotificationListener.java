package com.learning.ipldashboard.batch;

import com.learning.ipldashboard.entity.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
        }
        Map<String,Team> teamData = new HashMap<>();
        // get distinct each team details from match table
        entityManager.createQuery("select m.team1, count(*) from MatchEntity m group by m.team1",Object[].class)
                .getResultList()
                .stream()
                .map(value -> new Team(value[0].toString(),(long)value[1]))
                .forEach(team -> teamData.put(team.getTeamName(), team));

        // set total matches from match table using teamData map also
        entityManager.createQuery("select m.team2, count(*) from MatchEntity m group by m.team2",Object[].class)
                .getResultList()
                .stream()
                .forEach(value -> {
                    Team team = teamData.get(value[0].toString());
                    team.setTotalMatches(team.getTotalMatches() + (long) value[1]);
                });
        // set total wins from create query using teamData
        entityManager.createQuery("select m.matchWinner, count(*) from MatchEntity m group by m.matchWinner",Object[].class)
                .getResultList()
                .stream()
                .forEach(value -> {
                    Team team = teamData.get(value[0].toString());
                    if(team != null) team.setTotalWins((long) value[1]);
                });
        teamData.values().forEach(team -> entityManager.persist(team));
        teamData.values().forEach(team -> System.out.println("teamData="+team));
    }
}
