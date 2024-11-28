package com.example.CrudProject.controller;

import com.example.CrudProject.entity.Product;
import com.example.CrudProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for managing Product-related operations.
 * Provides RESTful APIs for CRUD operations and additional utilities.
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    /**
     * Add a single product to the database.
     *
     * @param product The product object to be added.
     * @return The saved product with its assigned ID.
     */
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    /**
     * Add multiple products to the database in bulk.
     *
     * @param products List of products to be added.
     * @return List of saved products.
     */
    @PostMapping("/bulk")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    /**
     * Retrieve all products.
     *
     * @return List of all products in the database.
     */
    @GetMapping
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    /**
     * Retrieve a product by its ID.
     *
     * @param id The unique identifier of the product.
     * @return The product object if found.
     */
    @GetMapping("/{id}")
    public Product findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    /**
     * Retrieve a product by its name.
     *
     * @param name The name of the product.
     * @return The product object if found.
     */
    @GetMapping("/search/name/{name}")
    public Product findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    /**
     * Update an existing product.
     *
     * @param product The product object with updated details.
     * @return The updated product object.
     */
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    /**
     * Delete a product by its ID.
     *
     * @param id The unique identifier of the product to be deleted.
     * @return Confirmation message upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }

    /**
     * Retrieve products within a specific price range.
     *
     * @param minPrice Minimum price (inclusive).
     * @param maxPrice Maximum price (inclusive).
     * @return List of products within the specified price range.
     */
    @GetMapping("/search/price")
    public List<Product> findProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return service.getProductsByPriceRange(minPrice, maxPrice);
    }

    /**
     * Retrieve products by category.
     *
     * @param category The category of the products.
     * @return List of products matching the specified category.
     */
    @GetMapping("/search/category/{category}")
    public List<Product> findProductsByCategory(@PathVariable String category) {
        return service.getProductsByCategory(category);
    }

    /**
     * Mark a product as out of stock.
     *
     * @param id The ID of the product to be marked out of stock.
     * @return The updated product object with "inStock" set to false.
     */
    @PatchMapping("/{id}/out-of-stock")
    public Product markProductAsOutOfStock(@PathVariable int id) {
        return service.markProductAsOutOfStock(id);
    }

    /**
     * Get a summary of all products by category.
     *
     * @return A map where the key is the category, and the value is the count of products in that category.
     */
    @GetMapping("/summary/category")
    public Map<String, Long> getProductSummaryByCategory() {
        return service.getProductSummaryByCategory();
    }

    /**
     * Get the total stock value of all products.
     *
     * @return The total value of all products in the database.
     */
    @GetMapping("/summary/stock-value")
    public double getTotalStockValue() {
        return service.getTotalStockValue();
    }
}
