package michalski.kamil.dao;

import michalski.kamil.domain.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerDao extends CrudRepository<Customer,Long> {
}
