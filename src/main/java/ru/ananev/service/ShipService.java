package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.CompanyPark;
import ru.ananev.entity.Ship;
import ru.ananev.repository.CompanyParkRepository;
import ru.ananev.repository.ShipRepository;

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
        }
        else
            throw new RuntimeException("Парк " + ship.getPark().getName() + " не найден");
    }

    /**
     * Процедура обновления судна
     *
     * @param ship судно
     */
    @Transactional
    public void update(Ship ship) {
        Optional<CompanyPark> companyPark = companyParkRepository.findCompanyParkByName(ship.getPark().getName());
        if (!companyPark.isPresent())
            throw new RuntimeException("Парк " + ship.getPark().getName() + " не найден");
        Optional<Ship> shipOptional = shipRepository.findById(ship.getId());
        if (shipOptional.isPresent()) {
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
        }
        else
            throw new RuntimeException("Судно с ID = " + shipID + " не существует");
    }

}
