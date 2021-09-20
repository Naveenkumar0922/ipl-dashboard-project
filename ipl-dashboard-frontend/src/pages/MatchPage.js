import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { LatestMatchDetailCard } from "../components/LatestMatchDetailCard";
import { SelectYearCard } from "../components/SelectYearCard";
import "./MatchPage.scss";

export const MatchPage = () => {
    const [matches, setMatches] = useState([]);
    const {teamName,year} = useParams();
    useEffect(() => {
        const fetchMatchesByYear = async () => {
            const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}/matches?year=${year}`);
            const fetchData = await response.json();
            console.log(fetchData);
            setMatches(fetchData);
        }
        fetchMatchesByYear();
     },[teamName, year]);
  return (
    <div className="MatchPage">
      <div className="year-select">
        <h3 className="header-match">Select Year</h3>
        <SelectYearCard teamName={teamName}/>
      </div>
      <div>
      <h1 className="header-match">{teamName} matches in {year}</h1>
      {matches.map(match => <LatestMatchDetailCard key={match.id} match={match} teamName={teamName}/>)}
      </div>
    </div>
  );
}