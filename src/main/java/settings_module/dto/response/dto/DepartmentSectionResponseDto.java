package settings_module.dto.response.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentSectionResponseDto {

    private Long seq;
    private Integer deptNo;
    private Integer sectionNo;
    private String name;
    private Integer status;
}
