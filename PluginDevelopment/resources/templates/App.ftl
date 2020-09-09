import React from 'react';
import './App.css';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Navbar from './partials/Navbar';
<#list entities as entity>
import ${entity.name}s from './${entity.name?lower_case}/${entity.name}';
</#list>

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar/>
        <Switch>
          <#list entities as entity>
          <Route path="/${entity.name?lower_case}s">
            <${entity.name}s/>
          </Route>
          </#list>
        </Switch>
      </Router>
    </div>
  );
}

export default App;