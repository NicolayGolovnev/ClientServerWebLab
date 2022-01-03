package ru.ananev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ananev.entity.Point;
import ru.ananev.entity.Route;
import ru.ananev.entity.SequenceRoute;
import ru.ananev.service.PointService;
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

    @Autowired
    PointService pointService;

    /**
     * Метод обработки запроса на загрузку главной страницы главного менеджера
     *
     * @return ModelAndView для главной страницы главного менеджера
     */
    @GetMapping("/main_page")
    public ModelAndView loadMainPage() {
        ModelAndView modelAndView = new ModelAndView("general_manager/main_page");
        List<Route> routeList = routeService.findAll();
        modelAndView.addObject("routes", routeList);
        modelAndView.addObject("points", pointService.findAll());
        log.info("GET - /general_manager/main_page\tOPENED GENERAL MANAGER MAIN PAGE");
        return modelAndView;
    }

    @GetMapping("/create_route")
    public ModelAndView createRouteForm(@ModelAttribute("routeForm") Route route) {
        return new ModelAndView("general_manager/create_route");
    }

    /**
     * Метод обработки запроса на добавление маршрута
     *
     * @param route парк
     * @return редирект на главную страницу главного менеджера
     */
    @PostMapping("/create_route")
    public ModelAndView createRoute(Route route) {
        log.info("POST - /general_manager/create_route\tENTERED CREATE ROUTE METHOD");
        routeService.save(route);
        log.info("CREATION COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/general_manager/main_page");
    }

    @GetMapping("/update_route/{id}")
    public ModelAndView updateRoute(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("general_manager/update_route");
        mv.addObject("route", routeService.findById(id));
        return mv;
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
        return new ModelAndView("redirect:/general_manager/main_page");
    }

    /**
     * Метод обработки запроса на удаление парка
     *
     * @param id ID парка
     * @return редирект на главную страницу директора
     */
    @GetMapping("/delete_route/{id}")
    public ModelAndView deleteRoute(@PathVariable("id") long id) {
        log.info("GET - /general_manager/delete_route/" + id + "\tENTERED DELETE ROUTE METHOD");
        routeService.delete(id);
        log.info("DELETE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/general_manager/main_page");
    }

    /**
     * Метод обработки запроса на загрузку страницы маршрута
     *
     * @return ModelAndView для страницы маршрута
     */
    @GetMapping("/route/{id}")
    public ModelAndView loadRoutePage(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("general_manager/route");
        List<SequenceRoute> sequenceRoutes = sequenceRouteService.findAllByRouteID(id);
        modelAndView.addObject("sequences", sequenceRoutes);
        modelAndView.addObject("routeId", id);
        log.info("GET - /route/" + id + "\tOPENED ROUTE PAGE");
        return modelAndView;
    }

    @GetMapping("/route/{id}/create_sequence")
    public ModelAndView createSequenceForm(@ModelAttribute("sequenceForm") SequenceRoute sequenceRoute, @PathVariable long id) {
        ModelAndView mv = new ModelAndView("general_manager/create_sequence");
        mv.addObject("points", pointService.findAll());
        mv.addObject("routes", routeService.findAll());
        mv.addObject("sequenceId", id);
        return mv;
    }

    /**
     * Метод обработки запроса на добавление последовательности
     *
     * @param sequenceRoute последовательность
     * @return редирект на страницу маршрута
     */
    @PostMapping("/route/create_sequence")
    public ModelAndView createSequence(SequenceRoute sequenceRoute) {
        log.info("POST - /general_manager/route/create_sequence\tENTERED CREATE SEQUENCE METHOD");
        sequenceRouteService.save(sequenceRoute);
        log.info("CREATION COMPLETED\tREDIRECTING TO ROUTE PAGE");
        return new ModelAndView("redirect:/general_manager/route/" + sequenceRoute.getRoute().getId());
    }

    @GetMapping("/route/update_sequence/{id}")
    public ModelAndView updSequence(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/general_manager/update_sequence");
        mv.addObject("sequence", sequenceRouteService.findById(id));
        mv.addObject("routes", routeService.findAll());
        mv.addObject("points", pointService.findAll());
        return mv;
    }

    /**
     * Метод обработки запроса на обновление последовательности
     *
     * @param sequenceRoute последовательность
     * @return редирект на страницу маршрута
     */
    @PostMapping("/route/update_sequence")
    public ModelAndView updateSequence(SequenceRoute sequenceRoute) {
        log.info("POST - /general_manager/route/update_sequence\tENTERED UPDATE SEQUENCE METHOD");
        sequenceRouteService.update(sequenceRoute);
        log.info("UPDATING COMPLETED\tREDIRECTING TO ROUTE PAGE");
        return new ModelAndView("redirect:/general_manager/route/" + sequenceRoute.getRoute().getId());
    }

    @GetMapping("/route/delete_sequence/{id}")
    public ModelAndView deleteSequence(@PathVariable Long id) {
        log.info("POST - /general_manager/route/delete_sequence\tENTERED DELETE SEQUENCE METHOD");
        Long id1 = sequenceRouteService.findById(id).getRoute().getId();
        sequenceRouteService.delete(id);
        log.info("DELETING COMPLETED\tREDIRECTING TO ROUTE PAGE");
        return new ModelAndView("redirect:/general_manager/route/" + id1);
    }

    @GetMapping("/create_point")
    public ModelAndView createPointForm(@ModelAttribute("pointForm") Point point) {
        return new ModelAndView("general_manager/create_point");
    }

    /**
     * Метод обработки запроса на добавление пункта
     *
     * @param point парк
     * @return редирект на главную страницу главного менеджера
     */
    @PostMapping("/create_point")
    public ModelAndView createPoint(Point point) {
        log.info("POST - /general_manager/create_point\tENTERED CREATE POINT METHOD");
        pointService.save(point);
        log.info("CREATION COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/general_manager/main_page");
    }

    @GetMapping("/update_point/{id}")
    public ModelAndView updatePoint(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("general_manager/update_point");
        log.info("POST - /general_manager/update_point\tENTERED UPDATE POINT METHOD");
        mv.addObject("point", pointService.findById(id));
        log.info("UPDATING COMPLETED\tREDIRECTING TO MAIN PAGE");
        return mv;
    }

    /**
     * Метод обработки запроса на обновление пункта
     *
     * @param point парк
     * @return редирект на главную страницу главного менеджера
     */
    @PostMapping("/update_point")
    public ModelAndView updatePoint(Point point) {
        log.info("POST - /general_manager/update_point\tENTERED UPDATE POINT METHOD");
        pointService.update(point);
        log.info("UPDATING COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/general_manager/main_page");
    }

    /**
     * Метод обработки запроса на удаление пункта
     *
     * @param id ID пункта
     * @return редирект на главную страницу директора
     */
    @GetMapping("/delete_point/{id}")
    public ModelAndView deletePoint(@PathVariable("id") long id) {
        log.info("GET - /general_manager/delete_point/" + id + "\tENTERED DELETE POINT METHOD");
        pointService.delete(id);
        log.info("DELETE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/general_manager/main_page");
    }

}
