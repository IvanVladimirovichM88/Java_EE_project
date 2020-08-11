package ru.geekbrain.lesson2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NavigationHttpServlet", urlPatterns = "/navigation")
public class NavigationHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<a href = main > Главная страница </a>");
        resp.getWriter().println("<a href = catalog > Каталог товаров </a>");
        resp.getWriter().println("<a href = product > Товар </a>");
        resp.getWriter().println("<a href = cart > Корзина </a>");
        resp.getWriter().println("<a href = order > Заказ </a>");

        if ( req.getPathInfo() != null ){
            resp.sendRedirect(req.getContextPath() +  req.getPathInfo());
        }
    }



}
