package settings_module.dto.requestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentSectionRequestDto {

    @NotNull
    private Integer deptNo;

    @NotNull
    private Integer sectionNo;

    @NotBlank
    private String name;

    @NotNull
    private Integer status;
}
