package ru.geekbrain.lesson2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CatalogHttpServlet,urlPatterns",urlPatterns = "/cart/*")
public class CartHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Корзина</h1>");
        getServletContext().getRequestDispatcher("/navigation").include(req,resp);
    }
}
