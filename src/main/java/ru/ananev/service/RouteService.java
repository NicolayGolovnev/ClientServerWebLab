package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.Route;
import ru.ananev.repository.RouteRepository;

import java.util.Optional;

@Service
@Slf4j
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    /**
     * Процедура создания нового маршрута
     *
     * @param route новый маршрут
     */
    @Transactional
    public void save(Route route) {
        try {
            routeRepository.save(route);
            log.info("ROUTE SAVED");
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Процедура обновления маршрута
     *
     * @param route маршрут
     */
    @Transactional
    public void update(Route route) {
        Optional<Route> optionalRoute = routeRepository.findById(route.getId());
        if (optionalRoute.isPresent()) {
            routeRepository.save(route);
            log.info("ROUTE WITH ID " + route.getId() + "UPDATED");
        }
        else
            throw new RuntimeException("Маршрут с ID = " + route.getId() + " не найден");
    }

    /**
     * Процедура удаления маршрута
     *
     * @param routeID ID маршрута
     */
    @Transactional
    public void delete(Long routeID) {
        Optional<Route> optionalRoute = routeRepository.findById(routeID);
        if (optionalRoute.isPresent()) {
            routeRepository.delete(optionalRoute.get());
        }
        else
            throw new RuntimeException("Маршрут с ID = " + routeID + " не найден");
    }

}
