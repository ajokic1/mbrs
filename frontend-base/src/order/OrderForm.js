import React, { useState, useEffect } from 'react';
import InputField from '../partials/InputField';
import { submitForm } from '../api';
import { Redirect, useRouteMatch } from 'react-router-dom';
import axios from 'axios';

const OrderForm = ({ fetchOrders }) => {
  const { params } = useRouteMatch();
  const isUpdate = params.id ? true : false;

  const [id, setid] = useState('');
  const [orderDate, setorderDate] = useState('');
  const [orderStatus, setorderStatus] = useState('');
  const [shipmentAddress, setshipmentAddress] = useState('');
  const [isSubmitted, setSubmitted] = useState(false);

  useEffect(() => {
    (async () => {
      if(params.id){
        const { data } = await axios('/orders/' + params.id);
        setid(data.id);
        setorderDate(data.orderDate);
        setorderStatus(data.orderStatus);
        setshipmentAddress(data.shipmentAddress);
      }
    })();
  }, [params.id]);

  const onSubmit = event => {
    event.preventDefault();
    const data = {
      id,
      orderDate,
      orderStatus,
      shipmentAddress
    };
    const url = '/orders/' + (isUpdate ? params.id : '');
    submitForm(url, data, () => {
      fetchOrders();
      setSubmitted(true);
    }, isUpdate);
  }

  if (isSubmitted) return <Redirect to="/orders"/>

  return (
    <form className="p-4">
      <InputField name="id" value={id} setter={setid} required />
      <InputField name="orderDate" value={orderDate} setter={setorderDate} required />
      <InputField name="orderStatus" value={orderStatus} setter={setorderStatus} required />
      <InputField name="shipmentAddress" value={shipmentAddress} setter={setshipmentAddress} required />
      <button type="submit" className="btn btn-primary" onClick={onSubmit}>
        Submit
      </button>
    </form>
  );
}

export default OrderForm;