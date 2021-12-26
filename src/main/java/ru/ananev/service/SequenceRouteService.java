package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.Route;
import ru.ananev.entity.SequenceRoute;
import ru.ananev.repository.PointRepository;
import ru.ananev.repository.RouteRepository;
import ru.ananev.repository.SequenceRouteRepository;

import java.util.Optional;

@Service
@Slf4j
public class SequenceRouteService {

    @Autowired
    SequenceRouteRepository sequenceRouteRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    RouteRepository routeRepository;

    /**
     * Процедура создания записи порядка следования
     *
     * @param sequenceRoute порядок следования
     */
    @Transactional
    public void save(SequenceRoute sequenceRoute) {
        try {
            checkSequence(sequenceRoute);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        sequenceRouteRepository.save(sequenceRoute);
        log.info("SEQUENCE ROUTE SAVED");
    }

    /**
     * Процедура обновления записи порядка следования
     *
     * @param sequenceRoute порядок следования
     */
    @Transactional
    public void update(SequenceRoute sequenceRoute) {
        try {
            checkSequence(sequenceRoute);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        Optional<SequenceRoute> sequenceRouteOptional = sequenceRouteRepository.findById(sequenceRoute.getId());
        if (sequenceRouteOptional.isPresent()) {
            sequenceRouteRepository.save(sequenceRoute);
            log.info("SEQUENCE ROUTE WITH ID " + sequenceRoute.getId() + "UPDATED");
        } else
            throw new RuntimeException("Порядок следования с ID = " + sequenceRoute.getId() + " не найден");
    }

    /**
     * Процедура удаления записи порядка следования
     *
     * @param sequenceRouteID  ID порядка следования
     */
    @Transactional
    public void delete(Long sequenceRouteID) {
        Optional<SequenceRoute> sequenceRouteOptional = sequenceRouteRepository.findById(sequenceRouteID);
        if (sequenceRouteOptional.isPresent()) {
            sequenceRouteRepository.deleteById(sequenceRouteID);
            log.info("SEQUENCE ROUTE WITH ID " + sequenceRouteID + "DELETED");
        } else
            throw new RuntimeException("Порядок следования с ID = " + sequenceRouteID + " не найден");
    }

    /**
     * Проверка порядка следования на корректность
     *
     * @param sequenceRoute порядок следования
     */
    private void checkSequence(SequenceRoute sequenceRoute) throws RuntimeException {
        var point = pointRepository.findPointByLocation(sequenceRoute.getPoint().getLocation());
        if (!point.isPresent())
            throw new RuntimeException("Пункт отправки с локацией " + sequenceRoute.getPoint().getLocation()
                    + " не найден");
        var optionalRoute = routeRepository.findById(sequenceRoute.getRoute().getId());
        if (!optionalRoute.isPresent())
            throw new RuntimeException("Маршрут с ID = " + sequenceRoute.getRoute().getId() + " не найден");
        if (sequenceRoute.getArrivalDate().after(sequenceRoute.getDispatchDate()))
            throw new RuntimeException("Время прибытия не может быть позже времени отправки");

    }

}
