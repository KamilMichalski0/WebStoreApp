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

        Product tablet_Nexus = new Product();
        tablet_Nexus.setName("Nexus");
        tablet_Nexus.setUnitPrice(new BigDecimal(300));
        tablet_Nexus.setDescription("Google Nexus 7 jest najlżejszym 7-calowym " +
                "tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon™ S4 Pro");
                tablet_Nexus.setCategory("Tablet");
        tablet_Nexus.setUnitsInStock(1000);
        productDao.save(tablet_Nexus);

        Product laptop_dell = new Product();
        laptop_dell.setName("Dell Inspiron");
        laptop_dell.setUnitPrice(new BigDecimal(700));
        laptop_dell.setDescription("Dell Inspiron, 14-calowy laptop (czarny) " +
                "z procesorem Intel Core 3. generacji");
        laptop_dell.setCategory("Laptop");
        laptop_dell.setUnitsInStock(1000);
        productDao.save(laptop_dell);

        model.addAttribute("products",productDao.findAll());

        return "products";
    }
}
