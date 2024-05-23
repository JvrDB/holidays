package r1vs.holidays.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import r1vs.holidays.dto.VacationDto;
import r1vs.holidays.entity.Vacation;
import r1vs.holidays.exception.ResourceNotFoundException;
import r1vs.holidays.mapper.EmployeeMapper;
import r1vs.holidays.mapper.VacationMapper;
import r1vs.holidays.mapper.VacationTypeMapper;
import r1vs.holidays.repository.EmployeeRepository;
import r1vs.holidays.repository.VacationRepository;
import r1vs.holidays.repository.VacationTypeRepository;
import r1vs.holidays.service.VacationService;

@Service
@AllArgsConstructor
public class VacationServiceImpl implements VacationService {
    
    private VacationRepository vacationRepository;

    private EmployeeRepository employeeRepository;

    private VacationTypeRepository vacationTypeRepository;

    @Override
    public VacationDto createVacation(VacationDto vacationDto) {
        employeeRepository.findById(vacationDto.getEmployee().getId()).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exists with given id: " + vacationDto.getEmployee().getId()));
        vacationTypeRepository.findById(vacationDto.getVacationType().getId()).orElseThrow(
            () -> new ResourceNotFoundException("Vacation Type does not exists with given id: " + vacationDto.getVacationType().getId()));

        Vacation vacation = VacationMapper.mapToVacation(vacationDto);
        Vacation savedVacation = vacationRepository.save(vacation);
        return VacationMapper.mapToVacationDto(savedVacation);
    }

    @Override
    public VacationDto getVacationById(Long vacationId) {
        Vacation vacation = vacationRepository.findById(vacationId).orElseThrow(
            () -> new ResourceNotFoundException("Vacation does not exists with given id: " + vacationId));
        return VacationMapper.mapToVacationDto(vacation);
    }

    @Override
    public List<VacationDto> getAllVacations() {
        List<Vacation> vacations = vacationRepository.findAll();
        return vacations.stream().map((vacation) -> VacationMapper.mapToVacationDto(vacation))
                .collect(Collectors.toList());
    }

    @Override
    public List<VacationDto> getVacationsByEmployeeId(Long employeeId) {
        List<Vacation> vacations = vacationRepository.findByEmployeeId(employeeId);
        return vacations.stream().map((vacation) -> VacationMapper.mapToVacationDto(vacation))
                .collect(Collectors.toList());
    }

    @Override
    public VacationDto updateVacation(Long vacationId, VacationDto updatedVacationDto) {
        employeeRepository.findById(updatedVacationDto.getEmployee().getId()).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exists with given id: " + updatedVacationDto.getEmployee().getId()));
        vacationTypeRepository.findById(updatedVacationDto.getVacationType().getId()).orElseThrow(
            () -> new ResourceNotFoundException("Vacation Type does not exists with given id: " + updatedVacationDto.getVacationType().getId()));
        Vacation vacation = vacationRepository.findById(vacationId).orElseThrow(
            () -> new ResourceNotFoundException("Vacation does not exists with given id: " + vacationId));

        vacation.setStartDate(updatedVacationDto.getStartDate());
        vacation.setEndDate(updatedVacationDto.getEndDate());
        vacation.setEmployee(EmployeeMapper.mapToEmployee(updatedVacationDto.getEmployee()));
        vacation.setVacationType(VacationTypeMapper.mapToVacationType(updatedVacationDto.getVacationType()));

        Vacation updatedVacation = vacationRepository.save(vacation);

        return VacationMapper.mapToVacationDto(updatedVacation);
    }

    @Override
    public void deleteVacation(Long vacationId) {
        vacationRepository.findById(vacationId).orElseThrow(
            () -> new ResourceNotFoundException("Vacation does not exists with given id: " + vacationId));
        
        vacationRepository.deleteById(vacationId);
    }

}
