# Ekea Backend

This project is an application built with Spring Boot using PostgreSQL database.

## Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

- Docker
- Docker Compose
- Java Development Kit (JDK)
- An IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/ScubaCodeDiver/ekea-project.git
   cd ekea-project
   
2. **Create and run the PostgreSQL database in a Docker container:**

   ```bash
   docker-compose up -d

3. **Run the backend:**
     
   Open the project in your IDE and run the EkeaApiApplication class.
   
4. **Populate the database:**

    ```bash
   ./populate_db.bat
   
### Usage

   Once the above steps are completed, you can access the backend API endpoints at http://localhost:8080/endpointname. Replace endpointname with the desired endpoint, such as products, customers, or orders.
