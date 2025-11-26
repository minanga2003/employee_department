package settings_module.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import settings_module.dto.requestDto.EmployeeRequestDto;
import settings_module.dto.responseDto.EmployeeResponseDto;
import settings_module.entity.Employee;
import settings_module.repository.DepartmentSectionRepository;
import settings_module.repository.EmployeeRepository;
import settings_module.service.EmployeeService;
import settings_module.util.CommonMessages;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;
    private final DepartmentSectionRepository departmentSectionRepository;

    /** -------------------- CREATE -------------------- **/
    @Override
    public EmployeeResponseDto create(EmployeeRequestDto dto) {
        try {
            validateDepartmentSection(dto.getDeptNo(), dto.getSectionNo());
            validateEmployeeNumber(dto.getEmpNo());
            validateUniqueEmail(dto.getEmail(), null);
            validateActiveFlagOnCreate(dto.getActive());

            double totalSalary = dto.getBasicSalary()
                    + dto.getTravelAllowance()
                    + dto.getOtherAllowance();

            Employee entity = Employee.builder()
                    .empNo(dto.getEmpNo())
                    .name(dto.getName())
                    .dob(dto.getDob())
                    .deptNo(dto.getDeptNo())
                    .sectionNo(dto.getSectionNo())
                    .email(dto.getEmail())
                    .basicSalary(dto.getBasicSalary())
                    .travelAllowance(dto.getTravelAllowance())
                    .otherAllowance(dto.getOtherAllowance())
                    .totalSalary(totalSalary)
                    .active(1)
                    .deleted(0)
                    .createdDatetime(LocalDateTime.now())
                    .build();

            entity = repo.save(entity);

            return toDto(entity);

        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    CommonMessages.EMAIL_ALREADY_EXISTS,
                    ex
            );
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex
            );
        }
    }

    /** -------------------- UPDATE -------------------- **/
    @Override
    public EmployeeResponseDto update(Long empNo, EmployeeRequestDto dto) {
        try {
            Employee entity = repo.findById(empNo)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            CommonMessages.RECORD_NOT_FOUND + empNo
                    ));

            if (dto.getEmpNo() == null) {
                dto.setEmpNo(empNo);
            } else if (!dto.getEmpNo().equals(empNo)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        CommonMessages.EMPLOYEE_NUMBER_MISMATCH
                );
            }

            validateDepartmentSection(dto.getDeptNo(), dto.getSectionNo());
            validateUniqueEmail(dto.getEmail(), empNo);

            double totalSalary = dto.getBasicSalary()
                    + dto.getTravelAllowance()
                    + dto.getOtherAllowance();

            entity.setName(dto.getName());
            entity.setDob(dto.getDob());
            entity.setDeptNo(dto.getDeptNo());
            entity.setSectionNo(dto.getSectionNo());
            entity.setEmail(dto.getEmail());
            entity.setBasicSalary(dto.getBasicSalary());
            entity.setTravelAllowance(dto.getTravelAllowance());
            entity.setOtherAllowance(dto.getOtherAllowance());
            entity.setTotalSalary(totalSalary);
            entity.setActive(dto.getActive());
            entity.setModifiedDatetime(LocalDateTime.now());

            entity = repo.save(entity);

            return toDto(entity);

        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    CommonMessages.EMAIL_ALREADY_EXISTS,
                    ex
            );
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex
            );
        }
    }

    /** -------------------- GET ONE -------------------- **/
    @Override
    public EmployeeResponseDto getOne(Long empNo) {
        try {
            Employee entity = repo.findById(empNo)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            CommonMessages.RECORD_NOT_FOUND + empNo
                    ));

            return toDto(entity);

        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex
            );
        }
    }

    /** -------------------- DELETE -------------------- **/
    @Override
    public boolean delete(Long empNo) {
        try {
            if (!repo.existsById(empNo)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        CommonMessages.RECORD_NOT_FOUND + empNo
                );
            }

            repo.deleteById(empNo);
            return true;

        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CommonMessages.INTERNAL_SERVER_ERROR,
                    ex
            );
        }
    }

    /** -------------------- GET ALL -------------------- **/
    @Override
    public List<EmployeeResponseDto> getAll() {
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
                    ex
            );
        }
    }

    /** -------------------- HELPER MAPPING -------------------- **/
    private EmployeeResponseDto toDto(Employee entity) {
        return EmployeeResponseDto.builder()
                .empNo(entity.getEmpNo())
                .name(entity.getName())
                .dob(entity.getDob())
                .deptNo(entity.getDeptNo())
                .sectionNo(entity.getSectionNo())
                .email(entity.getEmail())
                .basicSalary(entity.getBasicSalary())
                .travelAllowance(entity.getTravelAllowance())
                .otherAllowance(entity.getOtherAllowance())
                .totalSalary(entity.getTotalSalary())
                .active(entity.getActive())
                .deleted(entity.getDeleted())
                .createdUser(entity.getCreatedUser())
                .createdDatetime(entity.getCreatedDatetime())
                .modifiedUser(entity.getModifiedUser())
                .modifiedDatetime(entity.getModifiedDatetime())
                .build();
    }

    private void validateEmployeeNumber(Long empNo) {
        if (empNo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    CommonMessages.EMPLOYEE_NUMBER_REQUIRED
            );
        }

        if (repo.existsById(empNo)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    CommonMessages.EMPLOYEE_NUMBER_ALREADY_EXISTS
            );
        }
    }

    private void validateUniqueEmail(String email, Long excludedEmpNo) {
        if (!StringUtils.hasText(email)) {
            return;
        }

        boolean emailExists = (excludedEmpNo == null)
                ? repo.existsByEmail(email)
                : repo.existsByEmailAndEmpNoNot(email, excludedEmpNo);

        if (emailExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    CommonMessages.EMAIL_ALREADY_EXISTS
            );
        }
    }

    private void validateActiveFlagOnCreate(Integer activeFlag) {
        if (activeFlag != null && activeFlag == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    CommonMessages.EMPLOYEE_MUST_BE_ACTIVE_ON_CREATE
            );
        }
    }

    private void validateDepartmentSection(Long deptNo, Long sectionNo) {
        if (deptNo == null || sectionNo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    CommonMessages.INVALID_DEPARTMENT_SECTION
            );
        }

        Integer dept;
        Integer section;
        try {
            dept = Math.toIntExact(deptNo);
            section = Math.toIntExact(sectionNo);
        } catch (ArithmeticException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    CommonMessages.INVALID_DEPARTMENT_SECTION,
                    ex
            );
        }

        boolean exists = departmentSectionRepository.existsByDeptNoAndSectionNo(dept, section);
        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    CommonMessages.INVALID_DEPARTMENT_SECTION
            );
        }
    }
}
