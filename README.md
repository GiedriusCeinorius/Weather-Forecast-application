# Weather-Forecast-application

## Build and Run project:

1. In "Agmis_assignment" directory "mvn clean install"
2. In "target" directory "java -jar weather-forecast-app-0.0.1-SNAPSHOT.jar"
3. Open "Postman"

## How to use:

**1. Basic Auth for HTTP methods POST, PUT, DELETE:**
Username - admin
Password - admin1

**2. To fetch a list of cities with basic current weather information:**

HTTP method GET

e.g. http://localhost:8080/get/basicWeatherInfoAllCities

**3. To filter cities, by their area(country):**

e.g. http://localhost:8080/get/basicWeatherInfoByCountry/LT

**4. To filter cities population:**

e.g. http://localhost:8080/get/basicWeatherInfoByPopulation/from/300000/to/400000

**5. To fetch a single city with extended current weather information:**

e.g. http://localhost:8080/get/extendedWeatherInfoByCity/Kaunas

**6. Option to this request to include a 5 day/3 hour forecast:**

e.g. http://localhost:8080/get/extendedWeatherInfoByCity/Vilnius/fiveDaysForecast

**7. To fetch current weather forecast for user provided coordinates:**

e.g. http://localhost:8080/get/WeatherInfoByCoordinates

Body -> RAW -> JSON(application/json) 

{

"lon": 25.2798,

"lat": 54.689159

}

**8. Option to this request to include a 5 day/3 hour forecast:**

e.g. http://localhost:8080/get/weatherInfoByCoordinates/fiveDaysForecast

{

"lon": 28.190281,

"lat": 59.37722

}

**9. To add a city to the list of available cities for the weather forecast:**

e.g. http://localhost:8080/add/cityForForecast

HTTP method POST

{
    "name": "Panevezys",
    
"country": "LT",
    
"population":  88678, 
    
"coord": {
      
"lon": 24.35,
      
"lat": 55.73333
    
}
  
}

**10. To remove a city from the list of available cities:**

HTTP method DELETE

e.g. http://localhost:8080/remove/cityWithName/Narva

**11. To update a city information:**

HTTP method PUT

e.g. http://localhost:8080/update/City


{
    "name": "Panevezys",
    
"country": "LT",
    
"population":  11111, 
    
"coord": {
      
"lon": 24.354444,
      
"lat": 55.75555
    
}
  
}

**12. To add user:**

HTTP method POST

e.g. http://localhost:8080/add/User

     {
        "userName": "Popovas",
        "password": "neTasSitas",
        "firstName": "Arnoldas",
        "lastName": "Scwarzenneggeris",
        "homeLocation": {
            "country": "USA",
            "street": "Stromg men st.",
            "houseNumber": 1
        }, 
        "favoriteCities": ["Druskininkai", "Mikenai"]
     }

**13. To update user information:**

HTTP method PUT

e.g. http://localhost:8080/update/User

   {
        "userName": "Popovas",
        "password": "kitas",
        "firstName": "Silvesteris",
        "lastName": "Scwarzenneggeris",
        "homeLocation": {
            "country": "USA",
            "street": "Stromg men st.",
            "houseNumber": 1
        }, 
        "favoriteCities": ["Druskininkai", "Mikenai"]
     }

**14. To remove user information:**

HTTP method PUT

e.g. http://localhost:8080/remove/UserInformation/Popovas

**15. To retrieve a list of users:**

HTTP method GET

e.g. http://localhost:8080/get/AllUsers

**16. To filter users by first name:**

e.g. http://localhost:8080//get/UsersByFirstName/{firstName}

**17. To filter users by last name:**

HTTP method GET

e.g. http://localhost:8080/get/UsersByLastName/Last Name

**18. To filter users by favorite cities:**

e.g. http://localhost:8080/get/UsersByFavoriteCity/Favorite cities

**19. To retrieve single user:**

e.g. http://localhost:8080/get/UserByUserName/{username}

