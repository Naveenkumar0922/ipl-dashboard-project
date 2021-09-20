import React from "react";
import {Link} from "react-router-dom";
import "./LatestMatchDetailCard.scss";

export const LatestMatchDetailCard = ({match,teamName}) => {
if (!match) return null;
const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;
const opponentTeamLink = `/team/${opponentTeam}`;
const isMatchWinStyle = teamName === match.matchWinner;
  return (
    <div className={isMatchWinStyle ? 'LatestMatchDetailCard won-card' : 'LatestMatchDetailCard lost-card'}>
      <div>
        <span className="vs">vs</span>
        <h1><Link to={opponentTeamLink}>{opponentTeam}</Link></h1>
        <h2 className="match-main-detail">{match.date}</h2>
        <h3 className="match-main-detail">at {match.venue}</h3>
        <h3 className="match-main-detail">{match.matchWinner} won by {match.resultMargin} {match.result}</h3>
      </div>
      <div className="innings-detail">
        <h3>First Innings</h3>
        <p>{match.team1}</p>
        <h3>Second Innings</h3>
        <p>{match.team2}</p>
        <h3>Man of the Match</h3>
        <p>{match.playerOfMatch}</p>
        <h3>Umpires</h3>
        <p>{match.umpire1}, {match.umpire2}</p>
      </div>
    </div>
  );
}
