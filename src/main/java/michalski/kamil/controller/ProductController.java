package michalski.kamil.controller;



import michalski.kamil.dao.ProductDao;
import michalski.kamil.domain.Product;
import michalski.kamil.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductService productService;


    @RequestMapping("/products")
    public String list(Model model) {
        Product iphon = new Product();
        iphon.setName("iPhone");
        iphon.setUnitPrice(new BigDecimal(500));
        iphon.setDescription("Apple iPhone 5s, smartfon z 4-calowym ekranem" +
                "o rozdzielczości 640x1136 i 8-megapikselowym aparatem");
        iphon.setCategory("SmartPhone");
        iphon.setUnitsInStock(1000);
        iphon.setManufacturer("apple");
        productDao.save(iphon);

        Product tabletNexus = new Product();
        tabletNexus.setName("Nexus");
        tabletNexus.setUnitPrice(new BigDecimal(300));
        tabletNexus.setDescription("Google Nexus 7 jest najlżejszym 7-calowym " +
                "tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon™ S4 Pro");
        tabletNexus.setCategory("Tablet");
        tabletNexus.setUnitsInStock(1000);
        tabletNexus.setManufacturer("Google");
        productDao.save(tabletNexus);

        Product laptopDell = new Product();
        laptopDell.setName("Dell Inspiron");
        laptopDell.setUnitPrice(new BigDecimal(700));
        laptopDell.setDescription("Dell Inspiron, 14-calowy laptop (czarny) " +
                "z procesorem Intel Core 3. generacji");
        laptopDell.setCategory("Laptop");
        laptopDell.setManufacturer("Dell");
        laptopDell.setUnitsInStock(1000);
        productDao.save(laptopDell);

        model.addAttribute("products", productDao.findAll());

        return "products";
    }


    @RequestMapping("/products/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
        model.addAttribute("products", productService.getProductByCategory((productCategory)));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") long productId, Model model) {
        model.addAttribute("product", productDao.findOne(productId));
        return "product";
    }

    @RequestMapping("/products/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
                                      Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams).toArray());
        return "/products";
    }
}
