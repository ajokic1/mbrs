package springplugin.configuration;

public abstract class ApplicationConfiguration {
    protected String applicationName;
    protected String applicationPackage;

    protected ApplicationConfiguration() {}

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
}
