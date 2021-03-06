package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.DocumentNotes;
import ru.ananev.repository.DocumentNotesRepository;
import ru.ananev.repository.OrderRepository;
import ru.ananev.repository.TransportationDocumentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentNotesService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TransportationDocumentRepository transportationDocumentRepository;

    @Autowired
    DocumentNotesRepository documentNotesRepository;

    /**
     * Процедура добавления записи строки документа
     *
     * @param documentNotes строка документа
     */
    @Transactional
    public void save(DocumentNotes documentNotes) {
        try {
            checkNote(documentNotes);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        documentNotesRepository.save(documentNotes);
        log.info("DOCUMENT NOTE SAVED");
    }

    /**
     * Процедура обновления записи строки документа
     *
     * @param documentNotes строка документа
     */
    @Transactional
    public void update(DocumentNotes documentNotes) {
        try {
            checkNote(documentNotes);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        Optional<DocumentNotes> documentNotesOptional = documentNotesRepository.findById(documentNotes.getId());
        if (documentNotesOptional.isPresent()) {
            documentNotesRepository.save(documentNotes);
            log.info("DOCUMENT NOTE WITH ID " + documentNotes.getId() + "UPDATED");
        }
    }

    /**
     * Процедура удаления строки документа
     *
     * @param documentNoteID ID строки документа
     */
    @Transactional
    public void delete(Long documentNoteID) {
        Optional<DocumentNotes> documentNotesOptional = documentNotesRepository.findById(documentNoteID);
        if (documentNotesOptional.isPresent()) {
            documentNotesRepository.delete(documentNotesOptional.get());
            log.info("DOCUMENT NOTE WITH ID " + documentNoteID + "DELETED");
        } else
            throw new RuntimeException("Запись строки документа с ID = " + documentNoteID
                    + " не существует");
    }

    /**
     * Процедура проверки, существуют ли документ и заказ в БД
     *
     * @param documentNotes строка документа
     * @throws RuntimeException если что-то не существует
     */
    private void checkNote(DocumentNotes documentNotes) throws RuntimeException {
        var transportationDocument = transportationDocumentRepository
                .findById(documentNotes.getDocument().getId());
        if (!transportationDocument.isPresent())
            throw new RuntimeException("Документ с ID = " + documentNotes.getDocument().getId() + " не найден");
        var order = orderRepository.findById(documentNotes.getOrder().getId());
        if (!order.isPresent())
            throw new RuntimeException("Заказ с ID = " + documentNotes.getOrder().getId() + " не найден");
    }

    /**
     * Метод поиска всех строк документа для документа
     *
     * @param docID ID документа о перевозке
     * @return список строк документа
     */
    public List<DocumentNotes> findAllByDocumentID(Long docID) {
        List<DocumentNotes> documentNotes = documentNotesRepository.findAllByDocumentId(docID).stream()
                .sorted(Comparator.comparingLong(DocumentNotes::getId)).collect(Collectors.toList());
        log.info("FIND ALL DOCUMENT NOTES FOR TRANSPORTATION DOCUMENT WITH ID " + docID + " METHOD DONE");
        return documentNotes;
    }

    public DocumentNotes findById(Long id) {
        Optional<DocumentNotes> note = documentNotesRepository.findById(id);
        if (note.isPresent())
            return note.get();
        else
            throw new RuntimeException("DocumentNote[id = " + id + "] not found");
    }

}
