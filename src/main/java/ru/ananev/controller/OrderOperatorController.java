package ru.ananev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ananev.entity.*;
import ru.ananev.service.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("operator")
@Slf4j
public class OrderOperatorController {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PaymentNoteService paymentNoteService;

    @Autowired
    RouteService routeService;

    @Autowired
    SequenceRouteService sequenceRouteService;

    @Autowired
    PointService pointService;

    @GetMapping("/main_page")
    public ModelAndView loadMainPage() {
        ModelAndView modelAndView = new ModelAndView("operator/main_page");
        List<Order> orderList = orderService.findAll();
        List<Customer> customerList = customerService.findAll();
        modelAndView.addObject("orders", orderList);
        modelAndView.addObject("customerList", customerList);
        log.info("GET - /operator/main_page\tOPENED OPERATOR MAIN PAGE");
        return modelAndView;
    }

    @GetMapping("create_order")
    public ModelAndView createOrderForm(@ModelAttribute("orderForm") Order order){
        ModelAndView mv = new ModelAndView("operator/create_order");
        mv.addObject("points", pointService.findAll());
        mv.addObject("customers", customerService.findAll());
        return mv;
    }

    /**
     * Метод обработки запроса на добавление заказа
     *
     * @param order заказ
     * @return редирект на главную страницу оператора
     */
    @PostMapping("/create_order")
    public ModelAndView createOrder(Order order) {
        log.info("POST - /operator/create_order\tENTERED CREATE ORDER METHOD");
        orderService.save(order);
        log.info("CREATION COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/operator/main_page");
    }

    @GetMapping("/update_order/{id}")
    public ModelAndView updateOrderForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/operator/update_order");
        modelAndView.addObject("order", orderService.findById(id));
        modelAndView.addObject("customers", customerService.findAll());
        modelAndView.addObject("points", pointService.findAll());
        return modelAndView;
    }

    /**
     * Метод обработки запроса на обновление заказа
     *
     * @param order заказ
     * @return редирект на главную страницу оператора
     */
    @PostMapping("/update_order")
    public ModelAndView updateOrder(Order order) {
        log.info("POST - /operator/update_order\tENTERED UPDATE ORDER METHOD");
        orderService.update(order);
        log.info("UPDATING COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/operator/main_page");
    }

    /**
     * Метод обработки запроса на удаление заказа
     *
     * @param id ID заказа
     * @return редирект на главную страницу оператора
     */
    @GetMapping("/delete_order/{id}")
    public ModelAndView deleteOrder(@PathVariable long id) {
        log.info("POST - /operator/delete_order/" + id + "\tENTERED DELETE ORDER METHOD");
        orderService.delete(id);
        log.info("DELETE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/operator/main_page");
    }

    /**
     * Метод обработки запроса на добавление заказчика
     *
     * @param customer заказчик
     * @return редирект на главную страницу оператора
     */
    @PostMapping("create_customer")
    public ModelAndView createCustomer(Customer customer) {
        log.info("POST - /operator/create_customer\tENTERED CREATE CUSTOMER METHOD");
        customerService.save(customer);
        log.info("CREATION COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/operator/main_page");
    }

    /**
     * Метод обработки запроса на обновление заказчика
     *
     * @param customer заказчик
     * @return редирект на главную страницу оператора
     */
    @PostMapping("update_customer")
    public ModelAndView updateCustomer(Customer customer) {
        log.info("POST - /operator/update_customer\tENTERED UPDATE CUSTOMER METHOD");
        customerService.update(customer);
        log.info("UPDATE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/operator/main_page");
    }

    /**
     * Метод обработки запроса на удаление заказчика
     *
     * @param id ID заказчика
     * @return редирект на главную страницу оператора
     */
    @GetMapping("/delete_customer/{id}")
    public ModelAndView deleteCustomer(@PathVariable long id) {
        log.info("POST - /operator/delete_customer/" + id + "\tENTERED DELETE CUSTOMER METHOD");
        customerService.delete(id);
        log.info("DELETE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/operator/main_page");
    }

    /**
     * Метод обработки запроса на загрузку страницы заказа
     *
     * @return ModelAndView для страницы заказа
     */
    @GetMapping("/order/{id}")
    public ModelAndView loadOrderPage(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("order_page");
        List<PaymentNote> paymentNotes = paymentNoteService.findAllByOrderId(id);
        modelAndView.addObject("paymentNotes", paymentNotes);
        log.info("GET - /order/" + id + "\tOPENED ORDER PAGE");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на добавление платежной записи
     *
     * @param paymentNote платежная запись
     * @return редирект на страницу заказа
     */
    @PostMapping("/order/create_note")
    public ModelAndView createNote(PaymentNote paymentNote) {
        log.info("POST - /operator/order/create_note\tENTERED CREATE PAYMENT NOTE METHOD");
        paymentNoteService.save(paymentNote);
        log.info("CREATION COMPLETED\tREDIRECTING TO ORDER PAGE");
        return new ModelAndView("redirect:/operator/order/" + paymentNote.getOrder().getId());
    }

    /**
     * Метод обработки запроса на добавление платежной записи
     *
     * @param paymentNote платежная запись
     * @return редирект на страницу заказа
     */
    @PostMapping("/order/update_note")
    public ModelAndView updateNote(PaymentNote paymentNote) {
        log.info("POST - /operator/order/update_note\tENTERED UPDATE PAYMENT NOTE METHOD");
        paymentNoteService.update(paymentNote);
        log.info("UPDATE COMPLETED\tREDIRECTING TO ORDER PAGE");
        return new ModelAndView("redirect:/operator/order/" + paymentNote.getOrder().getId());
    }

    /**
     * Метод обработки запроса на удаление платежной записи
     *
     * @param paymentNote платежная запись
     * @return редирект на страницу заказа
     */
    @GetMapping("/order/delete_note/")
    public ModelAndView deleteNote(PaymentNote paymentNote) {
        log.info("GET - /operator/order/delete_note/"  + paymentNote.getId() + "\tENTERED DELETE NOTE METHOD");
        paymentNoteService.delete(paymentNote.getId());
        log.info("DELETE COMPLETED\tREDIRECTING TO ORDER PAGE");
        return new ModelAndView("redirect:/operator/order/" + paymentNote.getOrder().getId());
    }

    @GetMapping("/{id}/payment_order")
    public ModelAndView checkPayment(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("/operator/payment_order");
        Order order = orderService.findById(id);
        boolean answer;
        if (order != null) {
            answer = order.getPaymentNotes().stream().mapToInt(PaymentNote::getPayment).sum() == order.getCostDelivery();
        }
        else
            answer = false;

        mv.addObject("answer", (answer) ? "оплачен" : "не оплачен");
        return mv;
    }

    @GetMapping("/get_route_points/{id}")
    public ModelAndView getRoutePoints(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("operator/route");
        String answer;
        List<SequenceRoute> sequenceRoutes = sequenceRouteService.findAllByRouteID(id).stream()
                .sorted(Comparator.comparingInt(SequenceRoute::getOrderNumber))
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (SequenceRoute sequenceRoute : sequenceRoutes)
            stringBuilder.append(sequenceRoute.getPoint().getPointLocation()).append(", ");
        answer = stringBuilder.toString();
        mv.addObject("answer", answer);
        return mv;
    }

}
