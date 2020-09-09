import React from "react";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark text-light">
      <div className="container">
        <Link to="/" className="navbar-brand">
          ${applicationName}
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item active">
              <Link to="/" className="nav-link">
                Home <span className="sr-only">(current)</span>
              </Link>
            </li>
            <#list entities as entity>
            <li className="nav-item active">
              <Link to="/${entity.name?lower_case}s" className="nav-link">
                ${entity.name}s <span className="sr-only">(current)</span>
              </Link>
            </li>
            </#list>
          </ul>
          <div>
            <div className="navbar-nav nav-item">

            </div>
          </div>
        </div>
      </div>
    </nav>
  );
}
export default Navbar;
