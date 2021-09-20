import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { LatestMatchDetailCard } from "../components/LatestMatchDetailCard";
import { LatestMatchSmallCard } from "../components/LatestMatchSmallCard";
import "./TeamPage.scss";
import { PieChart } from 'react-minimal-pie-chart';

export const TeamPage = () => {

const [team ,setTeam ] = useState({matches : []});
const { teamName } = useParams();

 useEffect(() => {
    const fetchMatches = async () => {
        const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}`);
        const fetchData = await response.json();
        console.log(fetchData);
        setTeam(fetchData);
    }
    fetchMatches();
 },[teamName]);
 if(!team || !team.teamName){
   return <h2>Team Not Found Kindly Pass the Correct Team Name</h2>;
 }
  return (
    <div className="TeamPage">
      <div className="team-name-section">
        <h1 className="team-name">{team.teamName}</h1>
      </div>
      <div className="win-loss-section">
        Wins/Losses
        <PieChart
          data={[
            { title: 'Wins', value: team.totalWins, color: '#9FE2BF' },
            { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#900C3F' },
          ]}
        />
      </div>
      <div className="match-detail-section">
      <h3 className="head-style">Latest Matches</h3>
        <LatestMatchDetailCard match={team.matches[0]} teamName={team.teamName}/>
      </div>
      {team.matches.slice(1).map(match => <LatestMatchSmallCard key={match.id} match={match} teamName={team.teamName}/>)}
      <div className="more-link">
        <Link to={`/team/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More Info {'>'}</Link>
      </div>    
    </div>
  );
}

