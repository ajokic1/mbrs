import React from 'react';
import { useData } from '../api';
import ${entity}List from './${entity}List';
import { Link, Route, useRouteMatch } from 'react-router-dom';
import ${entity}Form from './${entity}Form';

function ${entity}s() {
  const { path, url } = useRouteMatch();
  
  const [${entity?lower_case}s, fetch${entity}s] = useData('/${entity?lower_case}s');

  return (
    <div className="p-3">
      <h1>${entity}s
        <Link to={url + '/add'}>
          <button className="btn btn-primary float-right">Add ${entity?lower_case}</button>
        </Link>        
      </h1>
      <Route exact path={ path }>
        <${entity}List ${entity?lower_case}s={ ${entity?lower_case}s } fetch${entity}s={ fetch${entity}s }/>
      </Route>
      <Route path={ path + '/add' }>
        <${entity}Form fetch${entity}s={ fetch${entity}s }/>
      </Route>
      <Route path={ path + '/edit/:id' }>
        <${entity}Form fetch${entity}s={ fetch${entity}s }/>
      </Route>
      
    </div>
  );
}

export default ${entity}s;