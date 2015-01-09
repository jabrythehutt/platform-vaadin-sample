# Simple Vaadin authentication application

This project demonstrates the use of global method security ([Spring security](http://projects.spring.io/spring-security/)) and [Vaadin](https://vaadin.com/home) with [vaadin4spring](https://github.com/peholmst/vaadin4spring) for web-based authentication/authorization without the headache of js.

## Quick start ##

1. Clone this repository: ```$ git clone https://github.com/djabry/platform-vaadin-sample.git```
2. Change into the root directory of the working copy and run: ```$ mvn clean install```
3. Run with the command: ```$ mvn jetty:run```
4. Browse to http://localhost:8080/
5. Log in as username: "john.smith" password: "password"

## An even quicker start ##

1. Launch in the cloud with [Codenvy](https://codenvy.com/f?id=6lvxcm9yx9asfdby)
2. Click "Run" (You can adjust the RAM to 512mb with the "Custom Run" menu item if the application defaults to 1024mb)
3. Log in as username: "john.smith" password: "password"