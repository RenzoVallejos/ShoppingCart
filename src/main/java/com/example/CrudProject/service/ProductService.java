package com.example.CrudProject.service;

import com.example.CrudProject.entity.Product;
import com.example.CrudProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing Product operations.
 * Contains business logic for CRUD operations and additional utilities.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // --- CRUD Operations ---

    /**
     * Save a single product to the database.
     *
     * @param product The product object to be saved.
     * @return The saved product.
     */
    public Product saveProduct(Product product) {
        product.setCreatedDate(Objects.isNull(product.getCreatedDate()) ?
                LocalDate.now() : product.getCreatedDate());
        product.setUpdatedDate(LocalDate.now());
        return repository.save(product);
    }

    /**
     * Save a list of products to the database.
     *
     * @param products List of products to be saved.
     * @return List of saved products.
     */
    public List<Product> saveProducts(List<Product> products) {
        products.forEach(product -> {
            product.setCreatedDate(Objects.isNull(product.getCreatedDate()) ?
                    LocalDate.now() : product.getCreatedDate());
            product.setUpdatedDate(LocalDate.now());
        });
        return repository.saveAll(products); // Save all products at once after updating
    }


    /**
     * Retrieve all products from the database.
     *
     * @return List of all products.
     */
    public List<Product> getProducts() {
        return repository.findAll();
    }

    /**
     * Retrieve a product by its ID.
     *
     * @param id The unique identifier of the product.
     * @return The product object if found, or throw an exception.
     */
    public Product getProductById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with ID " + id + " not found."));
    }

    /**
     * Retrieve a product by its name.
     *
     * @param name The name of the product.
     * @return The product object if found.
     */
    public Product getProductByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Product with name " + name + " not found."));
    }

    /**
     * Update an existing product.
     *
     * @param product The product object with updated details.
     * @return The updated product object.
     */
    public Product updateProduct(Product product) {
        Product existingProduct = getProductById(product.getId());
        existingProduct.updateDetails(product.getName(), product.getPrice(), product.getDescription(), product.getCategory());
        return repository.save(existingProduct);
    }

    /**
     * Delete a product by its ID.
     *
     * @param id The unique identifier of the product to be deleted.
     * @return Confirmation message upon successful deletion.
     */
    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "Product with ID " + id + " deleted successfully.";
    }

    // --- Additional Features ---

    /**
     * Retrieve products within a specific price range.
     *
     * @param minPrice Minimum price (inclusive).
     * @param maxPrice Maximum price (inclusive).
     * @return List of products within the specified price range.
     */
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return repository.findAll().stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve products by category.
     *
     * @param category The category of the products.
     * @return List of products matching the specified category.
     */
    public List<Product> getProductsByCategory(String category) {
        return repository.findAll().stream()
                .filter(product -> category.equalsIgnoreCase(product.getCategory()))
                .collect(Collectors.toList());
    }

    /**
     * Mark a product as out of stock.
     *
     * @param id The ID of the product to be marked out of stock.
     * @return The updated product object.
     */
    public Product markProductAsOutOfStock(int id) {
        Product product = getProductById(id);
        product.markAsOutOfStock();
        return repository.save(product);
    }

    /**
     * Get a summary of products by category.
     *
     * @return A map where the key is the category, and the value is the count of products in that category.
     */
    public Map<String, Long> getProductSummaryByCategory() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    /**
     * Calculate the total stock value of all products.
     *
     * @return The total value of stock (sum of price * quantity for all products).
     */
    public double getTotalStockValue() {
        return repository.findAll().stream()
                .mapToDouble(Product::calculateStockValue)
                .sum();
    }
}
