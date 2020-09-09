package springplugin.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.fmmodel.FMPersistentProperty;
import springplugin.generator.fmmodel.FMProperty;
import springplugin.generator.options.GeneratorOptions;

public class DtoGenerator extends PerEntityGenerator {

	public DtoGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
	}

	@Override
	protected void populateContext(FMEntity entity, Map<String, Object> context)  {
		context.put("appPackage", ApplicationConfiguration.getConfiguration().getApplicationPackage());
        context.put("entityName", entity.getName());
		List<FMPersistentProperty> persistentProperties = new ArrayList<>();
		for(FMProperty property: entity.getProperties()) {
			if(property instanceof FMPersistentProperty) {
				persistentProperties.add((FMPersistentProperty) property);
			}
		}
		context.put("properties", persistentProperties);
        context.put("getJavaTypes", ApplicationConfiguration.getConfiguration().getJavaTypes());
	}

}
