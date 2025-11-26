package settings_module.dto.responseDto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {

    private Long empNo;
    private String name;
    private LocalDate dob;
    private Long deptNo;
    private Long sectionNo;
    private String email;
    private double basicSalary;
    private double travelAllowance;
    private double otherAllowance;
    private double totalSalary;
    private Integer active;
    private Integer deleted;
    private String createdUser;
    private LocalDateTime createdDatetime;
    private String modifiedUser;
    private LocalDateTime modifiedDatetime;
}

