# EasyRent - MIU

## Getting Started

These instructions will get you a copy of the project up and running on your local machine. 

### Technologies Used
* **Java 11+**
* **Spring Boot**
* **Spring Security**
* **Gradle**
* **MySQL DB**
* **Flyway**

### Installation
```sh
$ git clone https://gitlab.com/a.maharjan/easyrent.git
$ cd easyrent/
```

### Installing Dependencies
##### On macOS and Linux:
``` 
./gradlew build --refresh-dependencies
``` 
##### On windows:
``` 
gradlew build --refresh-dependencies
```

### Pre-step
#### Setting up MySQL:
Download and install MySQL db on your system.  
You can follow either steps.  
* *Create a db according to the configuration specified on ./build.gradle .*
* *Or, change the configuration specified on ./build.gradle .*
  
### Running App
##### On macOS and Linux:
``` 
./gradlew bootRun
``` 

##### On windows:
``` 
gradlew bootRun
```

### Swagger URL:
http://localhost:8080/swagger-ui.html