import React, { useState, useEffect } from "react";
import axios from "axios";
import EmployeeSearch from "./components/EmployeeSearch";
import EmployeeTable from "./components/EmployeeTable";
import { CssBaseline, Container, Typography } from "@mui/material";

const App = () => {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    axios.get("/employees")
      .then(response => setEmployees(response.data))
      .catch(error => console.error("Error fetching employees:", error));
  }, []);

  const searchEmployeeById = (id) => {
    if (id.trim() === "") {
      axios.get("/employees")
        .then(response => setEmployees(response.data))
        .catch(error => console.error("Error fetching employees:", error));
    } else {
      axios.get(`/employees/${id}`)
        .then(response => setEmployees([response.data]))
        .catch(error => console.error("Error fetching employee:", error));
    }
  };

  return (
    <>
      <CssBaseline />
      <Container>
        <Typography variant="h4" align="center" sx={{ mt: 4 }}>
          Employee Search System
        </Typography>
        <EmployeeSearch onSearch={searchEmployeeById} />
        <EmployeeTable employees={employees} />
      </Container>
    </>
  );
};

export default App;