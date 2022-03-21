package be.nicholas.shop.product.mapper;

import be.nicholas.shop.product.model.Product;
import be.nicholas.shop.product.resource.ProductWebRequestResource;
import be.nicholas.shop.product.resource.ProductWebResponseResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product resourceToModel(final ProductWebRequestResource resource) {
        Product product = new Product();
        product.setName(resource.getName());
        product.setCode(resource.getCode());
        return product;
    }

    @Override
    public ProductWebResponseResource modelToResource(final Product product) {
        ProductWebResponseResource resource = new ProductWebResponseResource();
        resource.setId(product.getId());
        resource.setName(product.getName());
        resource.setCode(product.getCode());
        return resource;
    }

    @Override
    public List<ProductWebResponseResource> modelToResource(List<Product> products) {
        List<ProductWebResponseResource> resources = new ArrayList<>();
        for (Product product : products) {
            resources.add(modelToResource(product));
        }
        return resources;
    }
}
