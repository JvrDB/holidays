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

import r1vs.holidays.dto.VacationDto;
import r1vs.holidays.service.VacationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/vacations")
public class VacationController {

    @Autowired
    private VacationService vacationService;

    
    @PostMapping
    public ResponseEntity<VacationDto> createVacation(@RequestBody VacationDto vacationDto) {
        VacationDto savedVacation = vacationService.createVacation(vacationDto);
        return new ResponseEntity<>(savedVacation, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<VacationDto> getVacationById(@PathVariable("id") Long vacationId) {
        VacationDto vacationDto = vacationService.getVacationById(vacationId);
        return ResponseEntity.ok(vacationDto);
    }

    @GetMapping
    public ResponseEntity<List<VacationDto>> getAllVacations() {
        List<VacationDto> vacations = vacationService.getAllVacations();
        return ResponseEntity.ok(vacations);
    }

    @PutMapping("{id}")
    public ResponseEntity<VacationDto> updateVacation(@PathVariable("id") Long vacationId, @RequestBody VacationDto updatedVacationDto) {
        VacationDto vacationDto = vacationService.updateVacation(vacationId, updatedVacationDto);
        return ResponseEntity.ok(vacationDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVacation(@PathVariable("id") Long vacationId) {
        vacationService.deleteVacation(vacationId);
        return ResponseEntity.ok("Vacation deleted successfully");
    }
}
