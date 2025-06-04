import { useState } from "react";

import "./App.css";
import { Route, Routes, Router } from "react-router-dom";
import HomePage from "./Components/HomePage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
    </Routes>
  );
}

export default App;
