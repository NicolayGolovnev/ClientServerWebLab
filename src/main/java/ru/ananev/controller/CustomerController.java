package ru.ananev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ananev.entity.Customer;
import ru.ananev.entity.Order;
import ru.ananev.service.CustomerService;
import ru.ananev.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("customer")
@Slf4j
public class CustomerController {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/main_page")
    public ModelAndView loadMainPage() {
        ModelAndView modelAndView = new ModelAndView("/customer/main_page");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        log.info("GET - /customer/main_page\tOPENED CUSTOMER MAIN PAGE");
        return modelAndView;
    }

    @GetMapping("/personal_page/{id}")
    public ModelAndView loadCustomerPage(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("/customer/personal_page");
        List<Order> orders = orderService.findAllByCustomerId(id);
        Customer customer = customerService.findById(id);
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("customer", customer);
        log.info("GET - /customer/personal_page\tOPENED CUSTOMER PERSONAL PAGE");
        return modelAndView;
    }

}
