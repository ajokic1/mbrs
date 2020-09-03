package springplugin.generator;

import freemarker.template.TemplateException;
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

    public void generate() {
        try {
            super.generate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Writer out;
        Map<String, Object> context = new HashMap<>();
        try {
            out = getWriter("", "");
            if(out != null) {
                context.clear();
                populateContext(context);
                getTemplate().process(context, out);
                out.flush();
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private void populateContext(Map<String, Object> context) {
        context.put("projectName", ApplicationConfiguration.getConfiguration().getApplicationName());
        context.put("port", ApplicationConfiguration.getConfiguration().getServerPort());
        context.put("databaseName", ApplicationConfiguration.getConfiguration().getDatabaseName());
        context.put("databaseHost", ApplicationConfiguration.getConfiguration().getDatabaseHost());
        context.put("databasePort", ApplicationConfiguration.getConfiguration().getDatabasePort());
        context.put("databaseUsername", ApplicationConfiguration.getConfiguration().getDatabaseUsername());
        context.put("databasePassword", ApplicationConfiguration.getConfiguration().getDatabasePassword());
    }


}
