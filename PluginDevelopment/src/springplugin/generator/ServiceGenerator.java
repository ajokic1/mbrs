package springplugin.generator;

import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.options.GeneratorOptions;

import java.util.Map;

public class ServiceGenerator extends PerEntityGenerator {


    public ServiceGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    @Override
    protected void populateContext(FMEntity entity, Map<String, Object> context) {
        context.put("appPackage", ApplicationConfiguration.getConfiguration().getApplicationPackage());
        context.put("entityName", entity.getName());
        context.put("queryableProperties", entity.getQueryableProperties());
    }
}
