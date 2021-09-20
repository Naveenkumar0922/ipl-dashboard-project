import React from "react";
import { Link } from "react-router-dom";
import "./SelectYearCard.scss";

export const SelectYearCard = ({teamName}) => {
    let yearsList = [];
    const startYear = process.env.REACT_APP_DATA_START_YEAR;
    const endYear = process.env.REACT_APP_DATA_END_YEAR;

    for (let i = startYear; i <= endYear; i++) {
        yearsList.push(i);
    }
    return (
        <ol className="SelectYearCard">
            { yearsList.map(year => (
                <li key={year}>
                    <Link to={`/team/${teamName}/matches/${year}`}>{year}</Link>
                </li>
            )) }
        </ol>
    )

}