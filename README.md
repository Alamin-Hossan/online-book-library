# online-book-library
## Online Book Library - Help
## Introduction
Welcome to the Online Book library system.These API allow you to manage user by registering with some validation, log in, manage books, including creating, updating, find by author , find by author and book name and deleting books. It also provides endpoints to retrieve book information.

Base URL
The base URL for accessing the online Book Library API is [(http://localhost:8081/user/register)](http://localhost:8081/user/register)

##Authentication
The API requires authentication for certain endpoints. To authenticate, include a valid JWT token in the Authorization header of your requests.

Error Handling
The API returns appropriate HTTP status codes and error messages for different scenarios. Here are some common error responses:

- 400 Bad Request: Invalid request parameters or missing required fields.
- 401 Unauthorized: Authentication failed or missing authentication token.
- 403 Forbidden: Access to the resource is forbidden.
- 404 Not Found: The requested resource was not found.
- 409 Conflict: Conflict occurred, such as when creating a book that already exists.
- 500 Internal Server Error: An unexpected error occurred on the server.

## Instruction
At first you need to register or log in. Without Log in it will not allow you to hit http://localhost:8081/books/ endpoint.

### Register
For register you have to hit http://localhost:8081/user/register  point enter information with json file under the body. Here email should be valid and pssword should contain minimum of 8 character with Capital letter and symbol.

```http
  [POST http://localhost:8081/user/register](http://localhost:8081/user/register)
```
here is a example file for register JSON
{
  "firstName": "Alamin",
  "lastName": "Hossain",
  "email": "mdalamin.hossain@bjitacademy.com",
  "password": "Alamin@12345",
  "address": "kuril,Dhaka",
  "roles": ["CUSTOMER", "ADMIN"]
}

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | `string` | for user first name |
| `lastName` | `string` | for user last name |
| `email` | `email` | for user email|
| `password` | `string` | At least 8 character with Capital letter and symbol |
| `address` | `string` | for user address |
| `roles` | `string` | for user roles |

### Log In
For log in you have to hit http://localhost:8081/user/login  point enter information with json file under the body. 

```http
  [POST http://localhost:8081/user/login](http://localhost:8081/user/login)
```
here is a example file for login JSON
{
  "email": "mdalaminhossain@bjitacademy.com",
  "password": "Alamin@12345"
}
After log in a token will be generated. with that you can access other endpoint.

### Create a Book
Create a new book. for this to token is needed.

URL: [http://localhost:8081/books/create](http://localhost:8081/books/create)
Method: POST
Authentication: Required (Admin role)
Request Body:
{
  "name": "Sample ",
  "author": "Abc",
  "price": 500,
  "year": 2023,
	"quantity": 15,
  "description": "This is a sample book 3."
}

### View all books
VIew all available books. for this to token is needed.

URL: [http://localhost:8081/books/all]([http://localhost:8081/books/create](http://localhost:8081/books/all))
Method: GET
Authentication: Required (Admin or Customer role)



