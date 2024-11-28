package com.example.CrudProject.entity;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity class representing a Product in the database.
 * Contains various attributes and methods for managing Product details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_TBL")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    private int id;                     // Unique identifier for the product

    private String name;                // Name of the product
    private int quantity;               // Quantity available in stock
    private double price;               // Price per unit of the product
    private String description;         // Detailed description of the product
    private String category;            // Category (e.g., Electronics, Groceries)
    private LocalDate createdDate;      // Date the product was added to the database
    private LocalDate updatedDate;      // Date the product details were last updated
    private boolean inStock;            // Availability status (true = in stock)

    /**
     * Calculate the total value of the stock for this product.
     *
     * @return Total value of stock (price * quantity).
     */
    public double calculateStockValue() {
        return this.price * this.quantity;
    }

    /**
     * Check if the product belongs to a specific category.
     *
     * @param category The category to check against.
     * @return True if the product belongs to the specified category, false otherwise.
     */
    public boolean isInCategory(String category) {
        return this.category != null && this.category.equalsIgnoreCase(category);
    }

    /**
     * Mark the product as out of stock.
     */
    public void markAsOutOfStock() {
        this.inStock = false;
        this.quantity = 0;
    }

    /**
     * Update product details with new values.
     *
     * @param name New name of the product.
     * @param price New price of the product.
     * @param description New description of the product.
     * @param category New category of the product.
     */
    public void updateDetails(String name, double price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.updatedDate = LocalDate.now(); // Update the timestamp
    }
}
