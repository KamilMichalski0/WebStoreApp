package michalski.kamil.service;


import michalski.kamil.dao.ProductDao;
import michalski.kamil.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductServiceImpl implements ProductService {

@Autowired
private ProductDao productDao;
@Autowired
private ProductService productService;

    @Override
    public Set<Product> getProductByCategory(String category) {

        Set<Product> productsByCategory = new HashSet<Product>();
        for (Product product : productDao.findAll()) {
            if (category.equalsIgnoreCase(product.getCategory())) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        Set<Product> productsByBrand = new HashSet<Product>();
        Set<Product> productsByCategory = new HashSet<Product>();
        Set<String> criterias = filterParams.keySet();
        if(criterias.contains("brand")) {
            for(String brandName: filterParams.get("brand")) {
                for(Product product: productDao.findAll()) {
                    if(brandName.equalsIgnoreCase(product.getManufacturer())){
                        productsByBrand.add(product);
                    }
                }
            }
        }
        if(criterias.contains("category")) {
            for(String category: filterParams.get("category")) {
                productsByCategory.addAll(productService.getProductByCategory(category));
            }
        }
        productsByCategory.retainAll(productsByBrand);
        return productsByCategory;
    }
}
