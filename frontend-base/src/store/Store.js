import React from 'react';
import { useData } from '../api';
import StoreList from './StoreList';
import { Link, Route, useRouteMatch } from 'react-router-dom';
import StoreForm from './StoreForm';

function Stores() {
  const { path, url } = useRouteMatch();
  
  const [stores, fetchStores] = useData('/stores');

  return (
    <div className="p-3">
      <h1>Stores
        <Link to={url + '/add'}>
          <button className="btn btn-primary float-right">Add store</button>
        </Link>        
      </h1>
      <Route exact path={ path }>
        <StoreList stores={ stores } fetchStores={ fetchStores }/>
      </Route>
      <Route path={ path + '/add' }>
        <StoreForm fetchStores={ fetchStores }/>
      </Route>
      <Route path={ path + '/edit/:id' }>
        <StoreForm fetchStores={ fetchStores }/>
      </Route>
      
    </div>
  );
}

export default Stores;