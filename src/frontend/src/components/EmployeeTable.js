import React from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

const EmployeeTable = ({ employees }) => {
  return (
    <TableContainer component={Paper} sx={{ mt: 3, maxWidth: "80%", margin: "auto" }}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Name</TableCell>
            <TableCell>Salary</TableCell>
            <TableCell>Annual Salary</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {employees.map((emp) => (
            <TableRow key={emp.id}>
              <TableCell>{emp.id}</TableCell>
              <TableCell>{emp.employee_name}</TableCell>
              <TableCell>${emp.employee_salary}</TableCell>
              <TableCell>${emp.annualSalary}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default EmployeeTable;