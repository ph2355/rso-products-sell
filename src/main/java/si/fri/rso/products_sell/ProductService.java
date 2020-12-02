package si.fri.rso.products_sell;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ProductService {

    @PersistenceContext
    private EntityManager em;

    public Product getProduct(Integer productId) {
        return em.find(Product.class, productId);
    }

    public List<Product> getProducts() {
        List<Product> products = em
                .createNamedQuery("Product.findProducts", Product.class)
                .getResultList();

        return products;
    }

    @Transactional
    public void saveProduct(Product product) {
        if (product != null) {
            em.persist(product);
        }

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteProduct(Integer productId) {
        Product product = em.find(Product.class, productId);
        if (product != null) {
            em.remove(product);
        }
    }

    public List<Product> getProductsByOwnerId(Integer ownerId) {
        List<Product> products = em
                .createNamedQuery("Product.findByOwnerId", Product.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
        return products;
    }
}
