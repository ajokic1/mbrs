import React from 'react';
import { useData } from '../api';
import OrderrList from './OrderrList';
import { Link, Route, useRouteMatch } from 'react-router-dom';
import OrderrForm from './OrderrForm';

function Orderrs() {
  const { path, url } = useRouteMatch();
  
  const [orderrs, fetchOrderrs] = useData('/orderrs');

  return (
    <div className="p-3">
      <h1>Orderrs
        <Link to={url + '/add'}>
          <button className="btn btn-primary float-right">Add orderr</button>
        </Link>        
      </h1>
      <Route exact path={ path }>
        <OrderrList orderrs={ orderrs } fetchOrderrs={ fetchOrderrs }/>
      </Route>
      <Route path={ path + '/add' }>
        <OrderrForm fetchOrderrs={ fetchOrderrs }/>
      </Route>
      <Route path={ path + '/edit/:id' }>
        <OrderrForm fetchOrderrs={ fetchOrderrs }/>
      </Route>
      
    </div>
  );
}

export default Orderrs;