// import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
// import { addProduct } from "../service/product";

// const ProductForm = () => {
//   const navigate = useNavigate();
//   const [formData, setFormData] = useState({
//     name: "",
//     prize: "",
//     quantity: "",
//     category: "",
//     description: "",
//     image: null,
//   });

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setFormData((prev) => ({ ...prev, [name]: value }));
//   };

//   const handleImageChange = (e) => {
//     setFormData((prev) => ({ ...prev, image: e.target.files[0] }));
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     const data = new FormData();
//     data.append("name", formData.name);
//     data.append("prize", formData.prize);
//     data.append("quantity", formData.quantity);
//     data.append("categoryName", formData.category);
//     data.append("description", formData.description);
//     data.append("image", formData.image);

//     const respone = await addProduct(data);

//     if (respone) {
//       navigate("/staff");
//     }
//   };
//   return (
//     <>
//       <form
//         onSubmit={handleSubmit}
//         encType="multipart/form-data"
//         style={{ padding: "20px" }}
//       >
//         <h2 style={{ marginBottom: "20px" }}>Add new product </h2>

//         <input
//           type="text"
//           name="name"
//           value={formData.name}
//           onChange={handleChange}
//           placeholder="Product Name"
//           required
//         />

//         <br />
//         <br />

//         <input
//           type="number"
//           name="prize"
//           value={formData.prize}
//           onChange={handleChange}
//           placeholder="Product Price"
//           required
//         />
//         <br />
//         <br />

//         <input
//           type="number"
//           name="quantity"
//           value={formData.quantity}
//           onChange={handleChange}
//           placeholder="Quantity"
//           required
//         />
//         <br />
//         <br />

//         <input
//           type="text"
//           name="category"
//           value={formData.category}
//           onChange={handleChange}
//           placeholder="Product Category"
//           required
//         />
//         <br />
//         <br />

//         <textarea
//           name="description"
//           value={formData.description}
//           onChange={handleChange}
//           placeholder="Product Description"
//           rows="4"
//           cols="50"
//         />
//         <br />
//         <br />

//         <input
//           type="file"
//           accept="image/*"
//           onChange={handleImageChange}
//           required
//         />
//         <br />
//         <br />

//         <button type="submit">Add Product</button>
//       </form>
//     </>
//   );
// };

// export default ProductForm;

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addProduct } from "../service/product";
import "../index.css"; // or "./ProductForm.css"

const ProductForm = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: "",
    prize: "",
    quantity: "",
    category: "",
    description: "",
    image: null,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleImageChange = (e) => {
    setFormData((prev) => ({ ...prev, image: e.target.files[0] }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = new FormData();
    data.append("name", formData.name);
    data.append("prize", formData.prize);
    data.append("quantity", formData.quantity);
    data.append("categoryName", formData.category);
    data.append("description", formData.description);
    data.append("image", formData.image);

    const response = await addProduct(data);
    if (response) navigate("/staff");
  };

  return (
    <div className="product-form-container">
      <form
        onSubmit={handleSubmit}
        className="product-form"
        encType="multipart/form-data"
      >
        <h2>Add New Product</h2>

        <label className="product-label">Product Name</label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          className="product-input"
          required
        />

        <label className="product-label">Product Price</label>
        <input
          type="number"
          name="prize"
          value={formData.prize}
          onChange={handleChange}
          className="product-input"
          required
        />

        <label className="product-label">Quantity</label>
        <input
          type="number"
          name="quantity"
          value={formData.quantity}
          onChange={handleChange}
          className="product-input"
          required
        />

        <label className="product-label">Category</label>
        <input
          type="text"
          name="category"
          value={formData.category}
          onChange={handleChange}
          className="product-input"
          required
        />

        <label className="product-label">Description</label>
        <textarea
          name="description"
          value={formData.description}
          onChange={handleChange}
          className="product-textarea"
        />

        <label className="product-label">Product Image</label>
        <input
          type="file"
          accept="image/*"
          onChange={handleImageChange}
          className="product-input"
          required
        />

        <button type="submit" className="product-button">
          Add Product
        </button>
      </form>
    </div>
  );
};

export default ProductForm;
