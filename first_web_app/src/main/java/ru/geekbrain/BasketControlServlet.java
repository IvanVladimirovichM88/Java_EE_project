package ru.geekbrain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrain.persist.BasketRepository;
import ru.geekbrain.persist.Product;
import ru.geekbrain.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


@WebServlet(name = "BasketControlServlet", urlPatterns = {"/addInBasket","/showBasket"})
public class BasketControlServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BasketControlServlet.class);
    private BasketRepository basketRepository;
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        basketRepository = (BasketRepository) getServletContext().getAttribute("basketRepository");
        productRepository = (ProductRepository) getServletContext().getAttribute( "productRepository");

        if(basketRepository == null){
            throw new ServletException("Basket repository is not initialized in Basket servlet");
        }

        if (productRepository == null){
            throw new ServletException("Product repository is not initialized in Basket servlet");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("basket page");
        if ( req.getServletPath().equals("/showBasket") ){
            try {
                req.setAttribute("productsInBasket", basketRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/basket.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (req.getServletPath().equals("/addInBasket") ){
            String id = req.getParameter("id");
            try {
                basketRepository.add( Long.parseLong(id) );
                logger.info("add product in Basket");
                req.setAttribute("products", productRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
                logger.info("go for page index.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
