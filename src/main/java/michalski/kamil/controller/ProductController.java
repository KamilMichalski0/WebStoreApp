package michalski.kamil.controller;


import michalski.kamil.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;


    @RequestMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }


    @RequestMapping("/products/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
        model.addAttribute("products", productService.getProductByCategory((productCategory)));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") long productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping("/products/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
                                      Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams).toArray());
        return "/products";
    }


    @RequestMapping("/products/{category}/{Price}")
    public String getProductsByPriceAndManufacture(Model model,
                                                   @PathVariable("category") String productCategory,
                                                   @MatrixVariable(pathVar = "Price") Map<String, List<String>> filterParams,
                                                   @RequestParam("manufacturer") String manufacturer) {

        model.addAttribute("products", productService.getProductsByPriceManufactureAndCategory(filterParams,
                manufacturer, productCategory));
        return "/products";
    }
}
