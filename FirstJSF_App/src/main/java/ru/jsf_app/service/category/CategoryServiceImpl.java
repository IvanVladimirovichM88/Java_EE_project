package ru.jsf_app.service.category;

import ru.jsf_app.persist.CategoriesRepository;
import ru.jsf_app.persist.Category;
import ru.jsf_app.service.ProductDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService{

    @EJB
    private CategoriesRepository categoriesRepository;

    @Override
    public void insert(CategoryDAO categoryDAO) {
        Category category = new Category(categoryDAO.getFldCategory());
    }

    @Override
    public void update(CategoryDAO categoryDAO) {
        Category category = categoriesRepository.findById(categoryDAO.getIdCategory())
                .orElse(null);
        if (category != null){
            category.setFldCategory(categoryDAO.getFldCategory());
        }
    }

    @Override
    public void delete(Long id) {
        Category category = categoriesRepository.findById(id)
                .orElse(null);
        if( category.getProducts().isEmpty() ){
            categoriesRepository.delete(id);
        }

    }

    @Override
    public Optional<CategoryDAO> findById(Long id) {
        return categoriesRepository.findById(id)
                .map(CategoryDAO::new);
    }

    @Override
    public List<CategoryDAO> findAll() {
        return categoriesRepository.findAll().stream()
                .map(CategoryDAO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductDAO> getAllProductCategory(Long id){
        CategoryDAO categoryDAO = this.findById(id).orElse(null);
        if(categoryDAO!=null){
            return categoryDAO.getProductDAOList();
        }else  {
            return null;
        }
    }
}
