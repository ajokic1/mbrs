import React, { useState, useEffect } from 'react';
import InputField from '../partials/InputField';
import { submitForm } from '../api';
import { Redirect, useRouteMatch } from 'react-router-dom';
import axios from 'axios';

const LocationForm = ({ fetchLocations }) => {
  const { params } = useRouteMatch();
  const isUpdate = params.id ? true : false;

  const [storeAddress, setstoreAddress] = useState('');
  const [storeState, setstoreState] = useState('');
  const [storeCity, setstoreCity] = useState('');
  const [shipmentAddress, setshipmentAddress] = useState('');
  const [shipmentState, setshipmentState] = useState('');
  const [shipmentCity, setshipmentCity] = useState('');
  const [id, setid] = useState('');
  const [isSubmitted, setSubmitted] = useState(false);

  useEffect(() => {
    (async () => {
      if(params.id){
        const { data } = await axios('/locations/' + params.id);
        setstoreAddress(data.storeAddress);
        setstoreState(data.storeState);
        setstoreCity(data.storeCity);
        setshipmentAddress(data.shipmentAddress);
        setshipmentState(data.shipmentState);
        setshipmentCity(data.shipmentCity);
        setid(data.id);
      }
    })();
  }, [params.id]);

  const onSubmit = event => {
    event.preventDefault();
    const data = {
      storeAddress,
      storeState,
      storeCity,
      shipmentAddress,
      shipmentState,
      shipmentCity,
      id
    };
    const url = '/locations/' + (isUpdate ? params.id : '');
    submitForm(url, data, () => {
      fetchLocations();
      setSubmitted(true);
    }, isUpdate);
  }

  if (isSubmitted) return <Redirect to="/locations"/>

  return (
    <form className="p-4">
      <InputField name="storeAddress" value={storeAddress} setter={setstoreAddress} required />
      <InputField name="storeState" value={storeState} setter={setstoreState} required />
      <InputField name="storeCity" value={storeCity} setter={setstoreCity} required />
      <InputField name="shipmentAddress" value={shipmentAddress} setter={setshipmentAddress} required />
      <InputField name="shipmentState" value={shipmentState} setter={setshipmentState} required />
      <InputField name="shipmentCity" value={shipmentCity} setter={setshipmentCity} required />
      <InputField name="id" value={id} setter={setid} required />
      <button type="submit" className="btn btn-primary" onClick={onSubmit}>
        Submit
      </button>
    </form>
  );
}

export default LocationForm;