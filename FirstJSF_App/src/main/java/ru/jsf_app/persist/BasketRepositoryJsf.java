package ru.jsf_app.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            ResultSet rs = stmt.executeQuery("SELECT \n" +
                    "    `products`.`id` AS idProduct,\n" +
                    "    `products`.`name` AS nameProduct,\n" +
                    "    `products`.`description` AS descriptionProduct,\n" +
                    "    `products`.`price` AS priceProduct,\n" +
                    "    basket.basket_id AS idProductInBasket, \n" +
                    "    basket.number_of_product AS numberOfProduct\n" +
                    "FROM\n" +
                    "    basket\n" +
                    "        JOIN\n" +
                    "    products ON (basket.basket_id = products.id);");
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

}
