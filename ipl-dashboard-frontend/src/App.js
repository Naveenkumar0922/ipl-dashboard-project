import './App.scss';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { TeamPage } from './pages/TeamPage';
import { MatchPage } from './pages/MatchPage';
import { HomePage } from './pages/HomePage';

function App() {
  return (
    <div className="App">
      <Router>
        <Route exact path="/team/:teamName">
          <TeamPage/>
        </Route>
        <Route path="/team/:teamName/matches/:year">
          <MatchPage/>
        </Route>
        <Route exact path="/">
          <HomePage/>
        </Route>
      </Router>
    </div>
  );
}

export default App;
