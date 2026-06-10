package com.nicodev.product_service.service;

import com.nicodev.product_service.dto.ProductDTO;
import com.nicodev.product_service.model.Product;

import java.util.List;

public interface IProductService {

    // POST
    public void saveProduct(Product product);

    // FIND BY ID
    public Product findProduct(Long product_id);

    // FIND ALL
    public List<Product> getProducts();

    // DELETE
    public void deleteProduct(Long product_id);

    // UPDATE
    public Product editProduct(Long product_id, Product newProduct);

    // PATCH
    public Product patchProduct(Long product_id, Product pro);

    // FIND BY SECTION
    public List<Product> findBySection(String section);

    // GET DTO
    public ProductDTO findProductDTO(Long product_id);

    // PRODUCT EXIST
    public Boolean productExist(Long product_id);

    // ENOUGH STOCK
    public Boolean enoughStock(Long product_id, Integer quantity);

    // UPDATE STOCK
    public void updateStock(Long product_id, Integer quantity);


}
