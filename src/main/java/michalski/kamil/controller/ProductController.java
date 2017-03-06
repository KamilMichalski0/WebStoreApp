package michalski.kamil.controller;

import michalski.kamil.dao.ProductDao;
import michalski.kamil.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class ProductController {
    @Autowired
    private ProductDao productDao;


    @RequestMapping("/products")
    public String list(Model model) {
        Product iphon = new Product();
        iphon.setName("iPhone");
        iphon.setUnitPrice(new BigDecimal(500));
        iphon.setDescription("Apple iPhone 5s, smartfon z 4-calowym ekranem" +
                "o rozdzielczości 640x1136 i 8-megapikselowym aparatem");
        iphon.setCategory("SmartPhone");
        iphon.setUnitsInStock(1000);
        productDao.save(iphon);

        Product tabletNexus = new Product();
        tabletNexus.setName("Nexus");
        tabletNexus.setUnitPrice(new BigDecimal(300));
        tabletNexus.setDescription("Google Nexus 7 jest najlżejszym 7-calowym " +
                "tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon™ S4 Pro");
                tabletNexus.setCategory("Tablet");
        tabletNexus.setUnitsInStock(1000);
        productDao.save(tabletNexus);

        Product laptopDell = new Product();
        laptopDell.setName("Dell Inspiron");
        laptopDell.setUnitPrice(new BigDecimal(700));
        laptopDell.setDescription("Dell Inspiron, 14-calowy laptop (czarny) " +
                "z procesorem Intel Core 3. generacji");
        laptopDell.setCategory("Laptop");
        laptopDell.setUnitsInStock(1000);
        productDao.save(laptopDell);

        model.addAttribute("products",productDao.findAll());

        return "products";
    }
}
