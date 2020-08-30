package ru.geekbrain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrain.listener.AppBootsStartapListener;
import ru.geekbrain.persist.Product;
import ru.geekbrain.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

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
        if ( req.getServletPath().equals("/") ){
            try {
                req.setAttribute("products", productRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (req.getServletPath().equals("/new")){
            req.setAttribute("product", new Product());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req,resp);

        }else if ( req.getServletPath().equals("/edit") ){
            String id = req.getParameter("id");
            try {
                Optional<Product>  opt = productRepository.findById(Long.parseLong(id));
                if(opt.isPresent()) {
                    req.setAttribute("product", opt.get());
                }else{
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req,resp);

        }else if ( req.getServletPath().equals("/select") ){
            String id = req.getParameter("id");
            try {
                Optional<Product>  opt = productRepository.findById(Long.parseLong(id));
                if(opt.isPresent()) {
                    req.setAttribute("product", opt.get());
                }else{
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/WEB-INF/selectProduct.jsp").forward(req,resp);

        }
        else if( req.getServletPath().equals("/about") ){
            getServletContext().getRequestDispatcher("/WEB-INF/about.jsp").forward(req,resp);
        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if ( req.getServletPath().equals("/upsert") ) {
            try {
                String strId = req.getParameter("id");
                if( strId.isEmpty() ){
                    productRepository.insert(new Product(
                            -1L,
                            req.getParameter("name"),
                            req.getParameter("description"),
                            new BigDecimal(req.getParameter("price"))
                    ));
                }else{
                    productRepository.update(new Product(
                            Long.parseLong(strId),
                            req.getParameter("name"),
                            req.getParameter("description"),
                            new BigDecimal(req.getParameter("price"))
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(getServletContext().getContextPath());

        }else if  ( req.getServletPath().equals("/deleteProduct") ){
            String strId = req.getParameter("id");
            try {
                productRepository.delete(Long.parseLong(strId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(getServletContext().getContextPath());

        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
