package michalski.kamil.controller;

import michalski.kamil.dao.CustomerDao;
import michalski.kamil.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CustomerController {

    @Autowired
    CustomerDao customerDao;
    @RequestMapping("/customers")
    public String listCustomer(Model model){
        Customer customer1 = new Customer();
        customer1.setName("Kamil");
        customer1.setAdress("Warszawa, Górczewska 56");
        customerDao.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Piotr");
        customer2.setAdress("Stare Babice, Warszawaska 33");
        customerDao.save(customer2);

        Customer customer3=new Customer();
        customer3.setName("Aneta");
        customer3.setAdress("Lublin, Wyzwolenia 2");
        customerDao.save(customer3);

        model.addAttribute("customers",customerDao.findAll());

        return "customers";
    }
}
