import React, { useState } from "react";
import {
  Card,
  CardContent,
  CardMedia,
  Typography,
  CardActions,
  Button,
  IconButton,
} from "@mui/material";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";

const ProductCard = ({ product }) => {
  const [liked, setLiked] = useState(false);

  // const imageUrl = `/images/${product.name.toLowerCase()}.jpg`;
  // const imageUrl = `/Images/Mom&Dad.jpg`;

  const handleLike = () => {
    setLiked(!liked);
  };
  return (
    <>
      {/* {" "} */}
      <Card
        sx={{
          maxWidth: 320,
          height: 320,
          margin: 2,
          borderRadius: "16px",
          boxShadow: 5,
          position: "relative",
          display: "flex",
          flexDirection: "column",
          justifyContent: "space-between",
        }}
      >
        <CardMedia
          component="img"
          height="180"
          image={product.imageUrl}
          alt={product.name}
          onError={(e) => {
            e.target.onerror = null;
            e.target.src = "/images/default.jpg";
          }}
        />

        {/* Heart Icon */}
        <IconButton
          onClick={handleLike}
          sx={{
            position: "absolute",
            top: 10,
            right: 10,
            backgroundColor: "white",
            zIndex: 1,
          }}
        >
          {liked ? <FavoriteIcon color="error" /> : <FavoriteBorderIcon />}
        </IconButton>

        <CardContent>
          <Typography gutterBottom variant="h6" component="div">
            {product.name}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Price: ₹{product.prize}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Quantity: {product.quantity}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Category: {product.categoryName}
          </Typography>
        </CardContent>
        <CardActions>
          <Button size="small" color="primary">
            Buy
          </Button>
        </CardActions>
      </Card>
    </>
  );
};

export default ProductCard;
