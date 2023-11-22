package co.za.four27.assignment.ricardominnaar;

public class TestConfigurations {
    public static final String[] PROPERTIES = new String[]{
            "spring.datasource.url=jdbc:derby:memory:local;create=true",
            "spring.datasource.driver-class-name=org.apache.derby.jdbc.EmbeddedDriver",
            "spring.datasource.username=derbyuser",
            "spring.datasource.password=password",
            "DISABLE_HTTP_SECURITY=true"
    };
}
