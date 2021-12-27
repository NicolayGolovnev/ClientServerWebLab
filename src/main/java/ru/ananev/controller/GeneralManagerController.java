package ru.ananev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ananev.entity.Route;
import ru.ananev.entity.SequenceRoute;
import ru.ananev.service.RouteService;
import ru.ananev.service.SequenceRouteService;

import java.util.List;

@RestController
@RequestMapping("general_manager")
@Slf4j
public class GeneralManagerController {

    @Autowired
    RouteService routeService;

    @Autowired
    SequenceRouteService sequenceRouteService;

    /**
     * Метод обработки запроса на загрузку главной страницы главного менеджера
     *
     * @return ModelAndView для главной страницы главного менеджера
     */
    @GetMapping("/main_page")
    public ModelAndView loadMainPage() {
        ModelAndView modelAndView = new ModelAndView("general_manager_main_page");
        List<Route> routeList = routeService.findAll();
        modelAndView.addObject("routeList", routeList);
        log.info("GET - /general_manager/main_page\tOPENED GENERAL MANAGER MAIN PAGE");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на добавление маршрута
     *
     * @param route парк
     * @return редирект на главную страницу главного менеджера
     */
    @PostMapping("/create_route")
    public ModelAndView createRoute(Route route) {
        log.info("POST - /general_manager/add_route\tENTERED CREATE ROUTE METHOD");
        routeService.save(route);
        log.info("CREATION COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/general/manager_main_page");
    }

    /**
     * Метод обработки запроса на обновление маршрута
     *
     * @param route парк
     * @return редирект на главную страницу главного менеджера
     */
    @PostMapping("/update_route")
    public ModelAndView updateRoute(Route route) {
        log.info("POST - /general_manager/update_route\tENTERED UPDATE ROUTE METHOD");
        routeService.update(route);
        log.info("UPDATING COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/general/manager_main_page");
    }

    /**
     * Метод обработки запроса на уддаление парка
     *
     * @param id ID парка
     * @return редирект на главную страницу директора
     */
    @GetMapping("/delete_route/{id}")
    public ModelAndView deleteRoute(@PathVariable("id") long id) {
        log.info("GET - /general_manager/delete_route/" + id + "\tENTERED DELETE ROUTE METHOD");
        routeService.delete(id);
        log.info("DELETE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/general/manager_main_page");
    }

    /**
     * Метод обработки запроса на загрузку страницы маршрута
     *
     * @return ModelAndView для страницы маршрута
     */
    @GetMapping("/route/{id}")
    public ModelAndView loadRoutePage(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("route_page");
        List<SequenceRoute> sequenceRoutes = sequenceRouteService.findAllByRouteID(id);
        modelAndView.addObject("sequenceRoutes", sequenceRoutes);
        log.info("GET - /route/" + id + "\tOPENED ROUTE PAGE");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на добавление последовательности
     *
     * @param sequenceRoute последовательность
     * @return редирект на главную страницу главного менеджера
     */
    @PostMapping("/route/create_sequence")
    public ModelAndView createSequence(SequenceRoute sequenceRoute) {
        log.info("POST - /general_manager/route/create_sequence\tENTERED CREATE SEQUENCE METHOD");
        sequenceRouteService.save(sequenceRoute);
        log.info("CREATION COMPLETED\tREDIRECTING TO ROUTE PAGE");
        return new ModelAndView("redirect:/route/" + sequenceRoute.getRoute().getId());
    }

    /**
     * Метод обработки запроса на обновление последовательности
     *
     * @param sequenceRoute последовательность
     * @return редирект на главную страницу главного менеджера
     */
    @PostMapping("/route/update_sequence")
    public ModelAndView updateSequence(SequenceRoute sequenceRoute) {
        log.info("POST - /general_manager/route/update_sequence\tENTERED UPDATE SEQUENCE METHOD");
        sequenceRouteService.update(sequenceRoute);
        log.info("UPDATING COMPLETED\tREDIRECTING TO ROUTE PAGE");
        return new ModelAndView("redirect:/route/" + sequenceRoute.getRoute().getId());
    }

    /**
     * Метод обработки запроса на обновление последовательности
     *
     * @param sequenceRoute последовательность
     * @return редирект на главную страницу главного менеджера
     */
    @PostMapping("/route/delete_sequence")
    public ModelAndView deleteSequence(SequenceRoute sequenceRoute) {
        log.info("POST - /general_manager/route/delete_sequence\tENTERED DELETE SEQUENCE METHOD");
        sequenceRouteService.delete(sequenceRoute.getId());
        log.info("DELETING COMPLETED\tREDIRECTING TO ROUTE PAGE");
        return new ModelAndView("redirect:/route/" + sequenceRoute.getRoute().getId());
    }

}
