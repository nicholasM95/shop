package be.nicholas.shop.product.service;

import be.nicholas.shop.product.data.ProductRepository;
import be.nicholas.shop.product.exception.ProductAlreadyExistException;
import be.nicholas.shop.product.exception.ProductFormatException;
import be.nicholas.shop.product.exception.ProductNotFoundException;
import be.nicholas.shop.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        if (!product.getCode().equals(product.getCode().toUpperCase())) {
            LOGGER.error("Bad format, product code must be upper case.");
            throw new ProductFormatException("Bad format, product code must be upper case.");
        }

        if (3 != product.getCode().length()) {
            LOGGER.error("Bad format, product code length isn't 3.");
            throw new ProductFormatException("Bad format, product code length isn't 3.");
        }

        Optional<Product> optional = repository.findByName(product.getName());
        if (optional.isPresent()) {
            LOGGER.error("Product already exist.");
            throw new ProductAlreadyExistException("Product already exist.");
        }

        product.setId(UUID.randomUUID());
        LOGGER.info("New product created.");
        return repository.save(product);
    }

    public Product getById(UUID id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            LOGGER.error("Product with id {} not found.", id);
            throw new ProductNotFoundException("Product not found.");
        }
        LOGGER.info("Get product by id {}", id);
        return product.get();
    }

    public List<Product> getAll() throws InterruptedException {
        Thread.sleep(3000);
        LOGGER.info("Get all products.");
        return repository.findAll();
    }
}
