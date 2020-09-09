import React, { useState, useEffect } from 'react';
import InputField from '../partials/InputField';
import { submitForm } from '../api';
import { Redirect, useRouteMatch } from 'react-router-dom';
import axios from 'axios';

const ProductForm = ({ fetchProducts }) => {
  const { params } = useRouteMatch();
  const isUpdate = params.id ? true : false;
  
  const [productCategory, setProductCategory] = useState('');
  const [productName, setProductName] = useState('');
  const [isSubmitted, setSubmitted] = useState(false);

  useEffect(() => {
    (async () => {
      if(params.id){
        const { data } = await axios('/products/' + params.id);
        setProductName(data.productName);
        setProductCategory(data.productCategory);
      }
    })();
  }, [params.id]);

  const onSubmit = event => {
    event.preventDefault();
    const data = { productCategory, productName };
    const url = '/products/' + (isUpdate ? params.id : '');
    submitForm(url, data, () => {
      fetchProducts();
      setSubmitted(true);
    }, isUpdate);
  }  

  if (isSubmitted) return <Redirect to="/products"/>

  return ( 
    <form className="p-4">
      <InputField name="productCategory" value={productCategory} setter={setProductCategory} required />
      <InputField name="productName" value={productName} setter={setProductName} required />
      <button type="submit" className="btn btn-primary" onClick={onSubmit}>
        Submit
      </button>
    </form>
  );
}
 
export default ProductForm;