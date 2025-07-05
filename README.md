# E-Commerce System

A simple Java-based e-commerce system that demonstrates object-oriented programming concepts with product management, shopping cart functionality, and checkout processing.

## Features

- **Product Management**: Support for both expirable and non-expirable products
- **Shopping Cart**: Add/remove items with quantity management
- **Checkout System**: Complete checkout process with validation
- **Shipping Service**: Automatic shipping calculation and processing
- **Customer Management**: Balance tracking and payment processing
- **Error Handling**: Comprehensive validation for various scenarios

## Project Structure

```
src/main/java/com/fawry/ecommerce/
├── Main.java                 # Main application entry point
├── model/                    # Data models
│   ├── Cart.java            # Shopping cart implementation
│   ├── CartItem.java        # Individual cart items
│   ├── Customer.java        # Customer information
│   ├── Product.java         # Base product class
│   ├── ExpirableProduct.java # Products with expiration dates
│   ├── NonExpirableProduct.java # Products without expiration
│   └── ShippableItem.java   # Items that require shipping
└── service/                 # Business logic
    ├── ECommerceSystem.java # Main e-commerce logic
    └── ShippingService.java # Shipping calculations
```

## How to Build and Run

### Prerequisites
- Java 8 or higher
- Bash shell (for build script)

### Quick Start
1. Clone or download the project
2. Make the build script executable:
   ```bash
   chmod +x build.sh
   ```
3. Run the build script:
   ```bash
   ./build.sh
   ```

## Demo Scenarios

The application includes several test scenarios that demonstrate:

1. **Normal Checkout**: Successful purchase with multiple items
2. **Empty Cart Error**: Attempting to checkout with an empty cart
3. **Insufficient Balance**: Customer doesn't have enough money
4. **Out of Stock Error**: Trying to buy more items than available
5. **Expired Product Error**: Attempting to purchase expired products
