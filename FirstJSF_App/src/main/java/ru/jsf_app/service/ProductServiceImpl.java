package ru.jsf_app.service;

import ru.jsf_app.persist.CategoriesRepository;
import ru.jsf_app.persist.Category;
import ru.jsf_app.persist.Product;
import ru.jsf_app.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoriesRepository categoriesRepository;

    @Override
    public void insert(ProductDAO productDAO) {
        Category category = categoriesRepository.findById(productDAO.getIdCategory()).orElse(null);

        if (category != null){
            Product product= new Product(
                    productDAO.getName(),
                    productDAO.getDescription(),
                    productDAO.getPrice(),
                    category
            );
            productRepository.insert(product);
        }

    }

    @Override
    public void update(ProductDAO productDAO) {
        Product product = productRepository.findById(productDAO.getId()).orElse(null);
        if ( product != null ){

            product.setName(productDAO.getName());
            product.setDescription(productDAO.getDescription());
            product.setPrice(productDAO.getPrice());

            Category category = categoriesRepository.findById(productDAO.getIdCategory()).orElse(null);

            if (category != null) {
                product.setCategory(category);
            }

            productRepository.update(product);
        }

    }

    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public Optional<ProductDAO> findById(long id) {
        return productRepository.findById(id).map(ProductDAO::new);
    }

    @Override
    public List<ProductDAO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDAO::new).collect(Collectors.toList());
    }
}
