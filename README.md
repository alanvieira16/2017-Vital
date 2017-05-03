# 2017-Vital
Assignment for the 2017 edition of the "Web Development and the Semantic Web" course, by Gabriel Santa Clara Ucelli and Luiz Felipe Ferreira Mai

Para adicionar JAAS (configurações do WildFly):

1 - Colocar no standalone dentro de <security-domains>

```XML

<security-domain name="vital">
    <authentication>
        <login-module code="Database" flag="required">
            <module-option name="dsJndiName" value="java:jboss/datasources/Vital"/>
            <module-option name="principalsQuery" value="select password from User u where u.email=?"/>
            <module-option name="rolesQuery" value="select role, 'Roles' from User u where u.email=?"/>
            <module-option name="hashUserPassword" value="true"/>
        </login-module>
    </authentication>
</security-domain>

```

2 - Alterar o default ainda no standalone

```XML
<default-security-domain value="vital"/>
```

