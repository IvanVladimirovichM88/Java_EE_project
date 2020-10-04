import ru.jsf_app.service.ProductService;
import ru.jsf_app.service.ProductServiceWs;

import java.net.MalformedURLException;
import java.net.URL;

public class MainClass {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/FirstJSF_App/ProductService/ProductServiceImpl?wsdl");
        ProductService productService = new ProductService(url);

        ProductServiceWs port = productService.getProductServiceImplPort();

        port.findAll().forEach(todo -> System.out.println(todo.getId() + ": " + todo.getName()));
    }
}
