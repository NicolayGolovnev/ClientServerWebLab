package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.Order;
import ru.ananev.entity.PaymentNote;
import ru.ananev.repository.OrderRepository;
import ru.ananev.repository.PaymentNoteRepository;

import java.util.Optional;

@Service
@Slf4j
public class PaymentNoteService {

    @Autowired
    PaymentNoteRepository paymentNoteRepository;

    @Autowired
    OrderRepository orderRepository;

    /**
     * Процедура добавления записи об оплате заказа
     *
     * @param paymentNote новая запись
     */
    @Transactional
    public void save(PaymentNote paymentNote) {
        Optional<Order> order = orderRepository.findById(paymentNote.getOrder().getId());
        if (!order.isPresent())
            throw new RuntimeException("Заказ с ID = " + paymentNote.getOrder().getId() + " не найден");
        paymentNoteRepository.save(paymentNote);
        log.info("PAYMENT NOTE SAVED");
    }

    /**
     * Процедура обновления записи об оплате заказа
     *
     * @param paymentNote новая запись
     */
    @Transactional
    public void update(PaymentNote paymentNote) {
        Optional<Order> order = orderRepository.findById(paymentNote.getOrder().getId());
        if (!order.isPresent())
            throw new RuntimeException("Заказ с ID = " + paymentNote.getOrder().getId() + " не найден");
        Optional<PaymentNote> paymentNoteOptional = paymentNoteRepository.findById(paymentNote.getId());
        if (paymentNoteOptional.isPresent()) {
            paymentNoteRepository.save(paymentNote);
            log.info("PAYMENT NOTE WITH ID " + paymentNote.getId() + "UPDATED");
        }
    }

    /**
     * Процедура удаления записи об оплате заказа
     *
     * @param paymentNoteID ID записи об оплате заказа
     */
    @Transactional
    public void delete(Long paymentNoteID) {
        Optional<PaymentNote> paymentNote = paymentNoteRepository.findById(paymentNoteID);
        if (paymentNote.isPresent()) {
            paymentNoteRepository.delete(paymentNote.get());
        }
        else
            throw new RuntimeException("Запись об оплате заказа с ID = " + paymentNoteID + " не существует");
    }

}
