package settings_module.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import settings_module.dto.response.dto.DepartmentSectionResponseDto;
import settings_module.entity.DepartmentSection;
import settings_module.repository.DepartmentSectionRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department_section")
@RequiredArgsConstructor
public class DepartmentSectionController {

    private final DepartmentSectionRepository repo;

    @GetMapping("/departments")
    public List<DepartmentSectionResponseDto> getAllDepartments() {
        return repo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        DepartmentSection::getDeptNo,
                        Collectors.mapping(DepartmentSection::getName, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(e -> DepartmentSectionResponseDto.builder()
                        .deptNo(e.getKey())
                        .name(e.getValue().get(0))
                        .build())
                .toList();
    }

    @GetMapping("/departments/{deptNo}/sections")
    public List<DepartmentSectionResponseDto> getSectionsByDepartment(@PathVariable Integer deptNo) {
        return repo.findAll()
                .stream()
                .filter(ds -> ds.getDeptNo().equals(deptNo))
                .map(ds -> DepartmentSectionResponseDto.builder()
                        .seq(ds.getSeq())
                        .deptNo(ds.getDeptNo())
                        .sectionNo(ds.getSectionNo())
                        .name(ds.getName())
                        .status(ds.getStatus())
                        .build())
                .toList();
    }

    @GetMapping("/departments/{deptNo}/sections/{sectionNo}")
    public DepartmentSectionResponseDto getSection(@PathVariable Integer deptNo, @PathVariable Integer sectionNo) {
        return repo.findAll()
                .stream()
                .filter(ds -> ds.getDeptNo().equals(deptNo) && ds.getSectionNo().equals(sectionNo))
                .findFirst()
                .map(ds -> DepartmentSectionResponseDto.builder()
                        .seq(ds.getSeq())
                        .deptNo(ds.getDeptNo())
                        .sectionNo(ds.getSectionNo())
                        .name(ds.getName())
                        .status(ds.getStatus())
                        .build())
                .orElse(null);
    }
}
