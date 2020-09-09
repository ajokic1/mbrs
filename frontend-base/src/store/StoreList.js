import React from "react";
import { deleteItem } from "../api";
import { Link } from "react-router-dom";

const StoreList = ({ stores, fetchStores }) => {
  const onDelete = (event, id) => {
    event.preventDefault();
    deleteItem('/stores/' + id, () => {fetchStores()});
  }
  
  const storeRows = stores.map(store => (
    <tr key={ store.id }>
      <td>{ store.id }</td>
      <td>{ store.storeName }</td>
      <td>
        <Link className="btn btn-primary mr-2" to={'/stores/edit/' + store.id}>
          Edit
        </Link>
        <button className="btn btn-danger" type="button" onClick={ event => {onDelete(event, store.id)} }>
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
          <th scope="col">storeName</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        { storeRows }
      </tbody>
    </table>
  );
};

export default StoreList;
