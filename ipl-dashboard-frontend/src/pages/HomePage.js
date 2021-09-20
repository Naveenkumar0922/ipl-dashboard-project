import React, { useEffect, useState } from "react";
import { TeamNameList } from "../components/TeamNameList";
import "./HomePage.scss";

export const HomePage = () => {
    const [teams, setTeams] = useState([]);
    useEffect(() => {
        const fetchAllTeams = async () => {
            const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/teams`);
            const fetchData = await response.json();
            console.log(fetchData);
            setTeams(fetchData);
        }
        fetchAllTeams();
     },[]);
    
   return (
       <div className="HomePage">
           <div className="header-section">
                <h1 className="application-name">IPL DASHBOARD</h1>
           </div>
           <div className="team-list">
                {teams.map(team => <TeamNameList key={team.id} teamName={team.teamName}/> )}
           </div>
       </div>
   )
}