package springplugin.generator;

import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.options.GeneratorOptions;

import java.util.Map;

public class BaseRepositoryGenerator extends PerEntityGenerator {


    public BaseRepositoryGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    @Override
    protected void populateContext(FMEntity entity, Map<String, Object> context) {
        context.put("appPackage", ApplicationConfiguration.getConfiguration().getApplicationPackage());
        context.put("entityName", entity.getName());
        context.put("queryableProperties", entity.getQueryableProperties());
    }
}
