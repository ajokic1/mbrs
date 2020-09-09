import React, { useState, useEffect } from 'react';
import InputField from '../partials/InputField';
import { submitForm } from '../api';
import { Redirect, useRouteMatch } from 'react-router-dom';
import axios from 'axios';

const ProductForm = ({ fetchProducts }) => {
  const { params } = useRouteMatch();
  const isUpdate = params.id ? true : false;

  const [id, setid] = useState('');
  const [productName, setproductName] = useState('');
  const [productCategory, setproductCategory] = useState('');
  const [isSubmitted, setSubmitted] = useState(false);

  useEffect(() => {
    (async () => {
      if(params.id){
        const { data } = await axios('/products/' + params.id);
        setid(data.id);
        setproductName(data.productName);
        setproductCategory(data.productCategory);
      }
    })();
  }, [params.id]);

  const onSubmit = event => {
    event.preventDefault();
    const data = {
      id,
      productName,
      productCategory
    };
    const url = '/products/' + (isUpdate ? params.id : '');
    submitForm(url, data, () => {
      fetchProducts();
      setSubmitted(true);
    }, isUpdate);
  }

  if (isSubmitted) return <Redirect to="/products"/>

  return (
    <form className="p-4">
      <InputField name="id" value={id} setter={setid} required />
      <InputField name="productName" value={productName} setter={setproductName} required />
      <InputField name="productCategory" value={productCategory} setter={setproductCategory} required />
      <button type="submit" className="btn btn-primary" onClick={onSubmit}>
        Submit
      </button>
    </form>
  );
}

export default ProductForm;