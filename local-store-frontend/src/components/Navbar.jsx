import {
  AppBar,
  Avatar,
  Box,
  IconButton,
  Menu,
  MenuItem,
  Toolbar,
  Tooltip,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AdbIcon from "@mui/icons-material/Adb";
import { Button } from "@mui/material";
import LightModeIcon from "@mui/icons-material/LightMode";
import DarkModeIcon from "@mui/icons-material/DarkMode";
import { toast } from "react-toastify";

const Navbar = () => {
  const [darkMode, setDarkMode] = useState(false);
  const settings = ["Profile", "Logout"];
  const [anchorElUser, setAnchorElUser] = useState(null);
  const navigate = useNavigate();

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const handleAvtarItemList = (e) => {
    console.log(" in navbar logout " + e);
    const items = localStorage.getItem("token");
    if (items === null) {
      toast.info("Please LogIn first");
    } else if (e === "Logout") {
      navigate("/logOut");
      handleCloseUserMenu();
    } else if (e === "Profile") {
      navigate("/profile");
      handleCloseUserMenu();
    }
  };

  const toggleDarkMode = () => {
    setDarkMode(!darkMode);
    document.body.style.backgroundColor = darkMode ? "#fff" : "#121212";
  };
  return (
    <>
      <AppBar
        position="static"
        sx={{
          width: "100vw",
          left: 0,
          right: 0,
          background: "linear-gradient(90deg, #1e3c72, #2a5298 )",
          boxShadow: 3,
        }}
      >
        <Toolbar sx={{ width: "100%", px: 2 }}>
          <AdbIcon sx={{ display: { xs: "none", md: "flex" }, mr: 1 }} />
          <Typography
            variant="h6"
            noWrap
            component={Link}
            to="/"
            sx={{
              mr: 3,
              display: { xs: "none", md: "flex" },
              fontFamily: "monospace",
              fontWeight: 800,
              letterSpacing: ".2rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            LOCALMART
          </Typography>

          <Box sx={{ display: { xs: "none", md: "flex" }, gap: 1 }}>
            <Link to="/products" style={{ textDecoration: "none" }}>
              <Button
                //    variant="outlined"
                sx={{
                  color: "white",
                  borderColor: "white",
                  fontWeight: 600,
                  "&:hover": { borderColor: "#ffc107", color: "#ffc107" },
                }}
              >
                Products
              </Button>
            </Link>
          </Box>

          <Box sx={{ display: { xs: "none", md: "flex" }, gap: 1 }}>
            <Link to="/aboutUs" style={{ textDecoration: "none" }}>
              <Button
                //    variant="outlined"
                sx={{
                  color: "white",
                  borderColor: "white",
                  fontWeight: 600,
                  "&:hover": { borderColor: "#ffc107", color: "#ffc107" },
                }}
              >
                About US
              </Button>
            </Link>
          </Box>

          <Box sx={{ display: { xs: "none", md: "flex" }, gap: 1 }}>
            <Link to="/myCart" style={{ textDecoration: "none" }}>
              <Button
                //    variant="outlined"
                sx={{
                  color: "white",
                  borderColor: "white",
                  fontWeight: 600,
                  "&:hover": { borderColor: "#ffc107", color: "#ffc107" },
                }}
              >
                My Cart
              </Button>
            </Link>
          </Box>

          <Box
            sx={{
              marginLeft: "auto",
              display: { xs: "none", md: "flex" },
              gap: 1,
            }}
          >
            <Link to="/logIn" style={{ textDecoration: "none" }}>
              <Button
                //    variant="outlined"
                sx={{
                  color: "white",
                  borderColor: "white",
                  fontWeight: 600,
                  "&:hover": { borderColor: "#ffc107", color: "#ffc107" },
                }}
              >
                LogIn
              </Button>
            </Link>
          </Box>

          <Box sx={{ display: { xs: "none", md: "flex" }, gap: 1 }}>
            <Link to="/register" style={{ textDecoration: "none" }}>
              <Button
                //    variant="outlined"
                sx={{
                  color: "white",
                  borderColor: "white",
                  fontWeight: 600,
                  "&:hover": { borderColor: "#ffc107", color: "#ffc107" },
                }}
              >
                SignUp
              </Button>
            </Link>
          </Box>

          {/* Dark Mode Toggle */}
          <IconButton onClick={toggleDarkMode} color="inherit" sx={{ mr: 2 }}>
            {darkMode ? <LightModeIcon /> : <DarkModeIcon />}
          </IconButton>

          {/*  Avatar */}
          <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar alt="User" src="/static/images/avatar/2.jpg" />
              </IconButton>
            </Tooltip>
            <Menu
              anchorEl={anchorElUser}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
              anchorOrigin={{ vertical: "top", horizontal: "right" }}
              transformOrigin={{ vertical: "top", horizontal: "right" }}
              sx={{ mt: "40px" }}
            >
              {settings.map((setting) => (
                <MenuItem
                  key={setting}
                  onClick={() => handleAvtarItemList(setting)}
                >
                  <Typography textAlign="center">{setting}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
        </Toolbar>
      </AppBar>
    </>
  );
};

export default Navbar;
