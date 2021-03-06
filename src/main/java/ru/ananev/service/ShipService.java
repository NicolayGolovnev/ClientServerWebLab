package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.CompanyPark;
import ru.ananev.entity.Ship;
import ru.ananev.repository.CompanyParkRepository;
import ru.ananev.repository.ShipRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShipService {

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    CompanyParkRepository companyParkRepository;

    /**
     * Процедура добавления судна
     *
     * @param ship новое судно
     */
    @Transactional
    public void save(Ship ship) {
        Optional<CompanyPark> companyPark = companyParkRepository.findCompanyParkByName(ship.getPark().getName());
        if (companyPark.isPresent()) {
            shipRepository.save(ship);
            log.info("SHIP SAVED");
        } else
            throw new RuntimeException("Парк " + ship.getPark().getName() + " не найден");
    }

    /**
     * Процедура обновления судна
     *
     * @param ship судно
     */
    @Transactional
    public void update(Ship ship) {
        Optional<Ship> shipOptional = shipRepository.findById(ship.getId());
        if (shipOptional.isPresent()) {
            ship.setPark(shipOptional.get().getPark());
            Optional<CompanyPark> companyPark = companyParkRepository.findCompanyParkByName(ship.getPark().getName());
            if (!companyPark.isPresent())
                throw new RuntimeException("Парк " + ship.getPark().getName() + " не найден");
            shipRepository.save(ship);
            log.info("SHIP WITH ID " + ship.getId() + "UPDATED");
        }
    }

    /**
     * Процедура удаления судна
     *
     * @param shipID ID судна
     */
    @Transactional
    public void delete(Long shipID) {
        Optional<Ship> ship = shipRepository.findById(shipID);
        if (ship.isPresent()) {
            shipRepository.deleteById(shipID);
            log.info("SHIP WITH ID " + shipID + "DELETED");
        } else
            throw new RuntimeException("Судно с ID = " + shipID + " не существует");
    }

    /**
     * Метод поиска всех записей судов
     *
     * @return список судов
     */
    @Transactional
    public List<Ship> findAll() {
        List<Ship> ships = shipRepository.findAll(Sort.by("id"));
        log.info("FIND ALL SHIPS METHOD DONE");
        return ships;
    }

    @Transactional
    public Ship findById(Long id) {
        Optional<Ship> ship = shipRepository.findById(id);
        if (ship.isPresent())
            return ship.get();
        else
            throw new RuntimeException("Ship[id = " + id + "] not found");
    }

}
