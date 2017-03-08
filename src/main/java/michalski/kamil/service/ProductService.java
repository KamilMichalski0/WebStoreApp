package michalski.kamil.service;

import michalski.kamil.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ProductService {
    Set<Product> getProductByCategory(String category);
    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
}
