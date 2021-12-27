package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.PaymentNote;
import ru.ananev.entity.Point;
import ru.ananev.repository.PointRepository;

import java.util.Optional;

@Service
@Slf4j
public class PointService {

    @Autowired
    PointRepository pointRepository;

    /**
     * Процедура добавления пункта
     *
     * @param point новый пункт
     */
    @Transactional
    public void save(Point point) {
        Optional<Point> pointOptional = pointRepository.findPointByLocation(point.getLocation());
        if (pointOptional.isPresent())
            throw new RuntimeException("Пункт с локацией " + point.getLocation() + " уже существует.");
        pointRepository.save(point);
        log.info("POINT SAVED");
    }

    /**
     * Процедура обновления пункта
     *
     * @param point пункт
     */
    @Transactional
    public void update(Point point) {
        Optional<Point> pointOptional = pointRepository.findById(point.getId());
        if (pointOptional.isPresent()) {
            if (point.getLocation().equals(pointOptional.get().getLocation()))
                throw new RuntimeException("Пункт с локацией " + point.getLocation() + " уже существует.");
            pointRepository.save(point);
            log.info("POINT WITH ID " + point.getId() + "UPDATED");
        }
    }

    /**
     * Процедура удаления пункта
     *
     * @param pointID ID пункта
     */
    @Transactional
    public void delete(Long pointID) {
        Optional<Point> point = pointRepository.findById(pointID);
        if (point.isPresent()) {
            pointRepository.delete(point.get());
        }
        else
            throw new RuntimeException("Пункт с ID = " + pointID + " не существует");
    }

}
