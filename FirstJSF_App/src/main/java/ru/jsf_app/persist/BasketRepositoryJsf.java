//package ru.jsf_app.persist;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.io.Serializable;
//import java.util.List;
//import java.util.Optional;
//
//@ApplicationScoped
//@Named
//public class BasketRepositoryJsf implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(BasketRepositoryJsf.class);
//
//    @PersistenceContext(unitName = "ds")
//    private EntityManager entityManager;
//
//    public BasketRepositoryJsf(){
//        logger.info("\n --> BasketRepositoryJsf constructor");
//    }
//
//    @Transactional
//    public void insert( BasketProductJsf basketProductJsf ) {
//        entityManager.persist(basketProductJsf);
//    }
//    @Transactional
//    public void update( BasketProductJsf basketProductJsf ){
//        entityManager.merge(basketProductJsf);
//    }
//    @Transactional
//    public void delete(Long idBasket){
//        BasketProductJsf basketProductJsf = entityManager.find(BasketProductJsf.class, idBasket);
//        if(basketProductJsf != null){
//            entityManager.remove(basketProductJsf);
//        }
//    }
//
//    public Optional<BasketProductJsf> findById(int idBasket){
//        BasketProductJsf basketProductJsf = entityManager.find(BasketProductJsf.class, idBasket);
//        if(basketProductJsf != null){
//            return Optional.of(basketProductJsf);
//        }
//        return Optional.empty();
//    }
//
//    public Optional<BasketProductJsf> findByProduct(Product product){
//        List<BasketProductJsf> basketProductJsf = entityManager.createQuery(
//                "SELECT b FROM BasketProductJsf b WHERE b.product.id = :product",
//                BasketProductJsf.class
//                ).setParameter("product", product.getId()).getResultList();
//
//        if(! basketProductJsf.isEmpty() ){
//            return Optional.of(basketProductJsf.get(0));
//        }
//        return Optional.empty();
//    }
//
//    public List<BasketProductJsf> findAll(){
//        return entityManager.createQuery("from BasketProductJsf",BasketProductJsf.class).getResultList();
//    }
//
//
//}
