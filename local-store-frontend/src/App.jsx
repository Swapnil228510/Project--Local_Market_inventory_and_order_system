import "./App.css";
import Footer from "./components/Footer";
import Navbar from "./components/Navbar";
import Approuter from "./router/Approuter";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

function App() {
  return (
    <>
      <Navbar />
      <Approuter />
      <Footer />
    </>
  );
}

export default App;
