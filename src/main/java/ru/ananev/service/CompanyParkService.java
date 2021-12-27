package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.CompanyPark;
import ru.ananev.repository.CompanyParkRepository;

import java.util.Optional;

@Service
@Slf4j
public class CompanyParkService {

    @Autowired
    CompanyParkRepository companyParkRepository;

    /**
     * Процедура добавления парка компании
     *
     * @param companyPark парк компании
     */
    @Transactional
    public void save(CompanyPark companyPark) {
        Optional<CompanyPark> companyParkOptional = companyParkRepository.findCompanyParkByName(companyPark.getName());
        if (companyParkOptional.isPresent())
            throw new RuntimeException("Парк " + companyPark.getName() + " уже существует");
        companyParkRepository.save(companyPark);
        log.info("COMPANY PARK SAVED");
    }

    /**
     * Процедура обновления парка компании
     *
     * @param companyPark парк компании
     */
    @Transactional
    public void update(CompanyPark companyPark) {
        Optional<CompanyPark> companyParkOptional = companyParkRepository.findCompanyParkByName(companyPark.getName());
        if (companyParkOptional.isPresent()) {
            if (companyParkOptional.get().getName().equals(companyPark.getName()))
                throw new RuntimeException("Парк " + companyPark.getName() + " уже существует");
            companyParkRepository.save(companyPark);
            log.info("COMPANY PARK WITH ID " + companyPark.getId() + "UPDATED");
        }
        else
            throw new RuntimeException("Парк " + companyPark.getName() + " не существует");
    }

    /**
     * Процедура удаления парка компании
     *
     * @param companyParkID ID парка компании
     */
    @Transactional
    public void delete(Long companyParkID) {
        Optional<CompanyPark> companyParkOptional = companyParkRepository.findById(companyParkID);
        if (companyParkOptional.isPresent()) {
            companyParkRepository.deleteById(companyParkID);
            log.info("COMPANY PARK WITH ID " + companyParkID + "DELETED");
        }
        else
            throw new RuntimeException("Парк c ID" + companyParkID + " не существует");
    }

}
