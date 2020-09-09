import React from 'react';
import './App.css';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Navbar from './partials/Navbar';
import Stores from './store/Store';
import Products from './product/Product';
import Locations from './location/Location';
import Orderrs from './orderr/Orderr';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar/>
        <Switch>
          <Route path="/stores">
            <Stores/>
          </Route>
          <Route path="/products">
            <Products/>
          </Route>
          <Route path="/locations">
            <Locations/>
          </Route>
          <Route path="/orderrs">
            <Orderrs/>
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;