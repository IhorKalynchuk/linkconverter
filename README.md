# Trendyol link converter

## Setup

### generate the JAR file

To generate the JAR file run:

```
$ mvn install -DskipTests
```

### build the Docker image

```
$ docker build -t linkconverter.jar .
```

### Run the multi-container application

```
$ docker-compose up -d
```

## Usage
### Web URL to Deeplink

Send POST request to the address http://localhost:8080/api/link/toDeeplink with body
```
{
    "webUrl": "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=55555"
}
```
### Deeplink to Web URL

Send POST request to the address http://localhost:8080/api/link/toWebUrl with body
```
{
    "deeplink": "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"
}
```
### To view records in the database connect to PostgreSQL database: 
```
Host:       localhost
Port:       5432
Username:   postgres
Password:   password
Database:   postgres
```
### Swagger: 
```
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```
