package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.Order;
import ru.ananev.repository.CustomerRepository;
import ru.ananev.repository.OrderRepository;
import ru.ananev.repository.PointRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PointRepository pointRepository;

    /**
     * Процедура добавления записи заказа
     *
     * @param order новый заказ
     */
    @Transactional
    public void save(Order order) {
        try {
            checkOrder(order);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        orderRepository.save(order);
        log.info("ORDER SAVED");
    }

    /**
     * Процедура обновления записи заказа
     *
     * @param order заказ
     */
    @Transactional
    public void update(Order order) {
        try {
            order.setCustomer(customerRepository.findById(order.getCustomer().getId()).get());
            order.setPointArrival(pointRepository.findById(order.getPointArrival().getId()).get());
            order.setPointDeparture(pointRepository.findById(order.getPointDeparture().getId()).get());
            checkOrder(order);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        if (optionalOrder.isPresent()) {
            order.getPaymentNotes().addAll(optionalOrder.get().getPaymentNotes());
            orderRepository.save(order);
            log.info("ORDER WITH ID " + order.getId() + "UPDATED");
        }
    }

    /**
     * Процедура удаления заказа
     *
     * @param orderID ID заказа
     */
    @Transactional
    public void delete(Long orderID) {
        Optional<Order> orderOptional = orderRepository.findById(orderID);
        if (orderOptional.isPresent()) {
            orderRepository.delete(orderOptional.get());
            log.info("DOCUMENT NOTE WITH ID " + orderID + "DELETED");
        } else
            throw new RuntimeException("Запись заказа с ID = " + orderID
                    + " не существует");
    }

    /**
     * Процедура проверки заказа на корректность
     *
     * @param order заказ
     * @throws RuntimeException если что-то некорректно
     */
    private void checkOrder(Order order) throws RuntimeException {
        var customer = customerRepository.findById(order.getCustomer().getId());
        if (!customer.isPresent())
            throw new RuntimeException("Заказчик с паспортом " + order.getCustomer().getPassport() + " не найден");
        var pointDep = pointRepository.findPointByPointLocation(order.getPointDeparture().getPointLocation());
        if (!pointDep.isPresent())
            throw new RuntimeException("Пункт отправки с локацией " + order.getPointDeparture().getPointLocation()
                    + " не найден");
        var pointArr = pointRepository.findPointByPointLocation(order.getPointArrival().getPointLocation());
        if (!pointArr.isPresent())
            throw new RuntimeException("Пункт доставки с локацией " + order.getPointArrival().getPointLocation()
                    + " не найден");
        if (pointDep.get().equals(pointArr.get()))
            throw new RuntimeException("Пункты отправки и доставки имеют одинаковую локацию: "
                    + order.getPointArrival().getPointLocation());
    }

    public List<Order> findAll() {
        return orderRepository.findAll(Sort.by("id"));
    }

    public Order findById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            log.info("FIND ORDER BY ID METHOD DONE");
            return order.get();
        }
        return null;
    }

    public List<Order> findAllByCustomerId(long id) {
        List<Order> orders = orderRepository.findAllByCustomerId(id);
        log.info("FIND ORDERS BY CUSTOMER ID METHOD DONE");
        return orders;
    }

}
