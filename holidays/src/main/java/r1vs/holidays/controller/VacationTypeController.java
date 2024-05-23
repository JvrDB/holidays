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

import r1vs.holidays.dto.VacationTypeDto;
import r1vs.holidays.service.VacationTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/vacation-types")
public class VacationTypeController {

    @Autowired
    private VacationTypeService vacationTypeService;

    @PostMapping
    public ResponseEntity<VacationTypeDto> createVacationType(@RequestBody VacationTypeDto vacationTypeDto) {
        VacationTypeDto savedVacationType = vacationTypeService.createVacationType(vacationTypeDto);
        return new ResponseEntity<>(savedVacationType, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<VacationTypeDto> getVacationTypeById(@PathVariable("id") Long vacationTypeId) {
        VacationTypeDto vacationTypeDto = vacationTypeService.getVacationTypeById(vacationTypeId);
        return ResponseEntity.ok(vacationTypeDto);
    }

    @GetMapping
    public ResponseEntity<List<VacationTypeDto>> getAllVacationTypes() {
        List<VacationTypeDto> vacationTypes = vacationTypeService.getAllVacationTypes();
        return ResponseEntity.ok(vacationTypes);
    }

    @PutMapping("{id}")
    public ResponseEntity<VacationTypeDto> updateVacationType(@PathVariable("id") Long vacationTypeId, @RequestBody VacationTypeDto updatedvacationTypeDto) {
        VacationTypeDto vacationTypeDto = vacationTypeService.updateVacationType(vacationTypeId, updatedvacationTypeDto);
        return ResponseEntity.ok(vacationTypeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVacationType(@PathVariable("id") Long vacationTypeId) {
        vacationTypeService.deleteVacationType(vacationTypeId);
        return ResponseEntity.ok("Vacation Type deleted successfully");
    }

}
