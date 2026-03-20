package com.nicodev.product_service.controller;

import com.nicodev.product_service.dto.ProductDTO;
import com.nicodev.product_service.model.Product;
import com.nicodev.product_service.service.IProductService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    // POST
    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return ResponseEntity.created(URI.create("/products/save")).body("Product created successfully");
    }

    // FIND BY ID
    @GetMapping("/find/{product_id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long product_id) {
        return ResponseEntity.ok(productService.findProduct(product_id));
    }

    // GET ALL
    @GetMapping("/find")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    // DELETE
    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long product_id){
        productService.deleteProduct(product_id);
        return ResponseEntity.noContent().build();
    }

    // EDIT
    @PutMapping("/edit/{product_id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long product_id,
                                               @RequestBody Product newProduct){
        return ResponseEntity.ok(productService.editProduct(product_id,newProduct));
    }

    //PATCH
    @PatchMapping("/patch/{product_id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long product_id,
                                @RequestBody Product pro){
        return ResponseEntity.ok(productService.patchProduct(product_id,pro));
    }

    // FIND BY SECTION
    @GetMapping("/find/section/{section}")
    public ResponseEntity<List<Product>>findBySection(@PathVariable String section){
        return ResponseEntity.ok(productService.findBySection(section));
    }

    // FIND DTO
    @GetMapping("/find/dto/{product_id}")
    public ResponseEntity<ProductDTO> findProductDTO(@PathVariable Long product_id){
        return ResponseEntity.ok(productService.findProductDTO(product_id));
    }

    // PRODUCT EXIST
    @GetMapping("/exist/{product_id}")
    public ResponseEntity<Boolean> productExist(@PathVariable Long product_id){
        return ResponseEntity.ok(productService.productExist(product_id));
    }

}
