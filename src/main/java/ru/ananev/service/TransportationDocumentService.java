package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.Route;
import ru.ananev.entity.Ship;
import ru.ananev.entity.TransportationDocument;
import ru.ananev.repository.RouteRepository;
import ru.ananev.repository.ShipRepository;
import ru.ananev.repository.TransportationDocumentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransportationDocumentService {

    @Autowired
    TransportationDocumentRepository transportationDocumentRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    ShipRepository shipRepository;

    /**
     * Процедура добавления документа о перевозке
     *
     * @param transportationDocument документ о перевозке
     */
    @Transactional
    public void save(TransportationDocument transportationDocument) {
        try {
            checkDocument(transportationDocument);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        transportationDocumentRepository.save(transportationDocument);
        log.info("TRANSPORTATION DOCUMENT SAVED");
    }

    /**
     * Процедура обновления документа о перевозке
     *
     * @param transportationDocument документ о перевозке
     */
    @Transactional
    public void update(TransportationDocument transportationDocument) {
        try {
            checkDocument(transportationDocument);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        Optional<TransportationDocument> transportationDocumentOptional = transportationDocumentRepository
                .findById(transportationDocument.getId());
        if (transportationDocumentOptional.isPresent()) {
            transportationDocumentRepository.save(transportationDocument);
            log.info("TRANSPORTATION DOCUMENT WITH ID " + transportationDocument.getId() + "UPDATED");
        }
    }

    /**
     * Процедура удаления документа о перевозке
     *
     * @param transportationDocumentID ID документа о перевозке
     */
    @Transactional
    public void delete(Long transportationDocumentID) {
        Optional<TransportationDocument> transportationDocumentOptional = transportationDocumentRepository
                .findById(transportationDocumentID);
        if (transportationDocumentOptional.isPresent()) {
            transportationDocumentRepository.deleteById(transportationDocumentID);
            log.info("TRANSPORTATION DOCUMENT WITH ID " + transportationDocumentID + "DELETED");
        } else
            throw new RuntimeException("Документ о перевозке с ID = " + transportationDocumentID + " не существует");
    }

    /**
     * Процедура проверки документа о перевозке на корректнось
     *
     * @param transportationDocument документ о перевозке
     * @throws RuntimeException если что-то некорректно
     */
    private void checkDocument(TransportationDocument transportationDocument) throws RuntimeException {
        Optional<Ship> ship = shipRepository.findById(transportationDocument.getShip().getId());
        if (!ship.isPresent())
            throw new RuntimeException("Судно с ID = " + transportationDocument.getShip().getId() + " не существует");
        Optional<Route> route = routeRepository.findById(transportationDocument.getRoute().getId());
        if (!route.isPresent())
            throw new RuntimeException("Маршрут с ID = " + transportationDocument.getRoute().getId() + "не найден");
    }

    /**
     * Метод поиска всех документов о перевозке
     *
     * @return список документов о перевозке
     */
    @Transactional
    public List<TransportationDocument> findAll() {
        List<TransportationDocument> transportationDocuments = transportationDocumentRepository.findAll(Sort.by("id"));
        log.info("FIND ALL TRANSPORTATION DOCUMENTS METHOD DONE");
        return transportationDocuments;
    }

}
