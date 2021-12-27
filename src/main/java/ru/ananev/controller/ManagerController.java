package ru.ananev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ananev.entity.DocumentNotes;
import ru.ananev.entity.TransportationDocument;
import ru.ananev.service.DocumentNotesService;
import ru.ananev.service.TransportationDocumentService;

import java.util.List;

@RestController
@RequestMapping("manager")
@Slf4j
public class ManagerController {

    @Autowired
    TransportationDocumentService transportationDocumentService;

    @Autowired
    DocumentNotesService documentNotesService;

    /**
     * Метод обработки запроса на загрузку главной страницы менеджера
     *
     * @return ModelAndView для главной страницы менеджера
     */
    @GetMapping("/main_page")
    public ModelAndView loadMainPage() {
        ModelAndView modelAndView = new ModelAndView("manager_main_page");
        List<TransportationDocument> documentList = transportationDocumentService.findAll();
        modelAndView.addObject("documentList", documentList);
        log.info("GET - /manager/main_page\tOPENED MANAGER MAIN PAGE");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на добавление документа о перевозке
     *
     * @param transportationDocument документ о перевозке
     * @return редирект на главную страницу менеджера
     */
    @PostMapping("/create_transportation_document")
    public ModelAndView createDocument(TransportationDocument transportationDocument) {
        log.info("POST - /manager/create_transportation_document\tENTERED CREATE TRANSPORTATION DOCUMENT METHOD");
        transportationDocumentService.save(transportationDocument);
        log.info("CREATION COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/manager/main_page");
    }

    /**
     * Метод обработки запроса на обновление документа о перевозке
     *
     * @param transportationDocument документ о перевозке
     * @return редирект на главную страницу менеджера
     */
    @PostMapping("/update_transportation_document")
    public ModelAndView updateDocument(TransportationDocument transportationDocument) {
        log.info("POST - /manager/update_transportation_document\tENTERED UPDATE TRANSPORTATION DOCUMENT METHOD");
        transportationDocumentService.update(transportationDocument);
        log.info("UPDATING COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/manager/main_page");
    }

    /**
     * Метод обработки запроса на удаление документа о перевозке
     *
     * @param id ID парка
     * @return редирект на главную страницу директора
     */
    @GetMapping("/delete_transportation_document/{id}")
    public ModelAndView deleteDocument(@PathVariable("id") long id) {
        log.info("GET - /manager/delete_transportation_document/" + id + "\tENTERED DELETE TRANSPORTATION DOCUMENT" +
                " METHOD");
        transportationDocumentService.delete(id);
        log.info("DELETE COMPLETED\tREDIRECTING TO MAIN PAGE");
        return new ModelAndView("redirect:/manager/main_page");
    }

    /**
     * Метод обработки запроса на загрузку страницы документа о перевозке
     *
     * @return ModelAndView для страницы документа о перевозке
     */
    @GetMapping("/transportation_document/{id}")
    public ModelAndView loadDocumentPage(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("document_page");
        List<DocumentNotes> documentNotes = documentNotesService.findAllByDocumentID(id);
        modelAndView.addObject("documentNotes", documentNotes);
        log.info("GET - /transportation_document/" + id + "\tOPENED TRANSPORTATION DOCUMENT PAGE");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на добавление строки документа
     *
     * @param documentNotes строка документа
     * @return редирект на страницу строки документа
     */
    @PostMapping("/transportation_document/create_note")
    public ModelAndView createNote(DocumentNotes documentNotes) {
        log.info("POST - /manager/transportation_document/create_note\tENTERED CREATE NOTE METHOD");
        documentNotesService.save(documentNotes);
        log.info("CREATION COMPLETED\tREDIRECTING TO TRANSPORTATION DOCUMENT PAGE");
        return new ModelAndView("redirect:/transportation_document/" + documentNotes.getDocument().getId());
    }

    /**
     * Метод обработки запроса на обновление строки документа
     *
     * @param documentNotes строка документа
     * @return едирект на страницу строки документа
     */
    @PostMapping("/transportation_document/update_note")
    public ModelAndView updateNote(DocumentNotes documentNotes) {
        log.info("POST - /manager/transportation_document/update_note\tENTERED UPDATE NOTE METHOD");
        documentNotesService.update(documentNotes);
        log.info("UPDATING COMPLETED\tREDIRECTING TO DOCUMENT PAGE");
        return new ModelAndView("redirect:/transportation_document/" + documentNotes.getDocument().getId());
    }

    /**
     * Метод обработки запроса на обновление строки документа
     *
     * @param documentNotes строка документа
     * @return едирект на страницу строки документа
     */
    @PostMapping("/transportation_document/delete_note")
    public ModelAndView deleteNote(DocumentNotes documentNotes) {
        log.info("POST - /manager/transportation_document/delete_note\tENTERED DELETE NOTE METHOD");
        documentNotesService.delete(documentNotes.getId());
        log.info("DELETING COMPLETED\tREDIRECTING TO DOCUMENT PAGE");
        return new ModelAndView("redirect:/transportation_document/" + documentNotes.getDocument().getId());
    }

}
