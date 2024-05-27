# BIOmed Back End

## Herramientas de Desarrollo Empleadas

### Frameworks
- [Java V17](https://www.java.com/es/download/ie_manual.jsp)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Javax Validation](https://www.baeldung.com/javax-validation)
- [Jackson Annotations](https://www.baeldung.com/jackson-annotations)
- [Hibernate Validator](https://hibernate.org/validator/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Boot Dev Tools](https://www.baeldung.com/spring-boot-devtools)
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Project Lombok](https://projectlombok.org/)
- [Slf4j](https://www.slf4j.org/)

### Base de Datos
- [PostgreSQL](https://www.postgresql.org/)
- [PgAdmin](https://www.pgadmin.org/)
- [Flyway DB](https://flywaydb.org/)

### Despliegue en Producción

## Requisitos para Construir y Ejecutar la Aplicación
- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Gradle](https://gradle.org/install/)
- [Git](https://git-scm.com/downloads)
- [VS Code](https://code.visualstudio.com/#alt-downloads)

### Documentación de Swagger

## Pasos para Abrir el Proyecto

1. Abrir y configurar VS Code con las siguientes extensiones:
   - [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack)
   - [Language Support for Java(TM) by Red Hat](https://marketplace.visualstudio.com/items?itemName=redhat.java)

2. En la línea de comandos de la terminal de su elección ejecutar:
    ```sh
    git clone url_del_repositorio
    ```

3. En VS Code, en el directorio donde se ejecutó el comando del paso 2:
    ```sh
    File -> Open Folder -> Seleccionar y abrir el repositorio clonado
    ```

4. En la línea de comandos de la terminal de su elección ejecutar:
    ```sh
    ./run_app.sh
    ```

5. En VS Code:
   - Presionar F5 o utilizar el botón "Run" del Spring Boot Dashboard en el Explorador de VS Code

## Para Probar el Proyecto

### Colección POSTMAN para Pruebas en Máquina
- [PostMan Collection](https://www.getpostman.com/collections/edf5e231fa09adb0a63b)

### Endpoints para Probar en Ambiente de Producción

#### /api/upload-document
```sh
curl --location --request PUT 'https://tripulantes.herokuapp.com/api/document/1abc' \
--header 'Content-Type: multipart/form-data;boundary=<calculated when request is sent>' \
--form 'file=@"/D:/Documentos/2019-07-23-FORMATO-CESION-DE-CONTRATO-V2.pdf"' \
--form 'request_body="{
  \"document_properties\": {
    \"document_type\": \"0\",
    \"document_area\": {
      \"original_area_code\": \"originalAreaCode\",
      \"destination_area_code\": \"destinationAreaCode\",
      \"area_name\": \"areaName\"
    },
    \"documental_user\": {
      \"user_origin_code\": \"userOriginCode\",
      \"user_destiny_code\": \"userDestinyCode\",
      \"user_lend_code\": \"lendUserCode\"
    },
    \"correspondence_type\": \"INTERN\",
    \"document_priority\": \"MEDIUM\",
    \"printed_tag\": \"printedTag\",
    \"documental_dates\": {
      \"document_creation_details\": \"2017-01-01 20:00\",
      \"document_storage_details\": \"2017-01-01 20:00\",
      \"document_sharing_details\": \"2018-01-01 20:00\",
      \"document_delivery_date_details\": \"2019-01-01 20:00\",
      \"document_archival_date_details\": \"2019-01-01 20:00\",
      \"document_destruction_details\": \"2019-01-01 20:00\",
      \"document_modification_date_details\": \"2019-01-01 20:00\"
    },
    \"document_name\": \"documentHostedUrl\",
    \"observations\": \"Observations\"
  }
}"'
```

#### /api/download-document
```sh
curl --location --request GET 'http://localhost:8080/api/document/?storage_id=759444949&user_lend_code="zrz_lend_code"'
```

### Ejecutar el Script run_app.sh

Para ejecutar la aplicación utilizando el script `run_app.sh`, sigue estos pasos:

1. Asegúrate de que el script `run_app.sh` tenga permisos de ejecución. Si no, puedes otorgarlos con el siguiente comando:
    ```sh
    chmod +x run_app.sh
    ```

2. Ejecuta el script desde la línea de comandos:
    ```sh
    ./run_app.sh
    ```

El script `run_app.sh` debe contener los comandos necesarios para construir y ejecutar la aplicación. A continuación, se muestra un ejemplo de cómo podría ser el contenido de `run_app.sh`:

```sh
#!/bin/bash
echo "Cleaning and building the project..."
./gradlew clean build -x test

echo "Running the application..."
./gradlew bootRun
```