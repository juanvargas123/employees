import React, { useState } from "react";
import { TextField, Button, Box } from "@mui/material";

const EmployeeSearch = ({ onSearch }) => {
  const [id, setId] = useState("");

  const handleSearch = () => {
    onSearch(id);
  };

  return (
    <Box sx={{ display: "flex", justifyContent: "center", mt: 3 }}>
      <TextField
        label="Enter Employee ID"
        variant="outlined"
        value={id}
        onChange={(e) => setId(e.target.value)}
      />
      <Button 
        variant="contained" 
        color="primary" 
        sx={{ ml: 2 }}
        onClick={handleSearch}
      >
        Search
      </Button>
    </Box>
  );
};

export default EmployeeSearch;
