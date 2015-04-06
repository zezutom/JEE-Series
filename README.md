# JEE Series
JEE 7 examples and experiments. Inspired by [Netbean's JEE Learning Trail](https://netbeans.org/kb/trails/java-ee.html).

My implementation:
* doesn't dependend on any IDE
* runs on Maven
* has only a minimum configuration
* includes test automation

## [Simple Web App](https://github.com/zezutom/JEE-Series/tree/master/SimpleWebJSP)
Remake of [Introduction to Developing Web Apps](https://netbeans.org/kb/docs/web/quickstart-webapps.html)

Highlights:
* in sync with the original example showcases the use of JSP and JavaBeans
* runs on Tomcat (JEE 7 requires Tomcat 8)
* integration tests via [HtmlUnit](http://htmlunit.sourceforge.net)

## [Struts Showcase](https://github.com/zezutom/JEE-Series/tree/master/SimpleWebStruts)
Inspired by [Introduction to the Struts Framework](https://netbeans.org/kb/docs/web/quickstart-webapps-struts.html)

Highlights:
* uses the latest features of Struts 2.x
* almost no configuration thanks to the [Convention Plugin](https://struts.apache.org/docs/convention-plugin.html)
* validations are handled via annotations rather than programatically
* form post using Struts tags 
* runs on Tomcat 8
