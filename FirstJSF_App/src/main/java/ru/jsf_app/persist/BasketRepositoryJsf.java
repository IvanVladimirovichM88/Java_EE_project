package ru.jsf_app.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class BasketRepositoryJsf implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(BasketRepositoryJsf.class);
    @Inject
    private ServletContext servletContext;
    private Connection connection;

    public BasketRepositoryJsf(){
        logger.info("\n --> BasketRepositoryJsf constructor");
    }

    @PostConstruct
    public void init() {
        logger.info("\n --> Start init in BasketRepository");

        connection = (Connection)servletContext.getAttribute("connection");

        try {
            logger.info( "\n -->  connection.getSchema()  ---> "  + connection.getSchema() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BasketProductJsf> findAll() throws SQLException {
        List<BasketProductJsf> res = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                    "SELECT \n" +
                    "    `products`.`id` AS idProduct,\n" +
                    "    `products`.`name` AS nameProduct,\n" +
                    "    `products`.`description` AS descriptionProduct,\n" +
                    "    `products`.`price` AS priceProduct,\n" +
                    "    basket.basket_id AS idProductInBasket, \n" +
                    "    basket.number_of_product AS numberOfProduct\n" +
                    "FROM\n" +
                    "    basket\n" +
                    "        JOIN\n" +
                    "    products ON (basket.product_id = products.id);"
            );
            while (rs.next()) {

                res.add(new BasketProductJsf(rs.getLong("idProduct"),
                                             rs.getString("nameProduct"),
                                             rs.getString("descriptionProduct"),
                                             rs.getBigDecimal("priceProduct"),
                                             rs.getLong("idProductInBasket"),
                                                rs.getInt("numberOfProduct")));
            }
        }
        return res;
    }

    public void add( Long idProductInBasket ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE basket \n" +
                        "SET \n" +
                        "    basket.number_of_product = basket.number_of_product + 1\n" +
                        "WHERE\n" +
                        "    basket.basket_id = ?; ");
        preparedStatement.setLong(1 , idProductInBasket );
        preparedStatement.executeUpdate();
    }

    public void remove (Long idProductInBasket) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(
                "SELECT \n" +
                        "    basket.number_of_product\n" +
                        "FROM\n" +
                        "    basket\n" +
                        "WHERE\n" +
                        "    basket.basket_id = ?;");
        preparedStatement.setLong(1 , idProductInBasket);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            int numberOfProduct = resultSet.getInt("number_of_product");
            if (numberOfProduct > 1){
                preparedStatement = connection.prepareStatement(
                        "UPDATE basket \n" +
                                "SET \n" +
                                "    basket.number_of_product = basket.number_of_product - 1\n" +
                                "WHERE\n" +
                                "    basket.basket_id = ?;"
                );
                preparedStatement.setLong(1 , idProductInBasket);
                preparedStatement.execute();
            }else{
                preparedStatement = connection.prepareStatement("DELETE FROM basket WHERE basket_id = ?;");
                preparedStatement.setLong(1 , idProductInBasket);
                preparedStatement.execute();
            }
        }

    }

    public void delete(Long idProductInBasket) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM basket WHERE basket_id = ?;")){
            stmt.setLong(1 , idProductInBasket);
            stmt.execute();
        }
    }

}
