import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "../pages/Home";
import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import PrivateRoute from "../auth/PrivateRoute";
import CustomerHome from "../pages/customer/CustomerHome";
import StaffDashboard from "../pages/staff/StaffDashboard";
import AdminPanel from "../pages/admin/AdminPanel";
import NotFound from "../pages/NotFound";

const Approuter = () => {
  return (
    <BrowserRouter>
      <Routes>
        {/* Public Routes */}
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Protected role based routes */}
        <Route
          path="/customer"
          element={
            <PrivateRoute role="CUSTOMER">
              <CustomerHome />
            </PrivateRoute>
          }
        />

        <Route
          path="/staff"
          element={
            <PrivateRoute role="STAFF">
              <StaffDashboard />
            </PrivateRoute>
          }
        />

        <Route
          path="/admin"
          element={
            <PrivateRoute role="ADMIN">
              <AdminPanel />
            </PrivateRoute>
          }
        />

        {/* 404 Route */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Approuter;
