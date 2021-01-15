package si.fri.rso.products_sell;

import si.fri.rso.products_sell.BingMaps.BingAPICalls;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@RequestScoped
public class ProductService {

    @Inject
    BingAPICalls bingAPICalls;

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
            if(pi != null)
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

    @Transactional
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

    public Double getDistanceToProduct(Integer productId, String destination) {
        Product product = getProduct(productId);
        if(product == null)
            throw new NotFoundException();

        return bingAPICalls.getBingDistance(product.getLocation(), destination);
    }
}
