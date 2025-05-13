import React, { useEffect, useState } from "react";
import { getAllProduct } from "../service/product";
import { toast } from "react-toastify";
import { Box, Divider, Grid, Typography } from "@mui/material";
import ProductCard from "../components/ProductCard";
import StorefrontIcon from "@mui/icons-material/Storefront";

const Products = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const fetchAllProduct = async () => {
      const response = await getAllProduct();
      if (response && response.data.length === 0) {
        toast.info("There are no product is avaliable");
      } else {
        // toast.success("Products");
        setProducts(response.data);
      }
    };
    fetchAllProduct();
  }, []);
  return (
    <>
      {/* for displaying head  */}

      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        gap={1}
        mt={3}
      >
        <StorefrontIcon color="primary" fontSize="large" />
        <Typography
          variant="h4"
          fontWeight="bold"
          color="primary.main"
          textTransform="uppercase"
          letterSpacing={2}
        >
          Products
        </Typography>
      </Box>

      <Divider sx={{ mb: 3, mx: "auto", width: "20%", borderBottomWidth: 2 }} />

      {/* below is for productcard component */}
      <Box sx={{ p: 3 }}>
        <Grid container spacing={2}>
          {products.map((product) => (
            <Grid key={product.id}>
              <ProductCard product={product} />
            </Grid>
          ))}
        </Grid>
      </Box>
    </>
  );
};

export default Products;
