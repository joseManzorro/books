# Books

This project provides a REST API which exposes endpoints two entities named Author and Book.

## Examples
```
GET /api/authors/1
GET /api/authors?page=2&limit=1

GET /api/books/2
GET /api/books?page=0&limit=3

Create author
POST /api/authors

Add Book to Author with ID 5
PUT /api/authors/5/book
```

Run the service on local and hit it using requests contained on: </br>
```
Books.postman_collection.json
```

## Validations

The API won't allow to add 2 Books with the same Title for the same Author.

First name is a required field for Author.</br>
Title is required for Book.

Page size should be greater than zero.
