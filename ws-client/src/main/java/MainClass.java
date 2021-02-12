import ru.jsf_app.service.ProductDAO;
import ru.jsf_app.service.ProductService;
import ru.jsf_app.service.ProductServiceWs;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

public class MainClass {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/FirstJSF_App/ProductService/ProductServiceImpl?wsdl");
        ProductService productService = new ProductService(url);

        ProductServiceWs port = productService.getProductServiceImplPort();

        port.findAll().forEach(todo -> System.out.println(todo.getId() + ": " + todo.getName()));

        ProductDAO productDAO = new ProductDAO();
        productDAO.setName("WsProduct");
        productDAO.setIdCategory(1L);
        productDAO.setPrice( BigDecimal.valueOf(4545));
        productDAO.setDescription("product for test wsService");

        port.insert(productDAO);

        System.out.println("--->> products after insert");

        port.findAll().forEach(todo -> System.out.println(todo.getId() + ": " + todo.getName()));

        System.out.println("--->> show products with categoryId=1");

        port.findByCategoryId(1L).forEach(todo -> System.out.println(todo.getId() + ": " + todo.getName()));

        productDAO = port.findByName("WsProduct");

        port.delete(productDAO.getId());

        System.out.println("--->> products after delete");

        port.findAll().forEach(todo -> System.out.println(todo.getId() + ": " + todo.getName()));




    }
}
