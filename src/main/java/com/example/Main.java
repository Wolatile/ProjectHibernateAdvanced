package com.example;

import com.example.dao.*;
import com.example.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.math.BigDecimal;
import java.time.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Main {
    private final SessionFactory sessionFactory;

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FeatureDAO featureDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");

        sessionFactory = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Feature.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addProperties(properties)
                .buildSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        featureDAO = new FeatureDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();
//        main.customerReturnInventoryToStore();
//        Customer customer = main.createCustomer();
//        Rental rent = main.rentFilm(customer);
        Film film = main.createFilm();
    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = storeDAO.getItems(0, 1).get(0);

            City city = cityDAO.getByName("Zhoushan");

            Address address = new Address();
            address.setAddress("Test street 11");
            address.setCity(city);
            address.setDistrict("Testoviy");
            address.setPhone("88005553535");
            address.setPostalCode("99999");
            addressDAO.create(address);

            Customer customer = new Customer();
            customer.setFirstName("Test");
            customer.setLastName("Testov");
            customer.setEmail("test@mail.ru");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setActive(true);
            customerDAO.create(customer);

            session.getTransaction().commit();
            return customer;
        }
    }

    private Rental rentFilm(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Inventory inventory = inventoryDAO.getItems(0, 1).get(0);

            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setRentalDate(LocalDateTime.now());
            rental.setStaff(customer.getStore().getStaff());
            if (rentalDAO.getByInventory(inventory) == null || rentalDAO.getByInventory(inventory).getReturnDate() != null) {
                rental.setInventory(inventory);
            }
            rentalDAO.create(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(rental.getStaff());
            payment.setRental(rental);
            payment.setAmount(rental.getInventory().getFilm().getRentalRate());
            payment.setPaymentDate(LocalDateTime.now());
            paymentDAO.create(payment);

            session.getTransaction().commit();
            return rental;
        }
    }

    private void customerReturnInventoryToStore() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalDAO.getAnyUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());

            rentalDAO.update(rental);

            session.getTransaction().commit();
        }
    }

    private Film createFilm () {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = new Film();
            film.setTitle("Testovik");
            film.setDescription("testing testovyi testovik");
            film.setReleaseYear(Year.of(2024));
            film.setLanguage(languageDAO.getItems(0, 1).get(0));
            film.setRentalDuration((byte) 5);
            film.setRentalRate(BigDecimal.valueOf(99.99));
            film.setLength((short) 90);
            film.setReplacementCost(BigDecimal.valueOf(999.99));
            film.setRating(Rating.G);
            Set<Feature> features = new HashSet<>();
            features.add(Feature.getFeatureByValue("Trailers"));
            film.setSpecialFeatures(features);
            filmDAO.create(film);

            session.getTransaction().commit();
            return film;
        }
    }
}