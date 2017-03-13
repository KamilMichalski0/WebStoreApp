package michalski.kamil.dao;

import michalski.kamil.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryDao extends JpaRepository<Category,Long> {
}
