package settings_module.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @Column(name = "emp_no")
    private Long empNo;

    @Column(nullable = false, length = 150)
    private String name;

    private LocalDate dob;

    @Column(name = "dept_no")
    private Long deptNo;

    @Column(name = "section_no")
    private Long sectionNo;

    @Column(length = 150, unique = true)
    private String email;

    @Column(name = "basic_salary")
    private double basicSalary = 0.0;

    @Column(name = "travel_allowance")
    private double travelAllowance = 0.0;

    @Column(name = "other_allowance")
    private double otherAllowance = 0.0;

    @Column(name = "total_salary")
    private double totalSalary = 0.0;

    @Column(nullable = false)
    private Integer active = 1;

    @Column(nullable = false)
    private Integer deleted = 0;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;

    @Column(name = "modified_user")
    private String modifiedUser;

    @Column(name = "modified_datetime")
    private LocalDateTime modifiedDatetime;
}
