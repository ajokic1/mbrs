import React from "react";
import { deleteItem } from "../api";
import { Link } from "react-router-dom";

const OrderrList = ({ orderrs, fetchOrderrs }) => {
  const onDelete = (event, id) => {
    event.preventDefault();
    deleteItem('/orderrs/' + id, () => {fetchOrderrs()});
  }
  
  const orderrRows = orderrs.map(orderr => (
    <tr key={ orderr.id }>
      <td>{ orderr.id }</td>
      <td>{ orderr.orderDate }</td>
      <td>{ orderr.orderStatus }</td>
      <td>{ orderr.shipmentAddress }</td>
      <td>
        <Link className="btn btn-primary mr-2" to={'/orderrs/edit/' + orderr.id}>
          Edit
        </Link>
        <button className="btn btn-danger" type="button" onClick={ event => {onDelete(event, orderr.id)} }>
          Delete
        </button>
      </td>
    </tr>
  ));
  
  return (
    <table className="table">
      <thead>
        <tr>
          <th scope="col">id</th>
          <th scope="col">orderDate</th>
          <th scope="col">orderStatus</th>
          <th scope="col">shipmentAddress</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        { orderrRows }
      </tbody>
    </table>
  );
};

export default OrderrList;
