package r1vs.holidays.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import r1vs.holidays.dto.EmployeeDto;
import r1vs.holidays.dto.VacationDto;
import r1vs.holidays.service.EmployeeService;
import r1vs.holidays.service.VacationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private VacationService vacationService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/{id}/vacations")
    public ResponseEntity<List<VacationDto>> getVacationsByEmployeeId(@PathVariable("id") Long employeeId) {
        List<VacationDto> vacations = vacationService.getVacationsByEmployeeId(employeeId);
        return ResponseEntity.ok(vacations);
    }

    @GetMapping("/by-run/{run}")
    public ResponseEntity<EmployeeDto> getEmployeeByRun(@PathVariable("run") String employeeRun){
        EmployeeDto employeeDto = employeeService.getEmployeeByRun(employeeRun);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable("email") String employeeEmail){
        EmployeeDto employeeDto = employeeService.getEmployeeByEmail(employeeEmail);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployeeDto){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
 