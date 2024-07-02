package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(Long id, Product product) {
        if (!isExist(id)) {
            return createProduct(product);
        }
        product.setId(id);
        productRepository.update(product);
        return product;
    }

    public Long deleteProduct(Long id) {
        if (!isExist(id)) {
            return -1L;
        }
        productRepository.deleteById(id);
        return id;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    private boolean isExist(Long id) {
        return productRepository.findById(id) != null;
    }
}