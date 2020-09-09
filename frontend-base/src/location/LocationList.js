import React from "react";
import { deleteItem } from "../api";
import { Link } from "react-router-dom";

const LocationList = ({ locations, fetchLocations }) => {
  const onDelete = (event, id) => {
    event.preventDefault();
    deleteItem('/locations/' + id, () => {fetchLocations()});
  }
  
  const locationRows = locations.map(location => (
    <tr key={ location.id }>
      <td>{ location.storeAddress }</td>
      <td>{ location.storeState }</td>
      <td>{ location.storeCity }</td>
      <td>{ location.shipmentAddress }</td>
      <td>{ location.shipmentState }</td>
      <td>{ location.shipmentCity }</td>
      <td>{ location.id }</td>
      <td>
        <Link className="btn btn-primary mr-2" to={'/locations/edit/' + location.id}>
          Edit
        </Link>
        <button className="btn btn-danger" type="button" onClick={ event => {onDelete(event, location.id)} }>
          Delete
        </button>
      </td>
    </tr>
  ));
  
  return (
    <table className="table">
      <thead>
        <tr>
          <th scope="col">storeAddress</th>
          <th scope="col">storeState</th>
          <th scope="col">storeCity</th>
          <th scope="col">shipmentAddress</th>
          <th scope="col">shipmentState</th>
          <th scope="col">shipmentCity</th>
          <th scope="col">id</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        { locationRows }
      </tbody>
    </table>
  );
};

export default LocationList;
