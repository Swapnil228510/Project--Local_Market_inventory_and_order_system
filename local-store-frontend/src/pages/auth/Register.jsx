import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { register } from "../../service/authService";

const Register = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [mobile, setMobile] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [city, setCity] = useState("");
  const [dob, setDob] = useState("");
  const [gender, setGender] = useState("");

  const navigate = useNavigate();

  const registerUser = async () => {
    const passwordPattern =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{7,}$/;

    const mobilePattern = /^\d{10}$/;

    if (firstName.trim().length === 0) {
      toast.error("Please enter First Name");
    } else if (lastName.trim().length === 0) {
      toast.error("Please enter Last Name");
    } else if (email.trim().length === 0 || !/\S+@\S+\.\S+/.test(email)) {
      toast.error("Please enter a valid Email");
    } else if (
      !passwordPattern.test(password) ||
      password.trim().length === 0
    ) {
      toast.error("Password does not meet the required criteria");
    } else if (confirmPassword.trim().length === 0) {
      toast.error("Please enter Confirm Password");
    } else if (password != confirmPassword) {
      toast.error("Password does not matched");
    } else if (mobile.trim().length === 0) {
      toast.error("Please enter Mobile Number");
    } else if (!mobilePattern.test(mobile)) {
      toast.error("Mobile number must be exactly 10 digits");
    } else if (city.trim().length === 0) {
      toast.error("Please enter your City name");
    } else if (dob.length === 0) {
      toast.error("Please enter your Date of Birth");
    } else if (gender.length === 0) {
      toast.error("Please select your Gender");
    } else {
      const userInfo = {
        firstName: firstName.trim(),
        lastName: lastName.trim(),
        email: email.trim(),
        password: password.trim(),
        mobNo: mobile.trim(),
        gender: gender.trim(),
        city: city.trim(),
        dob: dob.trim(),
      };
      const response = await register(userInfo);

      if (response.data.success === true) {
        toast.success("You have successfully registered!!");
        navigate("/logIn");
      } else {
        toast.error("Something went wrong !! Please try again");
      }
    }
  };

  return (
    <>
      {/* Section: Design Block  */}
      <section className="text-center">
        {/*  Background image  */}
        <div
          className="p-5 bg-image"
          style={{
            backgroundImage: "url('Images/re.jpg')",
            height: "400px",
          }}
        ></div>
        {/*  Background image  */}

        <div
          className="card mx-4 mx-md-5 shadow-5-strong bg-body-tertiary"
          style={{
            marginTop: "-100px",
            backdropFilter: "blur(30px)",
          }}
        >
          <div className="card-body py-5 px-md-5">
            <div className="row d-flex justify-content-center">
              <div className="col-lg-8">
                <h2 className="fw-bold mb-5">Sign up now</h2>
                <form>
                  {/*  2 column grid layout with text inputs for the first and last names */}
                  <div className="row">
                    <div className="col-md-6 mb-4">
                      <div data-mdb-input-init className="form-outline">
                        <input
                          type="text"
                          id="firstNameId"
                          className="form-control"
                          placeholder="First name"
                          onChange={(e) => setFirstName(e.target.value)}
                        />
                      </div>
                    </div>
                    <div className="col-md-6 mb-4">
                      <div data-mdb-input-init className="form-outline">
                        <input
                          type="text"
                          id="lastNameId"
                          className="form-control"
                          placeholder="Last Name"
                          onChange={(e) => setLastName(e.target.value)}
                        />
                      </div>
                    </div>
                  </div>
                  {/*  Email input */}
                  <div data-mdb-input-init className="form-outline mb-4">
                    <input
                      type="email"
                      id="emailId"
                      className="form-control"
                      placeholder="Email Address"
                      //   value={email}
                      onChange={(e) => setEmail(e.target.value)}
                    />
                  </div>
                  {/*  Password input  */}
                  <div data-mdb-input-init className="form-outline mb-4">
                    <input
                      type="password"
                      id="passwordId"
                      className="form-control"
                      pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{7,}"
                      placeholder="Password"
                      onChange={(e) => setPassword(e.target.value)}
                    />
                    <div className="alert alert-info p-2 small" role="alert">
                      <i className="bi bi-info-circle-fill me-2 text-primary"></i>
                      <strong>Password requirements:</strong> Must be at least{" "}
                      <strong>7 characters</strong> and include a
                      <span className="text-success"> lowercase</span>,
                      <span className="text-primary"> uppercase</span>,
                      <span className="text-danger"> number</span>, and a
                      <span className="text-primary"> special character</span>.
                    </div>
                  </div>
                  <div data-mdb-input-init className="form-outline mb-4">
                    <input
                      type="password"
                      id="confirmPasswordId"
                      className="form-control"
                      placeholder="Confirm Password"
                      onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                  </div>
                  {/* ----------------------- */}
                  <div className="row">
                    <div className="col-md-6 mb-4">
                      <div data-mdb-input-init className="form-outline">
                        <input
                          type="number"
                          id="mobId"
                          className="form-control"
                          placeholder="Mobile No."
                          onChange={(e) => setMobile(e.target.value)}
                        />
                      </div>
                    </div>
                    <div className="col-md-6 mb-4">
                      <div data-mdb-input-init className="form-outline">
                        <input
                          type="text"
                          id="cityId"
                          className="form-control"
                          placeholder="City"
                          onChange={(e) => setCity(e.target.value)}
                        />
                      </div>
                    </div>
                  </div>
                  {/* ------------------------------------------ */}
                  <div className="row">
                    <div className="col-md-6 mb-4">
                      <div data-mdb-input-init className="form-outline">
                        <label className="label d-block mb-3 fw-semibold fs-8 text-primary">
                          Date of Birth
                        </label>
                        <input
                          type="date"
                          id="dobId"
                          className="form-control"
                          placeholder="Date of Birth"
                          onChange={(e) => setDob(e.target.value)}
                        />
                      </div>
                    </div>

                    {/* /--- */}

                    <div className="col-md-6 mb-4">
                      <label className="label d-block mb-3 fw-semibold fs-8 text-primary">
                        Select Gender
                      </label>

                      <div className="form-check form-check-inline">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="gender"
                          id="male"
                          value="Male"
                          checked={gender === "Male"}
                          onChange={(e) => setGender(e.target.value)}
                        />
                        <label
                          className="form-check-label text-dark"
                          htmlFor="male"
                        >
                          👨 Male
                        </label>
                      </div>

                      <div className="form-check form-check-inline">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="gender"
                          id="female"
                          value="Female"
                          checked={gender === "Female"}
                          onChange={(e) => setGender(e.target.value)}
                        />
                        <label
                          className="form-check-label text-dark"
                          htmlFor="female"
                        >
                          👩 Female
                        </label>
                      </div>

                      <div className="form-check form-check-inline">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="gender"
                          id="other"
                          value="Other"
                          checked={gender === "Other"}
                          onChange={(e) => setGender(e.target.value)}
                        />
                        <label
                          className="form-check-label text-dark"
                          htmlFor="other"
                        >
                          🌈 Other
                        </label>
                      </div>
                    </div>
                  </div>
                  {/* ----------------------------- */}
                  {/*  Checkbox  */}
                  <div className="form-check d-flex justify-content-center mb-4">
                    <input
                      className="form-check-input me-2"
                      type="checkbox"
                      value=""
                      id="form2Example33"
                      //   checked
                    />
                    <label
                      className="form-check-label"
                      htmlFor="form2Example33"
                    >
                      Subscribe for the new product updates!!
                    </label>
                  </div>
                  {/*  Submit button */}
                  <button
                    type="button"
                    data-mdb-button-init
                    data-mdb-ripple-init
                    className="btn btn-primary btn-block mb-4"
                    onClick={registerUser}
                  >
                    Sign up
                  </button>
                  <div className="mt-3">
                    Already have an account?{" "}
                    <Link to="/logIn" className="btn btn-link p-0">
                      Login
                    </Link>
                  </div>
                  <br />

                  {/*  Register buttons */}
                  <div className="text-center">
                    <p>or sign up with:</p>
                    <button
                      type="button"
                      data-mdb-button-init
                      data-mdb-ripple-init
                      className="btn btn-link btn-floating mx-1"
                    >
                      <i className="fab fa-facebook-f"></i>
                    </button>

                    <button
                      type="button"
                      data-mdb-button-init
                      data-mdb-ripple-init
                      className="btn btn-link btn-floating mx-1"
                    >
                      <i className="fab fa-google"></i>
                    </button>

                    <button
                      type="button"
                      data-mdb-button-init
                      data-mdb-ripple-init
                      className="btn btn-link btn-floating mx-1"
                    >
                      <i className="fab fa-twitter"></i>
                    </button>

                    <button
                      type="button"
                      data-mdb-button-init
                      data-mdb-ripple-init
                      className="btn btn-link btn-floating mx-1"
                    >
                      <i className="fab fa-github"></i>
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/*  Section: Design Block  */}
    </>
  );
};

export default Register;
