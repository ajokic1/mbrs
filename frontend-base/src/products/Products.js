import React from 'react';
import { useData } from '../api';
import ProductList from './ProductList';
import { Link, Route, useRouteMatch } from 'react-router-dom';
import ProductForm from './ProductForm';

function Products() {
  const { path, url } = useRouteMatch();
  
  const [products, fetchProducts] = useData('/products');

  return (
    <div className="p-3">
      <h1>Products
        <Link to={url + '/add'}>
          <button className="btn btn-primary float-right">Add product</button>
        </Link>        
      </h1>
      <Route exact path={ path }>
        <ProductList products={ products } fetchProducts={ fetchProducts }/>
      </Route>
      <Route path={ path + '/add' }>
        <ProductForm fetchProducts={ fetchProducts }/>
      </Route>
      <Route path={ path + '/edit/:id' }>
        <ProductForm fetchProducts={ fetchProducts }/>
      </Route>
      
    </div>
  );
}

export default Products;