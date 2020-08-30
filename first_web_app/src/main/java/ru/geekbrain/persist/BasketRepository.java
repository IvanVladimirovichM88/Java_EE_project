package ru.geekbrain.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasketRepository {
    private final Connection conn;

    public BasketRepository(Connection conn) {
        this.conn  = conn;

    }
    public void add(long productId) throws SQLException {
        //check is this product in basket
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int basketId =0;

        preparedStatement = conn.prepareStatement(
                "SELECT \n" +
                        "basket_id \n" +
                    " FROM \n" +
                        "basket \n" +
                    "WHERE \n" +
                        " product_id = ?;"
        );
        preparedStatement.setLong(1,productId);
        resultSet = preparedStatement.executeQuery();

        // if product in basket
        if (resultSet.next()){
            basketId = resultSet.getInt("basket_id");
            preparedStatement = conn.prepareStatement(
                    "UPDATE basket \n" +
                    "SET \n" +
                    "    basket.number_of_product = basket.number_of_product + 1\n" +
                    "WHERE\n" +
                    "    basket.basket_id = ?; "
            );
            preparedStatement.setLong(1,basketId);
            preparedStatement.executeUpdate();
        }else{
            preparedStatement = conn.prepareStatement(
                    "INSERT INTO basket (product_id, number_of_product) VALUES (?,1)"
            );
            preparedStatement.setLong(1,productId);
            preparedStatement.executeUpdate();
        }
    }


    public List<BasketProduct> findAll() throws SQLException {
        List<BasketProduct> arrayList = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                "SELECT \n" +
                    "    basket.product_id, \n" +
                    "    products.name, \n" +
                    "    products.description,\n" +
                    "    products.price,\n" +
                    "    basket.number_of_product,\n" +
                    "    basket.basket_id\n" +
                    "FROM\n" +
                    "    basket\n" +
                    "        JOIN\n" +
                    "    products ON (basket.product_id = products.id);");

            while (rs.next()) {
                arrayList.add(new BasketProduct(
                        rs.getLong("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("number_of_product"),
                        rs.getInt("basket_id")));
            }
        }
        return arrayList;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE `javaee_ecommerce_db`.`basket` (\n" +
                    "  `basket_id` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `product_id` INT NOT NULL,\n" +
                    "  `number_of_product` TINYINT UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`basket_id`),\n" +
                    "  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `fk_basket_product`\n" +
                    "    FOREIGN KEY (`product_id`)\n" +
                    "    REFERENCES `javaee_ecommerce_db`.`products` (`id`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION);");
        }
    }
}
