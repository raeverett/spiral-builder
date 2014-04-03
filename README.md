## Installation ##

This project uses Maven for build management. To build the project artifacts navigate to the root project directory and run the following command.

```sh
mvn clean:install
```

The project runs both a Web Service and a Web Application. We can use Maven to launch the Web Service by issuing the following command.

```sh
mvn exec:java --projects service
```

The Web Service automatically runs on port 31415 (that's pi!). If you need to use a different port then you will need to modify the following files before installing. (Sorry. This could be handled better.)

```sh
service > Main.java
webapp  > spiral.js
```

Finally, we can use Maven to launch the Web Application by issuing the following command.

```sh
mvn jetty:run --projects webapp
```

The Web Application automatically runs on port 8080. If this port is already in use then you can use a different port by issuing the following command instead.

```sh
mvn jetty:run -Djetty.port=1234 --projects webapp
```

To view the Web Application go the following address in your browser, using the correct port if you needed to override the default.

```sh
http://localhost:8080/
```

Of course you can always just use the build artifacts for the webapp and service and deploy them yourself. The respective files are:

```sh
spiral/webapp/target/spiral-webapp.war
spiral/service/target/spiral-service-1.0-jar-with-dependencies.jar
```

## Compatibility ##

This has only been tested using Java 1.7 and Chrome 33.0 on Mac OSX 10.8.

The Web Service and Web Application should run on any Java compatible platform and the Web Application should be viewable in any HTML5 compatible browser. YMMV.


## Requirements ##
The requirements for this project are based on the following exercise.

Write some code that accepts an integer and prints the integers from 0 to that input integer in a spiral format.

For example, if I supplied 24 the output would be:

```
20 21 22 23 24
19  6  7  8  9
18  5  0  1 10
17  4  3  2 11
16 15 14 13 12
```

## Design ##

### Considerations ###
One of the important principles of software development is to only develop what you need. However, I added the following requirements to allow me to better demonstrate OO design principles.

* Rotate the spiral counter-clockwise
* Start the spiral in all four directions
* Start the spiral with the input number and descend
* Implement a full-stack with a Web Application consuming a Web Service

### Assumptions ###

* The project should work and be well-designed, but corner cases do not need to be exhaustively tested.
* Robust security, error handling and logging do not need to be implemented.
* Integration testing of the Web Service is sufficient to drive development.

### Patterns ###

I employed the following design patterns and principles.

* Builder Pattern
  * `TableBuilder` to build a generic table
* Composition over Inheritance
  * `SpiralBuilder` wraps the `TableBuilder` to build a pre-filled table
  * `ForwardSpiralBuilder` wraps `SpiralBuilder` to build a spiral in forward order
  * `ReverseSpiralBuilder` wraps `SpiralBuilder` to build a spiral in reverse order
  * *Note:* I probably could have used a Strategy Pattern here
* Factory Pattern
  * Provides a new instance of the needed builder to the service resource

### Frameworks ###

* [Jersey](https://jersey.java.net/) for the web service (not technically REST)
* [AngularJS](http://angularjs.org/) for the web application
* [Guice](https://code.google.com/p/google-guice/) for dependency injection
* [jUnit](http://junit.org/) for testing

I used this exercise as an opportunity to learn the Jersey and AngularJS frameworks. This is my first time using them so feedback is encouraged.
