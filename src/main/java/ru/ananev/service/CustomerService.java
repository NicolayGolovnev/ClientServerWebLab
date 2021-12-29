package ru.ananev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ananev.entity.Customer;
import ru.ananev.entity.Order;
import ru.ananev.repository.CustomerRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Процедура добавления заказчика
     *
     * @param customer заказчик
     */
    @Transactional
    public void save(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByPassport(customer.getPassport());
        if (customerOptional.isPresent())
            throw new RuntimeException("'Заказчик с паспортом " + customer.getPassport() + " уже существует");
        customerRepository.save(customer);
        log.info("CUSTOMER SAVED");
    }

    /**
     * Процедура обновления заказчика
     *
     * @param customer заказчик
     */
    @Transactional
    public void update(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByPassport(customer.getPassport());
        if (customerOptional.isPresent()) {
            if (Objects.equals(customer.getId(), customerOptional.get().getId()))
                throw new RuntimeException("'Заказчик с паспортом " + customer.getPassport() + " уже существует");
            customerRepository.save(customer);
            log.info("CUSTOMER WITH ID " + customer.getId() + "UPDATED");
        } else
            throw new RuntimeException("'Заказчик с ID = " + customer.getId() + " не существует");
    }

    /**
     * Процедура удаления заказчика
     *
     * @param customerID ID заказчика
     */
    @Transactional
    public void delete(Long customerID) {
        Optional<Customer> customerOptional = customerRepository.findById(customerID);
        if (customerOptional.isPresent()) {
            customerRepository.deleteById(customerID);
            log.info("CUSTOMER WITH ID " + customerID + "DELETED");
        } else
            throw new RuntimeException("Парк c ID" + customerID + " не существует");
    }

    /**
     * Метод поиска всех записей заказчиков
     *
     * @return список заказчиков
     */
    @Transactional
    public List<Customer> findAll() {
        List<Customer> customerList = customerRepository.findAll(Sort.by("id"));
        log.info("FIND ALL CUSTOMERS METHOD DONE");
        return customerList;
    }

    public Customer findById(long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null);
    }

}
