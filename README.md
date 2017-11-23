# spring-data-rest-associations

## Test Guide
This example is OneToMany Guide for Spring-data-rest
In this example, deck and card data were entered via Initializer, but required mapping is required.
So, You should better use [postman](https://www.getpostman.com/) to test 

```
POST http://localhost:8080/api/decks/1/cards
Headers Content-Type : text/uri-list
Body (text) : http://localhost:8080/api/cards/1 
```
