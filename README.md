# Enviro365 File Data Processing API

This project is a Spring Boot application that provides a RESTful API for Enviro365 clients to upload environmental data files for processing and to retrieve the processed results. 

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Setup](#setup)
- [API Endpoints](#api-endpoints)
- [Error Handling](#error-handling)
- [Logging](#logging)
- [Swagger Documentation](#swagger-documentation)
- [License](#license)

## Features

- Upload environmental data files
- Retrieve processed results
- Robust error handling
- Logging for monitoring and troubleshooting
- API documentation with Swagger

## Requirements

- Java 11 or higher
- Maven 3.6.0 or higher

## Setup

4. **Access the application:**

    The application will run on `http://localhost:8080`.

## API Endpoints

### Upload File

- **URL:** `/api/upload`
- **Method:** `POST`
- **Request:**

    ```bash
    curl -F "file=@path/to/your/file.txt" http://localhost:8080/api/upload
    ```

- **Response:**

    ```json
    {
        "fileId": 1,
        "message": "File uploaded successfully"
    }
    ```

### Retrieve Processed Results

- **URL:** `/api/results/{fileId}`
- **Method:** `GET`
- **Response:**

    ```json
    "Processed: <processed data>"
    ```

## Error Handling

The application handles errors gracefully and returns appropriate HTTP status codes along with error messages. Common error responses include:

- `404 Not Found` for non-existent files
- `500 Internal Server Error` for general server errors

## Logging

API requests, responses, and errors are logged for monitoring and troubleshooting purposes. The logs include detailed information about each API interaction.

## Swagger Documentation

Swagger is integrated for API documentation. You can access the Swagger UI to explore and test the API endpoints:

- **URL:** `http://localhost:8080/swagger-ui.html`

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
