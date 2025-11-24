package settings_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import settings_module.entity.DepartmentSection;

@Repository
public interface DepartmentSectionRepository extends JpaRepository<DepartmentSection, Long> {
}