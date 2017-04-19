# JEE Series
JEE 7 examples and experiments. Inspired by [Netbean's JEE Learning Trail](https://netbeans.org/kb/trails/java-ee.html).

Key features:
* runs on Maven: IDE independent, minimalistic configuration
* JEE7 and Java 8 contribute to clean and concise code
* unit tests (JUnit 4) and integration tests (Arquillian)

The entire project can be downloaded an built at once
```bash
git clone git@github.com:zezutom/JEE-Series.git && cd ./JEE-Series && mvn clean install
```
Unless stated otherwise, all apps have been tested on Payara 4.1 (a better maintained version of Glassfish).

### [JSP + Session Bean Example](web-jsp-bean)
* A classic "What's your name" sample app
* Based on [Introduction to Developing Web Applications](https://netbeans.org/kb/docs/web/quickstart-webapps.html)

### [JSF + EJB](web-jsf-ejb)
* Another tutorial example, use case: capture and persist user input
* JSF2 + EJB with JPA
* Based on: [Getting Started with JEE Applications](https://netbeans.org/kb/docs/javaee/javaee-gettingstarted.html)

### ["Guess a Number" Game](web-jsf-ajax)
* Expands on the use of JSF technology outlined in the previous example
* Web interface of a simple "guess a number" game, AJAX to avoid full page reloads
* Based on: [Introduction to Java Server Faces 2.0](https://netbeans.org/kb/docs/web/jsf20-intro.html)

### [Simple JEE app: Tribute to Famous Composers](web-jsf-ajax-rest)
* Showcases a number of technologies, use case: a searcheable database of famous composers
* Key features:
  * JPA relationship management
  * Separation of persistence from presentation (entities vs DTOs)
  * Programmatic DTOs via JAXP and JAX-RS endpoints
  * Advanced query capabilities (fuzzy search)
  * UI via AngularJS + Bootstrap, infinite scrolling and content filtering
* Based on: [Introduction to Ajax for Java Web Applications](https://netbeans.org/kb/docs/web/ajax-quickstart.html)