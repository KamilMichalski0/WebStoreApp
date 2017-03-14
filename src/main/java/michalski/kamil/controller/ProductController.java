package michalski.kamil.controller;


import michalski.kamil.domain.Product;
import michalski.kamil.service.CategoryService;
import michalski.kamil.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


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


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {

        Product product = new Product();
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("product", product);
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute Product product,
                                           HttpServletRequest httpServletRequest) {
        CommonsMultipartFile productImage = product.getProductImage();
        String rootDirectory = httpServletRequest.getSession().getServletContext().getRealPath("/");
        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(rootDirectory + "resources\\images\\" + product.getName() +
                        ".png"));
            } catch (Exception e) {
                throw new RuntimeException("Niepowodzenie podczas próby zapisu obrazka produktu", e);
            }
        }
        productService.addProduct(product);
        return "redirect:/products";
    }


    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("unitsInOrder", "discontinued", "conditions", "productImage");
    }
}
