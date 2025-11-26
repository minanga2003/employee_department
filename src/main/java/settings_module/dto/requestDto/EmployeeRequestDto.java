package settings_module.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import static settings_module.util.CommonMessages.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {

    @NotNull(message = EMPLOYEE_NUMBER_REQUIRED)
    private Long empNo;

    @NotBlank(message = "Name" + REQUIRED_FIELD)
    private String name;

    private LocalDate dob;

    @NotNull(message = "Department number" + REQUIRED_FIELD)
    private Long deptNo;

    @NotNull(message = "Section number" + REQUIRED_FIELD)
    private Long sectionNo;

    private String email;

    private double basicSalary = 0.0;

    private double travelAllowance = 0.0;

    private double otherAllowance = 0.0;

    @NotNull(message = "Active flag" + REQUIRED_FIELD)
    private Integer active = 1;
}


