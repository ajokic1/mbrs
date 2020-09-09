import React from "react";
import { deleteItem } from "../api";
import { Link } from "react-router-dom";

const OrderList = ({ orders, fetchOrders }) => {
  const onDelete = (event, id) => {
    event.preventDefault();
    deleteItem('/orders/' + id, () => {fetchOrders()});
  }
  
  const orderRows = orders.map(order => (
    <tr key={ order.id }>
      <th scope='row'>{ order.id }</th>
      <td>{ order.id }</td>
      <td>{ order.orderDate }</td>
      <td>{ order.orderStatus }</td>
      <td>{ order.shipmentAddress }</td>
      <td>
        <Link className="btn btn-primary mr-2" to={'/orders/edit/' + order.id}>
          Edit
        </Link>
        <button className="btn btn-danger" type="button" onClick={ event => {onDelete(event, order.id)} }>
          Delete
        </button>
      </td>
    </tr>
  ));
  
  return (
    <table className="table">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">id</th>
          <th scope="col">orderDate</th>
          <th scope="col">orderStatus</th>
          <th scope="col">shipmentAddress</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        { orderRows }
      </tbody>
    </table>
  );
};

export default OrderList;
