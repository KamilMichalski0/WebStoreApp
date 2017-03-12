package michalski.kamil.dao;

import michalski.kamil.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductDao  extends JpaRepository<Product,Long> {
}
