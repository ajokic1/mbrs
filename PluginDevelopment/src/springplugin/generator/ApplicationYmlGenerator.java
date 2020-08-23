package springplugin.generator;

import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.options.GeneratorOptions;

import java.util.Map;

public class ApplicationYmlGenerator extends BasicGenerator {


    public ApplicationYmlGenerator (GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    /* 
    @Override
    protected void populateContext(entity, Map<String, Object> context) {
    	context.put("projectName", entity.getProjectName());
		context.put("port", entity.getPort());
		context.put("databaseUrl", entity.getDatabaseUrl());
		context.put("databaseUsername", entity.getDatabaseUsername());
		context.put("databasePassword", entity.getDatabasePassword());
    }*/
}
