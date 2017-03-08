package michalski.kamil.service;

import michalski.kamil.domain.Product;

import java.util.List;
import java.util.Set;

/**
 * Created by ppg38 on 08.03.2017.
 */
public interface ProductService {
    Set<Product> getProductByCategory(String category);
}
