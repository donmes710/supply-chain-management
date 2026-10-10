# Supply Chain Management System

A full-stack web application designed to manage essential supply chain operations, including customers, products, suppliers, and orders. The project combines a Spring Boot REST API with a React-based frontend and a PostgreSQL database.

## Features

- **Authentication:** Login functionality with JWT-based authentication.
- **Customer Management:** Manage customer records.
- **Product Management:** Organize and manage product information.
- **Supplier Management:** Maintain supplier records.
- **Order Management:** Handle order-related operations.
- **REST API:** Backend endpoints for communication between the frontend and backend.
- **Database Integration:** PostgreSQL integration for persistent data storage.
- **Web Interface:** React-based frontend for interacting with the application.

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Security
- JSON Web Token (JWT)
- Maven

### Frontend
- React
- TypeScript / JavaScript
- Vite
- Tailwind CSS

### Database
- PostgreSQL

### Tools
- Git and GitHub
- Visual Studio Code
- Postman

## Project Structure

```text
SpringBootProject/
├── Front-End/
│   ├── supply-chain-frontend/
│   └── supply-chain-frontend-01/
│       └── supply-chain-management-new/
├── supply-chain-management/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   └── mvnw.cmd
├── .gitignore
└── README.md
```

## Prerequisites

Before running the application, install:

- Java JDK
- Node.js and npm
- PostgreSQL
- Git

## Backend Setup

1. Create a PostgreSQL database named `supply_chain_db`.
2. Open the `supply-chain-management` directory.
3. Configure the database connection and JWT secret using environment variables.
4. Start the Spring Boot application.

Set the following environment variables in your terminal or development environment:

```text
DB_URL=jdbc:postgresql://localhost:5432/supply_chain_db
DB_USERNAME=postgres
DB_PASSWORD=your_database_password
JWT_SECRET=your_base64_encoded_secret
```

Replace the example database credentials with your own values. Never commit real passwords or secret keys to GitHub.

Run the backend on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend is expected to run at:

`http://localhost:8080`

## Frontend Setup

Choose the frontend you want to run.

For the newer React/TypeScript frontend:

```powershell
cd Front-End/supply-chain-frontend-01/supply-chain-management-new
npm install
```

Create a local `.env` file based on `.env.example` and configure the backend API URL.

Start the development server:

```powershell
npm run dev
```

For the older React frontend, navigate to `Front-End/supply-chain-frontend` and run `npm install` followed by `npm run dev`.

Use the local URL printed by Vite in your terminal to open the frontend in your browser.

## Configuration and Security

- Keep passwords, API keys, and JWT secrets out of source control.
- Use environment variables for sensitive configuration.
- Do not use development credentials in production.
- Configure appropriate security settings before deploying the application publicly.

## Future Improvements

- Improve frontend and backend integration.
- Add validation and error handling.
- Expand automated testing.
- Improve authentication and authorization.
- Add deployment instructions and screenshots.
- Enhance the user interface and user experience.

## Author

**Mohamed Rabin**

GitHub: [@donmes710](https://github.com/donmes710)

## License

No license has been specified yet. All rights are reserved unless a license is added to this repository.
