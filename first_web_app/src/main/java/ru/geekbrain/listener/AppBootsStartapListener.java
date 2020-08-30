package ru.geekbrain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrain.persist.BasketRepository;
import ru.geekbrain.persist.Product;
import ru.geekbrain.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppBootsStartapListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppBootsStartapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("initializing Application");

        ServletContext servletContext = sce.getServletContext();
        String jdbcConnectionString = servletContext.getInitParameter("jdbcConnectionString");
        String username = servletContext.getInitParameter("username");
        String  password = servletContext.getInitParameter("password");

        try {
            Connection connection = DriverManager.getConnection(jdbcConnectionString,username,password);
            servletContext.setAttribute( "connection", connection);

            ProductRepository productRepository = new ProductRepository(connection);
            BasketRepository basketRepository = new BasketRepository(connection);
            servletContext.setAttribute( "productRepository", productRepository);
            servletContext.setAttribute("basketRepository", basketRepository);

            if (productRepository.findAll().isEmpty()) {
                logger.info("No products in DB. Initializing.");

                productRepository.insert(new Product(-1L, "Apple Macbook pro 2015", "Apple profession laptop", new BigDecimal(3000)));
                productRepository.insert(new Product(-1L, "Apple Macbook air 2015", "Apple netbook", new BigDecimal(2000)));
                productRepository.insert(new Product(-1L, "Apple iPad", "Apple tablet", new BigDecimal(1000)));
            }



        } catch (SQLException e) {
            logger.error("",e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
