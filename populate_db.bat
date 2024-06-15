@echo off

:: Add Category: Carpet
curl -X POST http://localhost:8080/categories -H "Content-Type: application/json" -d "{\"name\": \"Carpet\"}"

:: Add Category: Food
curl -X POST http://localhost:8080/categories -H "Content-Type: application/json" -d "{\"name\": \"Food\"}"

:: Add Category: Furniture
curl -X POST http://localhost:8080/categories -H "Content-Type: application/json" -d "{\"name\": \"Furniture\"}"

:: Add Product: Carpet
curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d "{\"description\": \"Carpet\", \"price\": 100.00, \"category\": {\"id\": 1}, \"itemNumber\": \"10001\", \"weight\": null, \"color\": \"Blue\", \"flavor\": null}"

:: Add Product: Chili-flavored Hot Dog
curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d "{\"description\": \"Chili Hot Dog\", \"price\": 3.50, \"category\": {\"id\": 2}, \"itemNumber\": null, \"weight\": null, \"color\": null, \"flavor\": \"Chili\"}"

:: Add Product: Wardrobe
curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d "{\"description\": \"Wardrobe\", \"price\": 250.00, \"category\": {\"id\": 3}, \"itemNumber\": \"30001\", \"weight\": 50.0, \"color\": null, \"flavor\": null}"

:: Add Product: Vegetarian Hot Dog
curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d "{\"description\": \"Vegetarian Hot Dog\", \"price\": 3.00, \"category\": {\"id\": 2}, \"itemNumber\": null, \"weight\": null, \"color\": null, \"flavor\": null}"

:: Add Customer: Member
curl -X POST http://localhost:8080/customers -H "Content-Type: application/json" -d "{\"name\": \"Mathias Rabattersen\", \"isMember\": true}"

:: Add Customer: Non-Member
curl -X POST http://localhost:8080/customers -H "Content-Type: application/json" -d "{\"name\": \"Ian Fullprissen\", \"isMember\": false}"

:: Add Discount: Furniture
curl -X POST http://localhost:8080/discounts -H "Content-Type: application/json" -d "{\"category\": {\"id\": 3}, \"discountPercentage\": 20.0}"

:: Add Discount: Food
curl -X POST http://localhost:8080/discounts -H "Content-Type: application/json" -d "{\"category\": {\"id\": 2}, \"discountPercentage\": 50.0}"
