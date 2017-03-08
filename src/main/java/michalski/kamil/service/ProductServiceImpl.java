package michalski.kamil.service;


import michalski.kamil.dao.ProductDao;
import michalski.kamil.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ProductServiceImpl implements ProductService {

@Autowired
private ProductDao productDao;
    @Override
    public Set<Product> getProductByCategory( String category) {

        Set<Product> productsByCategory = new HashSet<Product>();
        for (Product product : productDao.findAll()) {
            if (category.equalsIgnoreCase(product.getCategory())) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }
}
