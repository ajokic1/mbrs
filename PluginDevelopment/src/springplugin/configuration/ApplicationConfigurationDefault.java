package springplugin.configuration;

public class ApplicationConfigurationDefault extends ApplicationConfiguration {
    public ApplicationConfigurationDefault() {
        super();
        this.applicationName = "example_application";
        this.applicationPackage = "com.example.application";
        this.serverPort = "8080";
        this.databaseName="example_application_db";
        this.databaseUsername="root";
        this.databasePassword="root";
        this.databaseHost="localhost";
        this.databasePort="3306";
    }
}
