package com.controller;

import com.auth.JwtOps;
import com.auth.exceptions.UnauthorizedException;
import com.entity.Identity;
import com.entity.Product;
import com.model.product.CreateProductRequest;
import com.model.product.GetProductsResponse;
import com.model.Response;
import com.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

// DELETE: used for testing
@CrossOrigin

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private static final ProductRepository productRepo = ProductRepository.get();

    @PostMapping("/create")
    public Response create(@RequestHeader("Authorization") String auth,
                           @RequestBody CreateProductRequest body) {

        Identity identity = JwtOps.decodeOrThrow(auth);

        if (identity.level <= 2) {
            throw new UnauthorizedException("Unauthorized");
        }

        Product product = Product.createNew(body.getName(), body.getPrice(), body.getDetails());
        productRepo.addProduct(product);

        return Response.good();
    }

    @GetMapping("/info")
    public Response productInfo(@RequestHeader("Authorization") String auth) {
        // Get products only if logged in
        JwtOps.decodeOrThrow(auth);

        return new GetProductsResponse(productRepo.getProducts());
    }
}
