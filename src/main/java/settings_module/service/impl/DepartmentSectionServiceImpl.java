package settings_module.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import settings_module.dto.requestDto.DepartmentSectionRequestDto;
import settings_module.dto.responseDto.DepartmentSectionResponseDto;
import settings_module.entity.DepartmentSection;
import settings_module.repository.DepartmentSectionRepository;
import settings_module.service.DepartmentSectionService;
import settings_module.util.CommonMessages;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentSectionServiceImpl implements DepartmentSectionService {

    private final DepartmentSectionRepository repo;
    @Override
    public DepartmentSectionResponseDto create(DepartmentSectionRequestDto dto) {
        try {
            DepartmentSection entity = DepartmentSection.builder()
                    .deptNo(dto.getDeptNo())
                    .sectionNo(dto.getSectionNo())
                    .name(dto.getName())
                    .status(dto.getStatus())
                    .build();

            entity = repo.save(entity);
            return toDto(entity);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex);
        }
    }
    @Override
    public DepartmentSectionResponseDto update(Long seq, DepartmentSectionRequestDto dto) {
        try {
            DepartmentSection entity = repo.findById(seq)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            CommonMessages.RECORD_NOT_FOUND + seq));

            entity.setDeptNo(dto.getDeptNo());
            entity.setSectionNo(dto.getSectionNo());
            entity.setName(dto.getName());
            entity.setStatus(dto.getStatus());

            entity = repo.save(entity);
            return toDto(entity);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex);
        }
    }
    @Override
    public DepartmentSectionResponseDto getOne(Long seq) {
        try {
            DepartmentSection entity = repo.findById(seq)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            CommonMessages.RECORD_NOT_FOUND + seq));

            return toDto(entity);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex);
        }
    }
    @Override
    public boolean delete(Long seq) {
        try {
            if (!repo.existsById(seq)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        CommonMessages.RECORD_NOT_FOUND + seq);
            }
            repo.deleteById(seq);
            return true;
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex);
        }
    }

    @Override
    public List<DepartmentSectionResponseDto> getAll() {
        try {
            return repo.findAll()
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex);
        }
    }
    
    private DepartmentSectionResponseDto toDto(DepartmentSection entity) {
        return DepartmentSectionResponseDto.builder()
                .seq(entity.getSeq())
                .deptNo(entity.getDeptNo())
                .sectionNo(entity.getSectionNo())
                .name(entity.getName())
                .status(entity.getStatus())
                .build();
    }
}