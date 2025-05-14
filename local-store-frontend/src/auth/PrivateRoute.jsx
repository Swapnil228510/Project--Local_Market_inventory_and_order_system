import { jwtDecode } from "jwt-decode";
import React from "react";
import { Navigate } from "react-router-dom";
import { log } from "../service/logger";

const PrivateRoute = ({ children, role }) => {
  const token = localStorage.getItem("token");

  if (!token) {
    return <Navigate to="/login" />;
  }

  try {
    const decode = jwtDecode(token);
    console.log(" decode " + decode.authorities);
    console.log(" decode role " + role);
    const userRole = decode?.authorities;
    log(" role in private Route  " + userRole + "and Role from props" + role);

    const allowedRole = Array.isArray(role) ? role : [role];

    if (!allowedRole.includes(userRole)) {
      return <Navigate to="/" />;
    }

    return children; // if role match then we simply render to respective page
  } catch (error) {
    console.error("Invalid token", error);
    return <Navigate to="/login" />;
  }
};

export default PrivateRoute;
