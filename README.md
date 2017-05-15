# 2017-Vital
Assignment for the 2017 edition of the "Web Development and the Semantic Web" course, by Gabriel Santa Clara Ucelli and Luiz Felipe Ferreira Mai


### How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).

1. Install [Eclipse Neon (version 4.6.x)](http://www.eclipse.org/);

2. Install [WildFly 10.x](http://wildfly.org) and create a Server configuration within Eclipse;

3. Install [MySQL](http://www.mysql.com/products/community/) and create a schema called `vital` and a user called `vital` with password `vital` and full access to the homonymous database;

4. Configure [the MySQL JDBC driver](http://dev.mysql.com/downloads/connector/j/) in WildFly;

5. Configure the datasource in WildFly's `standalone.xml` file:

```XML
 <datasource jta="true" jndi-name="java:/jboss/datasources/Vital" pool-name="vitalPool" enabled="true" use-java-context="true" use-ccm="true">
	<connection-url>jdbc:mysql://localhost:3306/vital</connection-url>
	<driver>mysql</driver>
	<security>
	    <user-name>vital</user-name>
	    <password>vital</password>
	</security>
	<statement>
	    <prepared-statement-cache-size>100</prepared-statement-cache-size>
	    <share-prepared-statements>true</share-prepared-statements>
	</statement>
 </datasource>
```

6. Configure the security domain in WildFly's `standalone.xml` file:
```XML
 <security-domain name="vital">
    <authentication>
        <login-module code="Database" flag="required">
            <module-option name="dsJndiName" value="java:jboss/datasources/Vital"/>
            <module-option name="principalsQuery" value="select password from User u where u.email=?"/>
            <module-option name="rolesQuery" value="select role, 'Roles' from User u where u.email=?"/>
            <module-option name="hashAlgorithm" value="MD5"/>
            <module-option name="hashEncoding" value="base64"/>
            <module-option name="hashUserPassword" value="true"/>
        </login-module>
    </authentication>
</security-domain>
```
7. Set `vital` to be the default security domain in WildFly's `standalone.xml` file:
```XML
 <default-security-domain value="vital"/>
```

## Built With
* [Java EE](http://www.oracle.com/technetwork/java/javaee/overview/index.html) - Standard Java EE stack of frameworks (JSF, CDI, JPA, EJB 
* [JButler](https://github.com/dwws-ufes/jbutler) - Web framework useful for projects that use the standard Java EE stack
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Gabriel Santa Clara Ucelli**
* **Luiz Felipe Ferreira Mai**
