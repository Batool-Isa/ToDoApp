# ToDo App

## About

This is my ToDo App project for the Spring Boot lab. We are building it step by step, and currently I’m working on Step 3(a), which focuses on the Category API.

## GitHub Repo

https://github.com/Batool-Isa/ToDoApp

## Design Decisions

I followed the MVC structure and separated my project into Controller, Service, Model, and Repository layers. The controller handles API requests, the service contains the business logic, the model represents the data, and the repository handles database access.

I chose this structure to separate responsibilities and keep the code organized and easier to maintain.

## What Went Right

I set up the Spring Boot project, connected it to PostgreSQL, and started implementing the Category CRUD API. So far, I have implemented getting all categories, getting a category by ID, and creating a category.

## Challenges

Understanding how the different layers work together and connecting Spring Data JPA with PostgreSQL.

## What I Enjoyed

I enjoyed structuring the project and linking the different layers together, especially seeing how the API connects to the database.

## Screenshots

### Get All Categories
![Get All Categories](Assest/getAllCategories.png)

### Get Category by ID
![Get Category by ID](Assest/getCategoryByID.png)

### Create a Category
![Create a Category](Assest/createCategory.png)

### Update a Category
![update category](Assest/updateCategory.png)
### Delete a Category
![delete category](Assest/deleteCategory.png)
### Get All Items
![Get All Categories](Assest/getItems.png)

### Get Item by ID
![Get Category by ID](Assest/getItem.png)

### Create an Item
![Create a Category](Assest/createItem.png)

### Update an Item
![update category](Assest/updateItem.png)
### Delete an Item
![delete category](Assest/delete%20Item.pnge)