package settings_module.service;

import settings_module.dto.requestDto.EmployeeRequestDto;
import settings_module.dto.responseDto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto create(EmployeeRequestDto dto);

    EmployeeResponseDto update(Long empNo, EmployeeRequestDto dto);

    EmployeeResponseDto getOne(Long empNo);

    boolean delete(Long empNo);

    List<EmployeeResponseDto> getAll();
}
