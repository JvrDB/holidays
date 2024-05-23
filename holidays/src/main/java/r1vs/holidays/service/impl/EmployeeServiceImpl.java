package r1vs.holidays.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import r1vs.holidays.dto.EmployeeDto;
import r1vs.holidays.entity.Employee;
import r1vs.holidays.exception.ResourceNotFoundException;
import r1vs.holidays.mapper.EmployeeMapper;
import r1vs.holidays.repository.EmployeeRepository;
import r1vs.holidays.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
 
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exists with given id: " +employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto getEmployeeByRun(String employeeRun) {
        Employee employee = employeeRepository.findByRun(employeeRun).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exists with given run: " + employeeRun));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String employeeEmail) {
        Employee employee = employeeRepository.findByEmail(employeeEmail).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exists with given email: " + employeeEmail));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            ()-> new ResourceNotFoundException("Employee does not exists with given id: " + employeeId));
        
        employee.setFirstName(updatedEmployeeDto.getFirstName());
        employee.setLastName(updatedEmployeeDto.getLastName());
        employee.setEmail(updatedEmployeeDto.getEmail());
        employee.setRun(updatedEmployeeDto.getRun());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exists with given id: " +employeeId));

        employeeRepository.deleteById(employeeId);
    }

}
