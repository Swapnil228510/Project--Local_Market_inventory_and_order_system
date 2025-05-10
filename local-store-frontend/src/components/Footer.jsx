import React from "react";
import { Box, Typography, Container, Grid, Link } from "@mui/material";

const Footer = () => {
  return (
    <Box
      sx={{
        bgcolor: "#0d47a1",
        color: "white",
        py: 5,
        mt: "auto",
      }}
      component="footer"
    >
      <Container maxWidth="lg">
        <Grid container spacing={2} columnSpacing={12}>
          <Box sx={{ md: 3 }}>
            <Grid sx={{ mb: 2 }}>
              <Typography variant="h6" gutterBottom>
                Local Mart
              </Typography>
              <Typography variant="body2">
                Your go-to place for everyday essentials, fresh produce, and
                more.
              </Typography>
            </Grid>
          </Box>

          <Box sx={{ md: 3 }}>
            <Grid sx={{ mb: 2 }}>
              <Typography variant="h6" gutterBottom>
                Explore
              </Typography>
              <Link href="/products" color="inherit" underline="hover">
                Products
              </Link>
              <br />
              <Link href="/pricing" color="inherit" underline="hover">
                Pricing
              </Link>
              <br />
              <Link href="/blog" color="inherit" underline="hover">
                Blog
              </Link>
            </Grid>
          </Box>

          <Box sx={{ md: 3 }}>
            <Grid sx={{ mb: 2 }}>
              <Typography variant="h6" gutterBottom>
                Support
              </Typography>
              <Link href="/help" color="inherit" underline="hover">
                Help Center
              </Link>
              <br />
              <Link href="/contact" color="inherit" underline="hover">
                Contact Us
              </Link>
              <br />
              <Link href="/privacy" color="inherit" underline="hover">
                Privacy Policy
              </Link>
            </Grid>
          </Box>

          <Box sx={{ md: 3 }}></Box>
          <Grid sx={{ mb: 2 }}>
            <Typography variant="h6" gutterBottom>
              Follow Us
            </Typography>
            <Link href="#" color="inherit" underline="hover">
              Facebook
            </Link>
            <br />
            <Link href="#" color="inherit" underline="hover">
              Instagram
            </Link>
            <br />
            <Link href="#" color="inherit" underline="hover">
              Twitter
            </Link>
          </Grid>
        </Grid>
        {/* </Box> */}

        <Box mt={2} textAlign="center">
          <Typography variant="body2">
            © {new Date().getFullYear()} Local Mart. All rights reserved.
          </Typography>
        </Box>
      </Container>
    </Box>
  );
};

export default Footer;
