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

        Set<Product> productsByCategory = new HashSet<>();
        for (Product product : productDao.findAll()) {
            if (category.equalsIgnoreCase(product.getCategory().getName())) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }

    @Override
    public List<Product> getProductsByManufacturer(String manufacturer) {
        List<Product> productByManufacture = new ArrayList<>();
        for (Product product : productDao.findAll()) {
            if (manufacturer.equalsIgnoreCase(product.getManufacturer())) {
                productByManufacture.add(product);
            }

        }
        return productByManufacture;
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        Set<Product> productsByBrand = new HashSet<>();
        Set<Product> productsByCategory = new HashSet<>();
        Set<String> criterias = filterParams.keySet();
        if (criterias.contains("brand")) {
            for (String brandName : filterParams.get("brand")) {
                for (Product product : productDao.findAll()) {
                    if (brandName.equalsIgnoreCase(product.getManufacturer())) {
                        productsByBrand.add(product);
                    }
                }
            }
        }
        if (criterias.contains("category")) {
            for (String category : filterParams.get("category")) {
                productsByCategory.addAll(productService.getProductByCategory(category));
            }
        }
        productsByCategory.retainAll(productsByBrand);
        return productsByCategory;
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.findOne(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.findAll();
    }

    @Override
    public Set<Product> getProductsByPriceManufactureAndCategory(Map<String, List<String>> filterParams, String manufacturer,
                                                                 String category) {
        Set<Product> productByCategory = new HashSet<>();
        productByCategory = productService.getProductByCategory(category);
        Set<Product> productByPrice = new HashSet<>();


        Set<String> criterias = filterParams.keySet();
        if (criterias.contains("low") && criterias.contains("high")) {
            for (String priceHigh : filterParams.get("high")) {
                int high = Integer.valueOf(priceHigh);
                for (String priceLow : filterParams.get("low")) {
                    int low = Integer.valueOf(priceLow);
                    for (Product product : productByCategory) {
                        if (product.getUnitPrice() > low && product.getUnitPrice() < high) {
                            productByPrice.add(product);
                        }
                    }
                }
            }
        }
        Set<Product> productBySort = new HashSet<>();
        for (Product product : productByPrice) {
            if (manufacturer.equalsIgnoreCase(product.getManufacturer())) {
                productBySort.add(product);
            }

        }


        return productBySort;
    }

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }
}
