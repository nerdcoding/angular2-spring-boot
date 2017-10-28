
This is a small sample web application consists of a client and a server 
application. The server is a Spring Boot application and the client
uses Angular2.

## Server

The server application runs with Spring Boot and provides some RESTful
web services. The Server contains an embedded MongoDB which is initialized
during server startup and filled with some test data.

### Build & Startup

The only requirement to build and run the server application is an 
installed Java runtime with version 1.8 or higher available in your
`PATH` variable.

To build the application run the `gradlew` script:

```
gradlw build
```

After the build was successful start the application:

```
java -jar build/libs/server-1.0.0-SNAPSHOT.jar
```

The server application is accessible with at `http://127.0.0.1:8080`.

## Client

The client is a Angular2 application which uses the Spring Boot application
as backend. It must be built and started independent from the server. 

### Build & Startup

Node.js and npm needs to be installed to build and start the client
application. Verify that you have at least node v4.x.x and  npm 3.x.x 
installed by running `node -v` and `npm -v` in a terminal. 

To install all required dependencies simply run:

```
npm install
```

Afterwards the Typescript classes have to be transpiled to Javascript and a 
http server needs to be started which delivers the client application. 
This is done with:

```
npm start
```

The server application is accessible with at `http://127.0.0.1:3000`. To
login into the application the user `admin` with the password `admin` 
could be used.

## Licensing
The software of this repository is licensed under the GNU Lesser General 
Public License v3. See [LICENSE](https://github.com/nerdcoding/angular2-spring-boot/blob/master/LICENSE) 
for the full license text.


