package gift.service;

import gift.domain.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    public void addProduct(Product product) {
        validateProduct(product);
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        validateProduct(product);
        productRepository.update(product);
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        if (product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
            throw new IllegalArgumentException("Product image URL is required");
        }
    }
}
