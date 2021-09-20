import React from "react";
import { Link } from "react-router-dom";
import "./TeamNameList.scss";

export const TeamNameList = ({teamName}) => {
    return(
        <div className="TeamNameList">
            <h2>
               <Link to={`/team/${teamName}`}>
                   {teamName}
               </Link>
                </h2>
        </div>
    )
}