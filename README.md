# Microservices-with-Springboot

#UserService-Microservices
```python
-GetAll User
 Method-GET
 localhost:{API-GATEWAY PORTNUMBER}/api/v1/users
 ```

-Get Single User
 METHOD-GET
 localhost:{API-GATEWAY PORTNUMBER}/api/v1/users/{USER-ID}
 
-Register User
METHOD-POST
localhost:{API-GATEWAY PORTNUMBER}/auth/register
@RequestBody
{
   "email":"dummy@gmail.com",
   "address":"Dummy Address",
   "password":"pwd",
   "firstName":"Dummy",
   "lastName":"Dummy",
   "description":" Dummy description",
   "isActive":false
}


#RatingService-Microservice
- Post Rating
METHOD-POST
localhost:{API-GATEWAY PORTNUMBER}/api/v1/ratings
@RequestBody
{
    "rating": 5,
    "feedback": "love this hotel service !!",
    "userId":{userId},
    "hotelId":{hotelId}
}

#HotelService-Microservice
- Post Hotel
METHOD-POST
localhost:{API-GATEWAY PORTNUMBER}/api/v1/hotels
@RequestBody
{
    "name":"The Kathmandu Hotel",
    "location":"Balaju, Kathmandu",
    "about":"A nice hotel"
}



