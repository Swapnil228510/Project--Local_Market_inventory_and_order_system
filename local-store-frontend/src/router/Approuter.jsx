import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "../pages/Home";
import AboutUs from "../pages/AboutUs";
import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import PrivateRoute from "../auth/PrivateRoute";
import CustomerHome from "../pages/customer/CustomerHome";
import MyCart from "../pages/customer/MyCart";
import StaffDashboard from "../pages/staff/StaffDashboard";
import AdminPanel from "../pages/admin/AdminPanel";
import NotFound from "../pages/NotFound";
import LogOut from "../pages/LogOut";
import Profile from "../pages/Profile";
import Products from "../pages/Products";
import ProductForm from "../components/ProductForm";

const Approuter = () => {
  return (
    <Routes>
      {/* Public Routes */}
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/aboutUs" element={<AboutUs />} />
      <Route path="/logOut" element={<LogOut />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/products" element={<Products />} />
      <Route path="/myCart" element={<MyCart />} />

      {/* Protected role based routes */}
      <Route
        path="/customer"
        element={
          <PrivateRoute role="ROLE_CUSTOMER">
            <CustomerHome />
            {/* <MyCart /> */}
          </PrivateRoute>
        }
      />

      <Route
        path="/staff"
        element={
          <PrivateRoute role="ROLE_STAFF">
            <StaffDashboard />
          </PrivateRoute>
        }
      />

      <Route
        path="/admin"
        element={
          <PrivateRoute role="ROLE_ADMIN">
            <AdminPanel />
          </PrivateRoute>
        }
      />

      <Route
        path="/staff/addproduct"
        element={
          <PrivateRoute role={["ROLE_ADMIN", "ROLE_STAFF"]}>
            <ProductForm />
          </PrivateRoute>
        }
      />

      <Route
        path="/staff/product/:productId"
        element={
          <PrivateRoute role={["ROLE_STAFF", "ROLE_ADMIN"]}>
            <ProductForm />
          </PrivateRoute>
        }
      />

      {/* 404 Route */}
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
};

export default Approuter;
