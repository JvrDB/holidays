package r1vs.holidays.service;

import java.util.List;

import r1vs.holidays.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    EmployeeDto getEmployeeByRun(String employeeRun);
    EmployeeDto getEmployeeByEmail(String employeeEmail);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto);
    void deleteEmployee(Long employeeId);
}
