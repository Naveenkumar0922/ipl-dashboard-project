import React from "react";
import {Link} from "react-router-dom";
import "./LatestMatchSmallCard.scss";

export const LatestMatchSmallCard = ({match,teamName}) => {
if (!match) return null;
const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;
const opponentTeamLink = `/team/${opponentTeam}`;
const isMatchWinStyle = teamName === match.matchWinner;
  return (
    <div className={isMatchWinStyle ? 'LatestMatchSmallCard won-card' : 'LatestMatchSmallCard lost-card'}>
      <span className="vs">vs</span>
      <h2><Link to={opponentTeamLink}>{opponentTeam}</Link></h2>
      <h3 className="match-result">{match.matchWinner} won by {match.resultMargin} {match.result}</h3>
    </div>
  );
}