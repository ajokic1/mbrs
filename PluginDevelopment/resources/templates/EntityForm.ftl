import React, { useState, useEffect } from 'react';
import InputField from '../partials/InputField';
import { submitForm } from '../api';
import { Redirect, useRouteMatch } from 'react-router-dom';
import axios from 'axios';

const ${entity}Form = ({ fetch${entity}s }) => {
  const { params } = useRouteMatch();
  const isUpdate = params.id ? true : false;

  <#list properties as property>
  const [${property.name}, set${property.name}] = useState('');
  </#list>
  const [isSubmitted, setSubmitted] = useState(false);

  useEffect(() => {
    (async () => {
      if(params.id){
        const { data } = await axios('/${entity?lower_case}s/' + params.id);
        <#list properties as property>
        set${property.name}(data.${property.name});
        </#list>
      }
    })();
  }, [params.id]);

  const onSubmit = event => {
    event.preventDefault();
    const data = {
      <#list properties as property>
      ${property.name}<#if property_has_next>,</#if>
      </#list>
    };
    const url = '/${entity?lower_case}s/' + (isUpdate ? params.id : '');
    submitForm(url, data, () => {
      fetch${entity}s();
      setSubmitted(true);
    }, isUpdate);
  }

  if (isSubmitted) return <Redirect to="/${entity?lower_case}s"/>

  return (
    <form className="p-4">
      <#list properties as property>
      <InputField name="${property.name}" value={${property.name}} setter={set${property.name}} required />
      </#list>
      <button type="submit" className="btn btn-primary" onClick={onSubmit}>
        Submit
      </button>
    </form>
  );
}

export default ${entity}Form;