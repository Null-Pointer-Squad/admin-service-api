# Admin Management and Authentication Service
This project is an admin management and authentication service built using Spring Boot, Spring Security, and JSON Web Tokens (JWT). It provides a secure and efficient way to manage admin users, authenticate them using email and password, and generate JWTs for accessing protected resources. Additionally, admins have the ability to add other admins and activate or deactivate their accounts.

## Deployemnt:

https://admin-service-api.onrender.com

## Features:
- User Authentication: Admin users can log in using their email and password.
- JSON Web Tokens (JWT): After successful authentication, admins receive a JWT that can be used to access protected resources.
- Admin Management: Admins have the ability to add new admins, activate or deactivate their accounts, and manage their permissions.
- Secure Password Storage: Admin passwords are securely stored using hashing algorithms to ensure data privacy.
- Role-Based Access Control: Different admin roles can be assigned to control access to different resources.
## Technologies Used:
- Spring Boot: A Java framework used for building robust and scalable applications.
- Spring Security: Provides authentication and authorization mechanisms for securing Spring applications.
- JSON Web Tokens (JWT): A compact and self-contained mechanism for transmitting authentication credentials.
- Java Persistence API (JPA): A standard API for accessing databases in Java applications.
- Hibernate: An Object-Relational Mapping (ORM) framework for Java.
- PostgreSql: A popular open-source relational database management system.
- Maven: A build automation tool used for managing dependencies and building the project.
## Getting Started:
1. Clone the repository: Clone the project repository to your local machine.
```
git clone https://github.com/Null-Pointer-Squad/admin-service-api.git
```
2. Configure the Database in the docker-compose file
```
  db:
    image: postgres:latest
    environment:
      POSTGRES_USER: username
      POSTGRES_PASSWORD: password
      POSTGRES_DB: admins
    ports:
      - "5432:5432"
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/admins
      SPRING_DATASOURCE_USERNAME: username
      SPRING_DATASOURCE_PASSWORD: password
```

3. Build the Project:
   ```
   mvn clean install
   ```
4. run th docker compose:
```
docker compose up
```
## Usage:
1. Login using these credintials -> mail : admin@fawry.com , password : 123456
2. access protected resources using token sent after a successfull login 
