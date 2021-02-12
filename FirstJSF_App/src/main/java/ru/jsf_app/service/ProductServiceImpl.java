package ru.jsf_app.service;

import ru.jsf_app.persist.CategoriesRepository;
import ru.jsf_app.persist.Category;
import ru.jsf_app.persist.Product;
import ru.jsf_app.persist.ProductRepository;
import ru.jsf_app.rest.ProductServiceRs;
import ru.jsf_app.service.category.CategoryDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@WebService(endpointInterface = "ru.jsf_app.service.ProductServiceWs",
            serviceName = "ProductService")
public class ProductServiceImpl implements ProductService, ProductServiceWs, ProductServiceRs {

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
        }else{
            Product product= new Product(
                    productDAO.getName(),
                    productDAO.getDescription(),
                    productDAO.getPrice()
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

    @Override
    public ProductDAO findByIdRs(long id) {
        return findById(id).get();
    }

    @Override
    public ProductDAO findByName(String productName) {
        return productRepository.findByProductName(productName)
                .map(ProductDAO::new).get();
    }

    @Override
    public List<ProductDAO> findByCategoryNameRs(String categoryName) {

        return productRepository.findByFldCategory(categoryName)
                .stream()
                .map(ProductDAO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDAO> findByCategoryId(Long categoryId) {
        return productRepository.findByIdCategory(categoryId)
                .stream()
                .map(ProductDAO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void changeProductCategory(ProductDAO productDAO, CategoryDAO categoryDAO) {

        Category category = new Category(categoryDAO.getFldCategory());

        Optional<Category> optionalCategory = categoriesRepository.findById(categoryDAO.getIdCategory());
        if ( !optionalCategory.isPresent()){
            categoriesRepository.insert(category);
            productDAO.setIdCategory( categoriesRepository.findByCategoryFLd(categoryDAO.getFldCategory()).get().getIdCategory() );
            productDAO.setNameCategory(categoryDAO.getFldCategory());
        }

        productDAO.setIdCategory(optionalCategory.get().getIdCategory());
        productDAO.setNameCategory(optionalCategory.get().getFldCategory());

        this.update(productDAO);
    }
}
