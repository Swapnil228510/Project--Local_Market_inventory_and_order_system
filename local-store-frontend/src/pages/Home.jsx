import { Box, Typography } from "@mui/material";
import React, { useState } from "react";

const Home = () => {
  return (
    <>
      {/* Carousel ==============================================*/}
      <Box
        sx={{
          maxWidth: "100%",
          overflow: "hidden",
          borderRadius: 3,
          boxShadow: 3,
          my: 3,
        }}
      >
        <div
          id="carouselExampleCaptions"
          className="carousel slide"
          data-bs-ride="carousel"
        >
          <div className="carousel-indicators">
            {[0, 1, 2].map((index) => (
              <button
                key={index}
                type="button"
                data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to={index}
                className={index === 0 ? "active" : ""}
                aria-current={index === 0 ? "true" : undefined}
                aria-label={`Slide ${index + 1}`}
              ></button>
            ))}
          </div>

          <div className="carousel-inner">
            {[
              {
                src: "/Images/Carousal/items.jpg",
                label: "Fresh Items",
                text: "Best quality everyday items.",
              },
              {
                src: "/Images/Carousal/masala.jpg",
                label: "Masala Magic",
                text: "Spices that bring flavor alive!",
              },
              {
                src: "/Images/Carousal/veggies.jpg",
                label: "Fresh Veggies",
                text: "Farm to your kitchen.",
              },
            ].map((slide, index) => (
              <div
                key={index}
                className={`carousel-item ${index === 0 ? "active" : ""}`}
              >
                <img
                  src={slide.src}
                  className="d-block w-100"
                  alt="slide"
                  style={{ height: "400px", objectFit: "cover" }}
                />
                <div className="carousel-caption d-none d-md-block bg-dark bg-opacity-50 rounded p-3">
                  <Typography variant="h5">{slide.label}</Typography>
                  <Typography variant="body1">{slide.text}</Typography>
                </div>
              </div>
            ))}
          </div>

          <button
            className="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev"
          >
            <span
              className="carousel-control-prev-icon"
              aria-hidden="true"
            ></span>
            <span className="visually-hidden">Previous</span>
          </button>
          <button
            className="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next"
          >
            <span
              className="carousel-control-next-icon"
              aria-hidden="true"
            ></span>
            <span className="visually-hidden">Next</span>
          </button>
        </div>
      </Box>
    </>
  );
};

export default Home;
