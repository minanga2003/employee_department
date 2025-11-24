package settings_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import settings_module.entity.DepartmentSection;

public interface DepartmentSectionRepository extends JpaRepository<DepartmentSection, Long> {
}
