import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { login } from "../../service/authService";
import { jwtDecode } from "jwt-decode";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const signinUser = async () => {
    if (email.trim().length === 0) {
      toast.error("Enter email address");
      return;
    } else if (password.trim().length === 0) {
      toast.error("Enter password");
      return;
    }

    try {
      const response = await login({ email, password });
      const token = response.data.jwt;
      //   console.log(" reponse" + response.data);

      localStorage.setItem("token", token);
      const decoded = jwtDecode(token);
      //   console.log(" role " + decoded.authorities);

      localStorage.setItem("user", JSON.stringify(response.data));

      toast.success(" Login successfull");

      //Navigate based on role

      switch (decoded.authorities) {
        case "ROLE_CUSTOMER":
          navigate("/customer");
          break;
        case "ROLE_STAFF":
          navigate("/staff");
          break;
        case "ROLE_ADMIN":
          navigate("/admin");
          break;
        default:
          navigate("/");
      }
    } catch (err) {
      toast.error("Invalid email or password");
      console.error(err);
    }
  };

  return (
    <>
      <CssBaseline />
      <section className="vh-100">
        <div className="card-body py-5 px-md-5">
          <div className="row d-flex justify-content-center">
            <div className="container-fluid">
              <div className="row">
                <div className="col-sm-6 text-black">
                  <div className="px-5 ms-xl-4">
                    <i
                      className="fas fa-crow fa-2x me-3 pt-5 mt-xl-4"
                      style={{ color: "#709085" }}
                    ></i>
                    <span
                      className="h1 fw-bold mb-4 text-primary text-center"
                      style={{
                        fontFamily: "Arial, sans-serif",
                        textShadow: "2px 2px 5px rgba(0, 0, 0, 0.1)",
                      }}
                    >
                      Welcome to Local Mart
                    </span>
                  </div>

                  {/* <Container maxWidth="sm">
          <Box sx={{ bgcolor: "#cfe8fc", height: "50vh" }} /> */}

                  <div className="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">
                    <form style={{ width: "23rem" }}>
                      <h3
                        className="fw-normal mb-3 pb-3"
                        style={{ letterSpacing: "1px" }}
                      >
                        Log in
                      </h3>

                      <div data-mdb-input-init className="form-outline mb-4">
                        <input
                          type="email"
                          id="form2Example18"
                          className="form-control form-control-lg"
                          placeholder="Email address"
                          onChange={(e) => setEmail(e.target.value)}
                        />
                      </div>

                      <div data-mdb-input-init className="form-outline mb-4">
                        <input
                          type="password"
                          id="form2Example28"
                          className="form-control form-control-lg"
                          placeholder="Password"
                          onChange={(e) => setPassword(e.target.value)}
                        />
                      </div>

                      <div className="pt-1 mb-4">
                        <button
                          data-mdb-button-init
                          data-mdb-ripple-init
                          className="btn btn-info btn-lg btn-block"
                          type="button"
                          onClick={signinUser}
                        >
                          Login
                        </button>
                      </div>

                      <p className="small mb-5 pb-lg-2">
                        <a className="text-muted" href="#!">
                          Forgot password?
                        </a>
                      </p>
                      <p>
                        Don't have an account?{" "}
                        <Link to="/register" className="link-info">
                          Register here
                        </Link>
                      </p>
                    </form>
                  </div>
                </div>
                {/* </Container> */}
                <div className="col-sm-6 px-0 d-none d-sm-block">
                  <img
                    src="/Images/Mom&Dad.jpg"
                    alt="Mom&Dad_Image"
                    className="w-100 vh-110"
                    style={{ objectFit: "cover", objectPosition: "left" }}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default Login;
