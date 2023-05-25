# Microservices-with-Springboot

## UserService-Microservices

```python
 GetAll User
 Method-GET
 API-[hostUrl]/api/v1/users
 ```

```python
 Get Single User
 Method-GET
 API-[hostUrl]/api/v1/users/{USER-ID}
 ```
 
 ```python
Register User
Method-POST
API-[hostUrl]/auth/register

@RequestBody
{
   "email":"dummy@gmail.com",
   "address":"Dummy Address",
   "firstName":"Dummy",
   "lastName":"Dummy",
   "description":" Dummy description"
}
```
```python
Login User
Method-POST
API-[hostUrl]/auth/authenticate

@RequestBody
{
    "email":"dummy@gmail.com",
    "password":"pwd"
}
```


## RatingService-Microservice

```python
Post Rating
Method-POST
API-localhost:[hostUrl]/api/v1/ratings

@RequestBody
{
    "rating": 5,
    "feedback": "love this hotel service !!",
    "userId":{userId},
    "hotelId":{hotelId}
}
```

## HotelService-Microservice

```python
Create Hotel
METHOD-POST
API- [hostUrl]/api/v1/hotels

@RequestBody
{
    "name":"The Kathmandu Hotel",
    "location":"Balaju, Kathmandu",
    "about":"A nice hotel"
}
```


