package michalski.kamil.service;

import michalski.kamil.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ProductService {
    Set<Product> getProductByCategory(String category);

    List<Product> getProductsByManufacturer(String manufacturer);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    Product getProductById(Long id);

    Iterable<Product> getAllProduct();

    Set<Product> getProductsByPriceManufactureAndCategory(Map<String, List<String>> filterParams, String manufacturer,
                                                          String category);

    void addProduct(Product product);
}
