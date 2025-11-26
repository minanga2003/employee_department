package settings_module.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {

    @NotNull(message = "Employee number is required")
    private Long empNo;

    @NotBlank(message = "Name is required")
    private String name;

    private LocalDate dob;

    @NotNull(message = "Department number is required")
    private Long deptNo;

    @NotNull(message = "Section number is required")
    private Long sectionNo;

    private String email;

    private double basicSalary = 0.0;

    private double travelAllowance = 0.0;

    private double otherAllowance = 0.0;

    @NotNull
    private Integer active = 1;
}


