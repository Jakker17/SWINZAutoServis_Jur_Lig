import './App.css';
import ReservationComponent from './components/ReservationComponent';
import Navbar from './components/Navbar';
import NewReservationComponent from './components/NewReservationComponent';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
          <Route path="/Reservations" component={ReservationComponent}>
              <ReservationComponent />
          </Route>
          <Route path="/NewReservation" component={NewReservationComponent}>
              <NewReservationComponent />
          </Route>
    </Router>
    </div>
  );
}

export default App;
