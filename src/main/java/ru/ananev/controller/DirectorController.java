package ru.ananev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ananev.entity.CompanyPark;
import ru.ananev.entity.Ship;
import ru.ananev.service.CompanyParkService;
import ru.ananev.service.ShipService;

import java.util.List;

@RestController
@RequestMapping("/director")
@Slf4j
public class DirectorController {

    @Autowired
    CompanyParkService companyParkService;

    @Autowired
    ShipService shipService;

    /**
     * Метод обработки запроса на загрузку главной страницы директора
     *
     * @return ModelAndView для главной страницы директора
     */
    @GetMapping("/main_page")
    public ModelAndView loadMainPage() {
        ModelAndView modelAndView = new ModelAndView("/director/main_page");
        List<CompanyPark> companyParkList = companyParkService.findAll();
        List<Ship> shipList = shipService.findAll();
        modelAndView.addObject("companyParkList", companyParkList);
        modelAndView.addObject("shipList", shipList);
        log.info("GET - /director/main_page\tOPENED DIRECTOR MAIN PAGE");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на добавление парка
     *
     * @param park парк
     * @return редирект на главную страницу директора
     */
    @PostMapping("/create_park")
    public ModelAndView createPark(CompanyPark park) {
        log.info("POST - /director/add_park\tENTERED CREATE PARK METHOD");
        companyParkService.save(park);
        log.info("CREATION COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/director/main_page");
    }

    /**
     * Метод обработки запроса на обновление парка
     *
     * @param park парк
     * @return редирект на главную страницу директора
     */
    @PostMapping("/update_park")
    public ModelAndView updatePark(CompanyPark park) {
        log.info("POST - /director/update_park\tENTERED UPDATE PARK METHOD");
        companyParkService.update(park);
        log.info("UPDATING COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/director/main_page");
    }

    /**
     * Метод обработки запроса на удаление парка
     *
     * @param id ID парка
     * @return редирект на главную страницу директора
     */
    @GetMapping("/delete_park/{id}")
    public ModelAndView deletePark(@PathVariable("id") long id) {
        log.info("GET - /director/delete_park/" + id + "\tENTERED DELETE PARK METHOD");
        companyParkService.delete(id);
        log.info("DELETE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/director/main_page");
    }

    @GetMapping("/create_ship")
    public ModelAndView newAgent(@ModelAttribute("shipForm") Ship ship) {
        return new ModelAndView("/director/create_ship");
    }

    /**
     * Метод обработки запроса на добавление судна
     *
     * @param ship судно
     * @return редирект на главную страницу директора
     */
    @PostMapping("/create_ship")
    public ModelAndView createShip(Ship ship) {
        log.info("POST - /director/add_ship\tENTERED CREATE SHIP METHOD");
        ship.setPark(companyParkService.findAll().get(0));
        shipService.save(ship);
        log.info("CREATION COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/director/main_page");
    }

    @GetMapping("/ship_update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/director/update_ship");
        Ship ship = shipService.findById(id);
        mv.addObject("ship", ship);
        return mv;
    }

    /**
     * Метод обработки запроса на обновления судна
     *
     * @param ship судно
     * @return редирект на главную страницу директора
     */
    @PostMapping("/ship_update")
    public ModelAndView updateShip(Ship ship) {
        log.info("POST - /director/update\tENTERED UPDATE SHIP METHOD");
        shipService.update(ship);
        log.info("UPDATING COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/director/main_page");
    }

    /**
     * Метод обработки запроса на удаление судна
     *
     * @param id ID судна
     * @return редирект на главную страницу директора
     */
    @GetMapping("/ship_delete/{id}")
    public ModelAndView deleteShip(@PathVariable("id") long id) {
        log.info("GET - /director/delete_ship/" + id + "\tENTERED DELETE SHIP METHOD");
        shipService.delete(id);
        log.info("DELETE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/director/main_page");
    }
}
