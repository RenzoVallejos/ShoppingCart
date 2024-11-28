# Shopping Cart CRUD API

## Overview
The Shopping Cart CRUD API is a backend application designed to manage shopping cart items. It provides endpoints to **create**, **read**, **update**, and **delete** items, making it a complete CRUD system. Users can interact with the API through tools like **Postman** to manage data such as the number of items, their prices, and types.

This project serves as an educational example of backend development using **Spring Boot**, showcasing how applications interact with databases and handle data operations.

---

## Features
- Add new items to the shopping cart.
- Retrieve the list of items with their details.
- Update existing items, including their quantity, price, or type.
- Delete items from the shopping cart.

---

## Technologies Used
- **Java**: Core programming language.
- **Spring Boot**: Framework for building the API.
- **Maven**: Dependency and build management.
- **H2 Database**: Embedded database for development and testing.
- **Postman**: For API testing and interaction.

---

## Project Structure
```plaintext
src/
├── main/
│   ├── java/com/example/CrudProject/
│   │   ├── controller/        # API controllers (handles requests)
│   │   ├── entity/            # Entity classes (Product model)
│   │   ├── repository/        # Data repositories (Database operations)
│   │   ├── service/           # Business logic services
│   │   └── CrudProjectApplication.java  # Application entry point
│   ├── resources/
│       └── application.properties  # Configuration file
└── test/                       # Test cases
