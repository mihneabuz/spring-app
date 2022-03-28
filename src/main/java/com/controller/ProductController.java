package com.controller;

import com.auth.JwtOps;
import com.auth.exceptions.UnauthorizedException;
import com.entity.Identity;
import com.entity.Product;
import com.model.product.CreateProductRequest;
import com.model.product.DeleteProductRequest;
import com.model.product.GetProductsResponse;
import com.model.Response;
import com.model.product.UpdateProductRequest;
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
        log.info(body.toString());

        Identity identity = JwtOps.decodeOrThrow(auth);
        if (identity.getLevel() <= 2) {
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

    @PostMapping("/update")
    public Response update(@RequestHeader("Authorization") String auth,
                           @RequestBody UpdateProductRequest body) {
        log.info(body.toString());

        Identity identity = JwtOps.decodeOrThrow(auth);
        if (identity.getLevel() <= 2) {
            throw new UnauthorizedException("Unauthorized");
        }

        var maybeProduct = productRepo.findById(body.getId());

        if (maybeProduct.isEmpty()) {
            return Response.bad("No product with this id found");
        }

        if (body.hasName()) {
            productRepo.updateName(body.getId(), body.getName());
        }
        if (body.hasPrice()) {
            productRepo.updatePrice(body.getId(), body.getPrice());
        }
        if (body.hasDetails()) {
            productRepo.updateDetails(body.getId(), body.getDetails());
        }

        return Response.good();
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestHeader("Authorization") String auth,
                           @RequestBody DeleteProductRequest body) {
        log.info(body.toString());

        Identity identity = JwtOps.decodeOrThrow(auth);
        if (identity.getLevel() <= 2) {
            throw new UnauthorizedException("Unauthorized");
        }

        var maybeProduct = productRepo.findById(body.getId());

        if (maybeProduct.isEmpty()) {
            return Response.bad("No product with this id found");
        }

        productRepo.deleteProduct(body.getId());

        return Response.good();
    }

    @GetMapping("/count")
    public long count(@RequestHeader("Authorization") String auth) {
        JwtOps.decodeOrThrow(auth);
        return productRepo.count();
    }
}
