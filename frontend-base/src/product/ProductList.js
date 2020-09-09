import React from "react";
import { deleteItem } from "../api";
import { Link } from "react-router-dom";

const ProductList = ({ products, fetchProducts }) => {
  const onDelete = (event, id) => {
    event.preventDefault();
    deleteItem('/products/' + id, () => {fetchProducts()});
  }
  
  const productRows = products.map(product => (
    <tr key={ product.id }>
      <td>{ product.id }</td>
      <td>{ product.productName }</td>
      <td>{ product.productCategory }</td>
      <td>
        <Link className="btn btn-primary mr-2" to={'/products/edit/' + product.id}>
          Edit
        </Link>
        <button className="btn btn-danger" type="button" onClick={ event => {onDelete(event, product.id)} }>
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
          <th scope="col">productName</th>
          <th scope="col">productCategory</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        { productRows }
      </tbody>
    </table>
  );
};

export default ProductList;
