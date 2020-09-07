package springplugin.configuration;

import java.util.*;

public abstract class ApplicationConfiguration {
    protected String applicationName;
    protected String applicationPackage;
    protected String serverPort;
    protected String databaseName;
    protected String databaseHost;
    protected String databasePort;
    protected String databaseUsername;
    protected String databasePassword;


    protected ApplicationConfiguration() {}

    public ApplicationConfiguration(String applicationName, String applicationPackage) {
        this.applicationName = applicationName;
        this.applicationPackage = applicationPackage;
    }

    private static ApplicationConfiguration configuration;

    public static ApplicationConfiguration getConfiguration() {
        if(configuration == null) {
            configuration = new ApplicationConfigurationDefault();
        }
        return configuration;
    }

    public static void setConfiguration(ApplicationConfiguration configuration) {
        ApplicationConfiguration.configuration = configuration;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationPackage() {
        return applicationPackage;
    }

    public String getGeneratedCodePackage() { return getApplicationPackage() + ".generated";}

    public String getServerPort() {
        return serverPort;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getGeneratedEntitiesPackage() { return getGeneratedCodePackage() + ".entities";}
    public String getGeneratedServicesPackage() { return getGeneratedCodePackage() + ".services";}
    public String getGeneratedControllersPackage() { return getGeneratedCodePackage() + ".controllers";}
    public String getGeneratedConvertersPackage() { return getGeneratedCodePackage() + ".converters";}
    public String getGeneratedDtosPackage() { return getGeneratedCodePackage() + ".dtos"; }
    public String getGeneratedRepositoriesPackage() { return getGeneratedCodePackage() + ".repositories"; }

    public Set<String> getJavaTypes() {
        String[] typesArray = new String[] {
                "int", "long", "Long", "float", "String", "char", "double", "boolean", "byte", "Integer", "Float", "Double",
                "Boolean"
        };
        return new HashSet<>(Arrays.asList(typesArray));
    }

    public Map<String, String> getImportedTypes() {
        Map<String, String> importedTypes = new HashMap<>();
        importedTypes.put("Date", "java.util");
        return importedTypes;
    }
}
