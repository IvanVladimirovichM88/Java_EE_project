package ru.geekbrain.lesson2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainPageHttpServlet",urlPatterns = "/main/*")
public class MainPageHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>-----ГЛАВНАЯ СТРАНИЦА------</h1>");
        getServletContext().getRequestDispatcher("/navigation").include(req,resp);
    }
}
