package springplugin.generator;

import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.fmmodel.FMPersistentProperty;
import springplugin.generator.fmmodel.FMProperty;
import springplugin.generator.options.GeneratorOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FrontPerEntityGenerator extends PerEntityGenerator {

    public FrontPerEntityGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    @Override
    protected void populateContext(FMEntity entity, Map<String, Object> context) {
        context.put("entity", entity.getName());
        List<FMPersistentProperty> persistentProperties = new ArrayList<>();
        for(FMProperty property: entity.getProperties()) {
            if(property instanceof FMPersistentProperty) {
                persistentProperties.add((FMPersistentProperty) property);
            }
        }
        context.put("properties", persistentProperties);
    }
}
