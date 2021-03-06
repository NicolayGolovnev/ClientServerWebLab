package ru.ananev.controller;

import lombok.extern.slf4j.Slf4j;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ananev.entity.*;
import ru.ananev.service.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("manager")
@Slf4j
public class ManagerController {

    @Autowired
    TransportationDocumentService transportationDocumentService;

    @Autowired
    DocumentNotesService documentNotesService;

    @Autowired
    ShipService shipService;

    @Autowired
    RouteService routeService;

    @Autowired
    SequenceRouteService sequenceRouteService;

    @Autowired
    OrderService orderService;

    /**
     * Метод обработки запроса на загрузку главной страницы менеджера
     *
     * @return ModelAndView для главной страницы менеджера
     */
    @GetMapping("/main_page")
    public ModelAndView loadMainPage() {
        ModelAndView modelAndView = new ModelAndView("/manager/main_page");
        List<TransportationDocument> documentList = transportationDocumentService.findAll();
        modelAndView.addObject("documentList", documentList);
        log.info("GET - /manager/main_page\tOPENED MANAGER MAIN PAGE");
        return modelAndView;
    }

    @GetMapping("/create_transportation_document")
    public ModelAndView createDocumentForForm(@ModelAttribute("document") TransportationDocument document) {
        ModelAndView mv = new ModelAndView("/manager/create_transportation_document");
        List<Ship> ships = shipService.findAll();
        mv.addObject("shipList", ships);
        List<Route> routes = routeService.findAll();
        mv.addObject("routeList", routes);
        return mv;
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

    @GetMapping("/update_transportation_document/{id}")
    public ModelAndView updateDocumentForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/manager/update_transportation_document");
        TransportationDocument document = transportationDocumentService.findById(id);
        mv.addObject("document", document);
        List<Ship> ships = shipService.findAll();
        mv.addObject("shipList", ships);
        List<Route> routes = routeService.findAll();
        mv.addObject("routeList", routes);
        return mv;
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
        ModelAndView modelAndView = new ModelAndView("/manager/transportation_document");
        List<DocumentNotes> documentNotes = documentNotesService.findAllByDocumentID(id);
        modelAndView.addObject("documentNotes", documentNotes);
        modelAndView.addObject("documentId", id);
        modelAndView.addObject("documentNum", transportationDocumentService.findById(id).getDocNumber());
        log.info("GET - /transportation_document/" + id + "\tOPENED TRANSPORTATION DOCUMENT PAGE");
        return modelAndView;
    }

    @GetMapping("/transportation_document/{id}/create_note")
    public ModelAndView createNoteForm(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("/manager/create_note");
        DocumentNotes note = new DocumentNotes();
        note.setDocument(transportationDocumentService.findById(id));
        mv.addObject("note", note);
        mv.addObject("documentId", id);
        List<Order> orders = orderService.findAll();
        mv.addObject("orderList", orders);
        return mv;
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
        long id = documentNotes.getDocument().getId();
        documentNotesService.save(documentNotes);
        log.info("CREATION COMPLETED\tREDIRECTING TO TRANSPORTATION DOCUMENT PAGE");
        return new ModelAndView("redirect:/manager/transportation_document/" + id);
    }

    @GetMapping("/transportation_document/update_note/{id}")
    public ModelAndView updateNoteForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/manager/update_note");
        DocumentNotes note = documentNotesService.findById(id);
        mv.addObject("note", note);
        List<Order> orders = orderService.findAll();
        mv.addObject("orderList", orders);
        return mv;
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
        return new ModelAndView("redirect:/manager/transportation_document/" + documentNotes.getDocument().getId());
    }

    @GetMapping("/transportation_document/delete_note/{id}")
    public ModelAndView deleteNote(@PathVariable Long id) {
        log.info("POST - /manager/transportation_document/delete_note\tENTERED DELETE NOTE METHOD");
        long idDocument = documentNotesService.findById(id).getDocument().getId();
        documentNotesService.delete(id);
        log.info("DELETING COMPLETED\tREDIRECTING TO DOCUMENT PAGE");
        return new ModelAndView("redirect:/manager/transportation_document/" + idDocument);
    }

    @GetMapping("/ship_list")
    public ModelAndView getListOfShip() {
        ModelAndView mv = new ModelAndView("/manager/ship_list");
        List<Ship> ships = shipService.findAll();
        mv.addObject("shipList", ships);
        return mv;
    }

    @GetMapping("/route_list")
    public ModelAndView getListOfRoute() {
        ModelAndView mv = new ModelAndView("/manager/route_list");
        List<Route> routes = routeService.findAll();
        mv.addObject("routeList", routes);
        return mv;
    }

    @GetMapping("/sequence_route/{id}")
    public ModelAndView getSequenceRoutes(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/manager/sequence_route");
        List<SequenceRoute> sequenceRoutes = sequenceRouteService.findAllByRouteID(id);
        mv.addObject("sequenceList", sequenceRoutes);
        return mv;
    }

    @GetMapping("/transportation_document/{id}/create_report")
    public ModelAndView createReport(@PathVariable Long id) throws Docx4JException {
        TransportationDocument document = transportationDocumentService.findById(id);
        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

        mainDocumentPart.addStyledParagraphOfText("Title", "Документ о перевозке №" + document.getDocNumber());

        mainDocumentPart.addParagraphOfText("Судно:\n");
        mainDocumentPart.addParagraphOfText("\tГрузоподъемность: " + document.getShip().getLiftingCapacity());
        mainDocumentPart.addParagraphOfText("\tПроходимость: " + document.getShip().getPassability());
        mainDocumentPart.addParagraphOfText("\tЦена: " + document.getShip().getPrice());
        mainDocumentPart.addParagraphOfText("\tСостояние: " + document.getShip().getState());
        mainDocumentPart.addParagraphOfText(null);

        mainDocumentPart.addParagraphOfText("Маршрут следования:\n");
        Route route = document.getRoute();
        for (int i = 0; i < route.getSequenceRoutes().size(); i++) {
            SequenceRoute sequenceRoute = route.getSequenceRoutes().get(i);
            mainDocumentPart.addParagraphOfText("\tПункт: " + sequenceRoute.getPoint().getPointLocation() +
                    " Время прибытия: " + sequenceRoute.getArrivalDate() +
                    " Время отправки: " + sequenceRoute.getDispatchDate());
        }

        mainDocumentPart.addParagraphOfText("Строчки документа:\n");
        List<DocumentNotes> notes = documentNotesService.findAllByDocumentID(id);
        for(int i = 0; i < notes.size(); i++) {
            DocumentNotes note = notes.get(i);
            mainDocumentPart.addParagraphOfText((i + 1) + ": " +
                    "Заказ: " + note.getOrder().getCargo());
            mainDocumentPart.addParagraphOfText("\tДата отправки: " + note.getOrder().getDepartureDate());
            mainDocumentPart.addParagraphOfText("\tПункт отправки: " + note.getOrder().getPointDeparture().getPointLocation());
            mainDocumentPart.addParagraphOfText("\tДата прибытия: " + note.getOrder().getArrivalDate());
            mainDocumentPart.addParagraphOfText("\tПункт прибытия: " + note.getOrder().getPointArrival().getPointLocation());
            mainDocumentPart.addParagraphOfText("\tВес груза: " + note.getOrder().getCargoWeight());
            mainDocumentPart.addParagraphOfText("\tСтоимость доставки: " + note.getOrder().getCostDelivery());
            mainDocumentPart.addParagraphOfText(null);
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        String userHome = System.getProperty("user.home");
        File exportFile = new File(userHome + "\\Downloads\\document_report_"
                + simpleDateFormat.format(new Date()) + ".docx");
        wordPackage.save(exportFile);
        return new ModelAndView("redirect:/manager/transportation_document/" + id);
    }

}
