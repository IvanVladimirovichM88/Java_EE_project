package ru.geekbrain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrain.listener.AppBootsStartapListener;
import ru.geekbrain.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductControllerServlet", urlPatterns = {"","/"})
public class ProductControllerServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductControllerServlet.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");

        if (productRepository == null){
            throw new ServletException("Product repository is not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("index product page");
        if (req.getServletPath().equals("/")){
            try {
                req.setAttribute("products", productRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
