package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.CompanyPark;
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
            checkOrder(order);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        if (optionalOrder.isPresent()) {
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
        var customer = customerRepository.findCustomerByPassport(order.getCustomer().getPassport());
        if (!customer.isPresent())
            throw new RuntimeException("Заказчик с паспортом " + order.getCustomer().getPassport() + " не найден");
        var pointDep = pointRepository.findPointByLocation(order.getPointDeparture().getLocation());
        if (!pointDep.isPresent())
            throw new RuntimeException("Пункт отправки с локацией " + order.getPointDeparture().getLocation()
                    + " не найден");
        var pointArr = pointRepository.findPointByLocation(order.getPointArrival().getLocation());
        if (!pointArr.isPresent())
            throw new RuntimeException("Пункт доставки с локацией " + order.getPointArrival().getLocation()
                    + " не найден");
        if (pointDep.get().equals(pointArr.get()))
            throw new RuntimeException("Пункты отправки и доставки имеют одинаковую локацию: "
                    + order.getPointArrival().getLocation());
        if (order.getDepartureDate().after(order.getArrivalDate()))
            throw new RuntimeException("Время прибытия не может быть позже времени отправки");
    }

    /**
     * Метод поиска всех записей заказов
     *
     * @return список заказов
     */
    @Transactional
    public List<Order> findAll() {
        List<Order> orderList = orderRepository.findAll(Sort.by("id"));
        log.info("FIND ALL ORDERS METHOD DONE");
        return orderList;
    }

    public Order findById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            log.info("FIND ORDER BY ID METHOD DONE");
            return order.get();
        }
        return null;
    }

}
