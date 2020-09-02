package ru.jsf_app.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.sql.Connection;

public class BasketRepositoryJsf {

    private static final Logger logger = LoggerFactory.getLogger(BasketRepositoryJsf.class);
    @Inject
    private ServletContext servletContext;
    private Connection connection;
}
