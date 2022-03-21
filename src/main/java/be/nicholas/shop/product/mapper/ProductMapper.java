package be.nicholas.shop.product.mapper;

import be.nicholas.shop.product.model.Product;
import be.nicholas.shop.product.resource.ProductWebRequestResource;
import be.nicholas.shop.product.resource.ProductWebResponseResource;

import java.util.List;

public interface ProductMapper {
    Product resourceToModel(final ProductWebRequestResource resource);

    ProductWebResponseResource modelToResource(final Product product);

    List<ProductWebResponseResource> modelToResource(final List<Product> products);
}
