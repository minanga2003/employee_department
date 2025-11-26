package settings_module.dto.responseDto;

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
