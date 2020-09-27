package ru.jsf_app.service.cart;


import ru.jsf_app.service.ProductDAO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {
    public void addProduct(ProductDAO productDAO);
    public List<CartProduct> getAllProducts();
    public void removeProduct(ProductDAO productDAO);

}
