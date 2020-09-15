package ru.jsf_app.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.swing.*;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    public ProductRepository() {
    }

    @PostConstruct
    public void init()  {
        logger.info("product repository init() start!!.");
        if (this.findAll().isEmpty()) {
            logger.info("No products in DB. Initializing.");

            try {
                userTransaction.begin();

                this.insert(new Product( "Apple Macbook pro 2015", "Apple profession laptop", new BigDecimal(3000)));
                this.insert(new Product( "Apple Macbook air 2015", "Apple netbook", new BigDecimal(2000)));
                this.insert(new Product( "Apple iPad", "Apple tablet", new BigDecimal(1000)));

                userTransaction.commit();
            }catch (Exception ex){
                try {
                    userTransaction.rollback();
                    ex.printStackTrace();
                } catch (SystemException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Transactional
    public void insert(Product product) {
        entityManager.persist(product);
    }
    @Transactional
    public void update(Product product) {
        entityManager.merge(product);
    }
    @Transactional
    public void delete(long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null){
            entityManager.remove(product);
        }
    }

    public Optional<Product> findById(long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null){
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public List<Product> findAll() {
        return entityManager.createQuery("from Product",Product.class).getResultList();
    }

//    public void addInBasket(Long product_id) throws SQLException {
//        PreparedStatement preparedStatement = null;
//        preparedStatement = conn.prepareStatement(
//                "SELECT \n" +
//                        "    basket.basket_id\n" +
//                        "FROM\n" +
//                        "    basket\n" +
//                        "WHERE\n" +
//                        "    basket.product_id = ?;");
//        preparedStatement.setLong(1 , product_id);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if(resultSet.next()){
//            preparedStatement = conn.prepareStatement(
//                        "UPDATE basket \n" +
//                                "SET \n" +
//                                "    basket.number_of_product = basket.number_of_product + 1\n" +
//                                "WHERE\n" +
//                                "    basket.product_id = ?;"
//                );
//                preparedStatement.setLong(1 , product_id);
//                preparedStatement.executeUpdate();
//        }else{
//            preparedStatement = conn.prepareStatement(
//                    "INSERT INTO basket (product_id, number_of_product) VALUES (?,1);");
//            preparedStatement.setLong(1 , product_id);
//            preparedStatement.executeUpdate();
//        }
//    }

}

