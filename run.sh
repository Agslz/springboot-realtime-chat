#!/bin/bash

# Comprobamos si Docker está instalado
if ! command -v docker &> /dev/null
then
    echo "Docker no está instalado. Por favor, instálalo antes de continuar."
    exit
fi

# Comprobamos si Docker Compose está instalado
if ! command -v docker-compose &> /dev/null
then
    echo "Docker Compose no está instalado. Por favor, instálalo antes de continuar."
    exit
fi

# Levantamos los servicios con Docker Compose
docker-compose up -d

# Esperamos unos segundos para asegurarnos de que los servicios estén arriba
sleep 10

# Construimos y ejecutamos la aplicación Spring Boot
./mvnw clean install
./mvnw spring-boot:run
