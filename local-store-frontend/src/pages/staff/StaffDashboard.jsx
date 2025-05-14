import React from "react";
import Products from "../Products";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";

const StaffDashboard = () => {
  const navigate = useNavigate();
  return (
    <>
      <br />
      <div style={{ padding: 15, textAlign: "right" }}>
        <button
          className="btn btn-primary"
          onClick={() => navigate("/staff/addproduct")}
        >
          + Add Product
        </button>
        <Products />
      </div>
    </>
  );
};

export default StaffDashboard;
