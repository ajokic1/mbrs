package springplugin.generator;

import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.fmmodel.FMModel;
import springplugin.generator.options.GeneratorOptions;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class ApplicationYmlGenerator extends BasicGenerator {


    public ApplicationYmlGenerator (GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

/*  
    @Override
    public void generate() {
        try {
            super.generate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    protected abstract void populateContext(FMEntity cl, Map<String, Object> context)
            throws TemplateException;
            
     protected void populateContext(FMentity, Map<String, Object> context) {
    	context.put("projectName", entity.getProjectName());
		context.put("port", entity.getPort());
		context.put("databaseUrl", entity.getDatabaseUrl());
		context.put("databaseUsername", entity.getDatabaseUsername());
		context.put("databasePassword", entity.getDatabasePassword());
    }
}*/

}
