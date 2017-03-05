package michalski.kamil.dao;

import michalski.kamil.domain.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductDao  extends CrudRepository<Product,Long> {
}
