package michalski.kamil.dao;

import michalski.kamil.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductDao  extends CrudRepository<Product,Long> {
}
