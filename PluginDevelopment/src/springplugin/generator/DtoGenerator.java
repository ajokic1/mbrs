package springplugin.generator;

import java.util.Map;

import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.options.GeneratorOptions;

public class DtoGenerator extends PerEntityGenerator {

	public DtoGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
	}

	@Override
	protected void populateContext(FMEntity entity, Map<String, Object> context)  {
		context.put("appPackage", ApplicationConfiguration.getConfiguration().getApplicationPackage());
        context.put("entityName", entity.getName());
        context.put("properties", entity.getProperties());
        context.put("getJavaTypes", ApplicationConfiguration.getConfiguration().getJavaTypes());
	}

}