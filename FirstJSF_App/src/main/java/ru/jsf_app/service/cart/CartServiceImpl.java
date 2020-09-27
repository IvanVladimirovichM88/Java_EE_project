package ru.jsf_app.service.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.jsf_app.service.ProductDAO;
import ru.jsf_app.service.category.CategoryService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartServiceImpl implements CartService {

    private List<CartProduct> productsInCart = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    public CartServiceImpl(){}

    @Override
    public void addProduct(ProductDAO productDAO) {
        CartProduct cp = findProductInCart(productDAO);
        if (cp != null){
            cp.addCount();
        }else {
            productsInCart.add(new CartProduct(productDAO, 1));
        }
    }

    @Override
    public List<CartProduct> getAllProducts() {
        return productsInCart;
    }

    public void removeProduct(ProductDAO productDAO){

        CartProduct cp = findProductInCart(productDAO);
        if ( cp.cutCount() == 0 ){
            productsInCart.remove(cp);
        }
    }

    private CartProduct findProductInCart(ProductDAO productDAO){
        for (CartProduct cp : productsInCart){
            if(cp.getProductDAO().getId() == productDAO.getId()){
                return cp;
            }
        }
        return null;
    }




    @PostConstruct
    public void init(){
        logger.info("\n------>>   Start CartService \n");
    }

    @PreDestroy
    public void destroy(){
        logger.info("\n------>>   Destroy CartService \n");
    }

}
