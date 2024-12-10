package ait.cohort49.shop_49.repository;

import ait.cohort49.shop_49.model.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CustomerRepositoryHibernate implements CustomerRepository {
    private EntityManager entityManager;

    public CustomerRepositoryHibernate() {
        entityManager = new Configuration().configure("hibernate/postgres.cfg.xml")
                .buildSessionFactory().createEntityManager();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(customer);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Transaction cancelled");
        }
    }

    @Override
    public List<Customer> findAllCustomer() {

        //! from Customer???
        return List.of();
        //return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer findCustomerById(Long id) {
        //return null;
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer findCustomerByName(String name) {
        return entityManager.find(Customer.class, name);
        //return null;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        //return null;
        return entityManager.merge(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        entityManager.remove(customer);
        return customer;
        //return null;
    }

    @Override
    public Customer deleteCustomer(String name) {
        Customer customer = findCustomerByName(name);
        entityManager.remove(customer);
        return customer;
        //return null;
    }

    @Override
    public Customer restoreCustomer(Long id) {
        Customer customer = findCustomerById(id);
        return entityManager.merge(customer);
        //return null;
    }

    @Override
    public int getCustomerCount() {
        return findAllCustomer().size();
    }
}
