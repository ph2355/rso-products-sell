package si.fri.rso.products_sell;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
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
        ProductImage pi = em.find(ProductImage.class, productId);
        if (product != null) {
            em.remove(product);
            em.remove(pi);
        }
    }

    public List<Product> getProductsByOwnerId(Integer ownerId) {
        List<Product> products = em
                .createNamedQuery("Product.findByOwnerId", Product.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
        return products;
    }

    public ProductImage getProductImage(Integer productId) {
        ProductImage productImage;
        try {
            productImage = em
                    .createNamedQuery("ProductImage.find", ProductImage.class)
                    .setParameter("productId", productId)
                    .getSingleResult();
        } catch (NoResultException e) {
            productImage = null;
        }

       return productImage;
    }

    @Transactional
    public void saveProductImage(ProductImage pi) {
        Product p = em.find(Product.class, pi.getProductId());
        if(p == null)
            throw new NotFoundException();
        else
            em.persist(pi);
    }
}
