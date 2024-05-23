package r1vs.holidays.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import r1vs.holidays.dto.VacationTypeDto;
import r1vs.holidays.entity.VacationType;
import r1vs.holidays.exception.ResourceNotFoundException;
import r1vs.holidays.mapper.VacationTypeMapper;
import r1vs.holidays.repository.VacationTypeRepository;
import r1vs.holidays.service.VacationTypeService;

@Service
@AllArgsConstructor
public class VacationTypeServiceImpl implements VacationTypeService{
    
    private VacationTypeRepository vacationTypeRepository;

    @Override
    public VacationTypeDto createVacationType(VacationTypeDto vacationTypeDto) {
        VacationType vacationType = VacationTypeMapper.mapToVacationType(vacationTypeDto);
        VacationType savedVacationType = vacationTypeRepository.save(vacationType);
        return VacationTypeMapper.mapToVacationTypeDto(savedVacationType);
    }

    @Override
    public VacationTypeDto getVacationTypeById(Long vacationTypeId) {
        VacationType vacationType = vacationTypeRepository.findById(vacationTypeId).orElseThrow(
            () -> new ResourceNotFoundException("Vacation Type does not exists with given id: " +vacationTypeId));
        return VacationTypeMapper.mapToVacationTypeDto(vacationType);
    }

    @Override
    public List<VacationTypeDto> getAllVacationTypes() {
        List<VacationType> vacationTypes = vacationTypeRepository.findAll();
        return vacationTypes.stream().map((vacationType) -> VacationTypeMapper.mapToVacationTypeDto(vacationType))
                .collect(Collectors.toList());
    }

    @Override
    public VacationTypeDto updateVacationType(Long vacationTypeId, VacationTypeDto updatedVacationTypeDto) {
        VacationType vacationType = vacationTypeRepository.findById(vacationTypeId).orElseThrow(
            () -> new ResourceNotFoundException("Vacation Type does not exists with given id: " + vacationTypeId));

        vacationType.setDescription(updatedVacationTypeDto.getDescription());
        vacationType.setMaxDays(updatedVacationTypeDto.getMaxDays());

        VacationType updatedVacationType = vacationTypeRepository.save(vacationType);

        return VacationTypeMapper.mapToVacationTypeDto(updatedVacationType);
    }

    @Override
    public void deleteVacationType(Long vacationTypeId) {
        vacationTypeRepository.findById(vacationTypeId).orElseThrow(
            () -> new ResourceNotFoundException("Vacation Type does not exists with given id: " + vacationTypeId));
        vacationTypeRepository.deleteById(vacationTypeId);
    }

}
