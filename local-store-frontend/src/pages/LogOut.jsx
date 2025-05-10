import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const LogOut = () => {
  const navigate = useNavigate();

  useEffect(() => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    navigate("/");
  }, [navigate]);

  return null;
};

export default LogOut;
