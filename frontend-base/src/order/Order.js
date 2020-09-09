import React from 'react';
import { useData } from '../api';
import OrderList from './OrderList';
import { Link, Route, useRouteMatch } from 'react-router-dom';
import OrderForm from './OrderForm';

function Orders() {
  const { path, url } = useRouteMatch();
  
  const [orders, fetchOrders] = useData('/orders');

  return (
    <div className="p-3">
      <h1>Orders
        <Link to={url + '/add'}>
          <button className="btn btn-primary float-right">Add order</button>
        </Link>        
      </h1>
      <Route exact path={ path }>
        <OrderList orders={ orders } fetchOrders={ fetchOrders }/>
      </Route>
      <Route path={ path + '/add' }>
        <OrderForm fetchOrders={ fetchOrders }/>
      </Route>
      <Route path={ path + '/edit/:id' }>
        <OrderForm fetchOrders={ fetchOrders }/>
      </Route>
      
    </div>
  );
}

export default Orders;