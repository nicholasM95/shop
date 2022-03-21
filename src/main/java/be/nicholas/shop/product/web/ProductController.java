package be.nicholas.shop.product.web;

import be.nicholas.shop.product.mapper.ProductMapper;
import be.nicholas.shop.product.resource.ProductWebRequestResource;
import be.nicholas.shop.product.resource.ProductWebResponseResource;
import be.nicholas.shop.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProductWebResponseResource> create(@RequestBody ProductWebRequestResource resource) {
        ProductWebResponseResource saved = mapper.modelToResource(service.create(mapper.resourceToModel(resource)));
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/product/{id}").buildAndExpand(saved.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductWebResponseResource> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(mapper.modelToResource(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ProductWebResponseResource>> getAll() throws InterruptedException {
        return ResponseEntity.ok(mapper.modelToResource(service.getAll()));
    }
}
