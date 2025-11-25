package settings_module.service;

import settings_module.dto.requestDto.DepartmentSectionRequestDto;
import settings_module.dto.response.dto.DepartmentSectionResponseDto;

import java.util.List;

public interface DepartmentSectionService {

    DepartmentSectionResponseDto create(DepartmentSectionRequestDto dto);

    DepartmentSectionResponseDto update(Long seq, DepartmentSectionRequestDto dto);

    DepartmentSectionResponseDto getOne(Long seq);

    boolean delete(Long seq);

    List<DepartmentSectionResponseDto> getAll();
}
