import React from "react";
import { deleteItem } from "../api";
import { Link } from "react-router-dom";

const ${entity}List = ({ ${entity?lower_case}s, fetch${entity}s }) => {
  const onDelete = (event, id) => {
    event.preventDefault();
    deleteItem('/${entity?lower_case}s/' + id, () => {fetch${entity}s()});
  }
  
  const ${entity?lower_case}Rows = ${entity?lower_case}s.map(${entity?lower_case} => (
    <tr key={ ${entity?lower_case}.id }>
      <#list properties as property>
      <td>{ ${entity?lower_case}.${property.name} }</td>
      </#list>
      <td>
        <Link className="btn btn-primary mr-2" to={'/${entity?lower_case}s/edit/' + ${entity?lower_case}.id}>
          Edit
        </Link>
        <button className="btn btn-danger" type="button" onClick={ event => {onDelete(event, ${entity?lower_case}.id)} }>
          Delete
        </button>
      </td>
    </tr>
  ));
  
  return (
    <table className="table">
      <thead>
        <tr>
          <#list properties as property>
          <th scope="col">${property.name}</th>
          </#list>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        { ${entity?lower_case}Rows }
      </tbody>
    </table>
  );
};

export default ${entity}List;
