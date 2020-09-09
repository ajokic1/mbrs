import React, { useState, useEffect } from 'react';
import InputField from '../partials/InputField';
import { submitForm } from '../api';
import { Redirect, useRouteMatch } from 'react-router-dom';
import axios from 'axios';

const StoreForm = ({ fetchStores }) => {
  const { params } = useRouteMatch();
  const isUpdate = params.id ? true : false;

  const [id, setid] = useState('');
  const [storeName, setstoreName] = useState('');
  const [isSubmitted, setSubmitted] = useState(false);

  useEffect(() => {
    (async () => {
      if(params.id){
        const { data } = await axios('/stores/' + params.id);
        setid(data.id);
        setstoreName(data.storeName);
      }
    })();
  }, [params.id]);

  const onSubmit = event => {
    event.preventDefault();
    const data = {
      id,
      storeName
    };
    const url = '/stores/' + (isUpdate ? params.id : '');
    submitForm(url, data, () => {
      fetchStores();
      setSubmitted(true);
    }, isUpdate);
  }

  if (isSubmitted) return <Redirect to="/stores"/>

  return (
    <form className="p-4">
      <InputField name="id" value={id} setter={setid} required />
      <InputField name="storeName" value={storeName} setter={setstoreName} required />
      <button type="submit" className="btn btn-primary" onClick={onSubmit}>
        Submit
      </button>
    </form>
  );
}

export default StoreForm;