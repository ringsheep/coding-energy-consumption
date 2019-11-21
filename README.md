# Energy Consumption management system
This project create a simple system that allows to receive and collect data about energy consumption from different villages.

## Quick start
Docker way:
```
TBD, not done yet :)
```
Simple jar launch:
```
./gradlew clean build && java -jar build/libs/coding-energy-consumption-0.0.1-SNAPSHOT.jar
```

## Functionality
(TODO: export to swagger)
* Populate demo data `/populate_demo_data`
```
curl -X GET http://localhost:8080/populate_demo_data -H 'Content-Type: application/json'
```
* Add a new consumption record for an existing counter `/counter_callback`
```
curl -X POST \
  http://localhost:8080/counter_callback \
  -H 'Content-Type: application/json' \
  -d '{
    "counter_id": "3",
    "amount": 1010.123
}'
```
* Get additional information about the counter `/counter?id=3`
```
curl -X GET 'http://localhost:8080/counter?id=3' -H 'Content-Type: application/json'
```
* Create a consumption report for any period from now. Supports Long time amount and seconds(s), minutes(m), hours(h) and days(d) as units. `/consumption_report?duration=10m`
```
curl -X GET 'http://localhost:8080/consumption_report?duration=10m' -H 'Content-Type: application/json'
```

## Technologies & frameworks: 
* Java 11, Spring Boot, H2, Junit, Mockito

## Requirements
* JDK 11

## TODO
* Dockerize app
* Integration tests using WireMock
* Swagger documentation
* Remove DemoController and use a sql file for demo db initialization
* Improve requests validation through validator pattern and domain-specific exceptions 
* Improve logging to log some major events (like working with db and errors)
* Add caching for reports