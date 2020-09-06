package springplugin.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.*;
import springplugin.generator.options.GeneratorOptions;

public class ModelGenerator extends PerEntityGenerator{

	public ModelGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
	}

	@Override
	protected void populateContext(FMEntity entity, Map<String, Object> context) {
		context.put("appPackage", ApplicationConfiguration.getConfiguration().getApplicationPackage());
        context.put("entityName", entity.getName());
        context.put("getJavaTypes", ApplicationConfiguration.getConfiguration().getJavaTypes());
        context.put("schema", entity.getSchema());
        context.put("properties", entity.getProperties());
        classifyProperties(entity.getProperties(), context);
	}

	private void classifyProperties(List<FMProperty> properties, Map<String, Object> context) {
		List<FMGeneratedValueProperty> generatedValueProperties = new ArrayList<>();
		List<FMPersistentProperty> persistentProperties = new ArrayList<>();
		List<FMOwningProperty> owningProperties = new ArrayList<>();
		List<FMInverseProperty> inverseProperties = new ArrayList<>();
		for(FMProperty property: properties) {
			if(property instanceof FMGeneratedValueProperty)
				generatedValueProperties.add((FMGeneratedValueProperty) property);
			else if (property instanceof FMPersistentProperty)
				persistentProperties.add((FMPersistentProperty) property);
			else if (property instanceof FMOwningProperty)
				owningProperties.add((FMOwningProperty) property);
			else if (property instanceof FMInverseProperty)
				inverseProperties.add((FMInverseProperty) property);
		}
		context.put("generatedValueProperties", generatedValueProperties);
		context.put("persistentProperties", persistentProperties);
		context.put("owningProperties", owningProperties);
		context.put("inverseProperties", inverseProperties);
	}

}
