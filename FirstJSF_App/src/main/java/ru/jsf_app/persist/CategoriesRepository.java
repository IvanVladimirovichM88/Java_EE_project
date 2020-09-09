package ru.jsf_app.persist;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class CategoriesRepository {
    @Inject
    private ServletContext servletContext;
    private Connection connection;

    public CategoriesRepository(){}

    @PostConstruct
    public void init(){
        connection = (Connection)servletContext.getAttribute("connection");
    }

    public List<Category> findAll() throws SQLException {
        List<Category> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT category_id, category_fld FROM categories;"
        );
        while (resultSet.next()){
            res.add(new Category(
                    resultSet.getLong("category_id"),
                    resultSet.getString("category_fld")
            ));
        }
        return res;

    }
}
