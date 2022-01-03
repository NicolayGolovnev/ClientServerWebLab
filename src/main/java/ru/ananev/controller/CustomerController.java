package ru.ananev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ananev.entity.Customer;
import ru.ananev.entity.Order;
import ru.ananev.entity.PaymentNote;
import ru.ananev.entity.Ship;
import ru.ananev.service.CustomerService;
import ru.ananev.service.OrderService;
import ru.ananev.service.PaymentNoteService;

import java.util.List;

@RestController
@RequestMapping("customer")
@Slf4j
public class CustomerController {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PaymentNoteService paymentNoteService;

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

    @GetMapping("/create")
    public ModelAndView createCustomerForm(@ModelAttribute("customerForm") Customer customer) {
        return new ModelAndView("/customer/create_page");
    }

    @PostMapping("/add_new")
    public ModelAndView createCustomer(Customer customer) {
        customerService.save(customer);
        return new ModelAndView("redirect:/customer/main_page");
    }

    @GetMapping("/{id}/state_order")
    public ModelAndView checkState(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/customer/state_order");
        Order order = orderService.findById(id);
        mv.addObject("order", order);
        mv.addObject("customer", order.getCustomer());
        return mv;
    }

    @GetMapping("/{id}/pay_page")
    public ModelAndView payOrderForm(@ModelAttribute("paymentForm") PaymentNote payment, @PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/customer/pay_page");
        Order order = orderService.findById(id);
        mv.addObject("order", order);
        mv.addObject("customer", order.getCustomer());
        return mv;
    }

    @PostMapping("/pay_page")
    public ModelAndView payOrder(PaymentNote payment) {
        paymentNoteService.save(payment);
        Customer customer = orderService.findById(payment.getOrder().getId()).getCustomer();
        ModelAndView mv = new ModelAndView("redirect:/customer/personal_page/" + customer.getId());
//        mv.addObject("customer", customer);
//        mv.addObject("orders", orderService.findAllByCustomerId(customer.getId()));
        return mv;
    }

    @GetMapping("/{customer_id}/order/{id}/payment_page")
    public ModelAndView getPaymentPage(@PathVariable long id, @PathVariable long customer_id) {
        ModelAndView modelAndView = new ModelAndView("customer/order_payment");
        List<PaymentNote> payments = paymentNoteService.findAllByOrderId(id);
        modelAndView.addObject("payments", payments);
        modelAndView.addObject("orderId", id);
        modelAndView.addObject("customer_id", customer_id);
        return modelAndView;
    }

}
