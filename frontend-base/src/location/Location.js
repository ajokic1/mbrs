import React from 'react';
import { useData } from '../api';
import LocationList from './LocationList';
import { Link, Route, useRouteMatch } from 'react-router-dom';
import LocationForm from './LocationForm';

function Locations() {
  const { path, url } = useRouteMatch();
  
  const [locations, fetchLocations] = useData('/locations');

  return (
    <div className="p-3">
      <h1>Locations
        <Link to={url + '/add'}>
          <button className="btn btn-primary float-right">Add location</button>
        </Link>        
      </h1>
      <Route exact path={ path }>
        <LocationList locations={ locations } fetchLocations={ fetchLocations }/>
      </Route>
      <Route path={ path + '/add' }>
        <LocationForm fetchLocations={ fetchLocations }/>
      </Route>
      <Route path={ path + '/edit/:id' }>
        <LocationForm fetchLocations={ fetchLocations }/>
      </Route>
      
    </div>
  );
}

export default Locations;