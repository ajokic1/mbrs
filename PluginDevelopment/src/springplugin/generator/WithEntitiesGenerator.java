package springplugin.generator;

import freemarker.template.TemplateException;
import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMModel;
import springplugin.generator.options.GeneratorOptions;

import javax.swing.*;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class WithEntitiesGenerator extends BasicGenerator {
    public WithEntitiesGenerator (GeneratorOptions generatorOptions) {
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
                context.put("applicationName", ApplicationConfiguration.getConfiguration().getApplicationName());
                context.put("entities", FMModel.getInstance().getClasses());
                getTemplate().process(context, out);
                out.flush();
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

}
