package settings_module.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "department_section",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"deptNo", "sectionNo"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private Integer deptNo;

    @Column(nullable = false)
    private Integer sectionNo;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private Integer status = 1;
}
