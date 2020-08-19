package springplugin.generator;

import freemarker.template.TemplateException;
import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.fmmodel.FMModel;
import springplugin.generator.options.GeneratorOptions;

import javax.swing.*;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public abstract class PerClassGenerator extends BasicGenerator {

    public PerClassGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    @Override
    public void generate() {
        try {
            super.generate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ApplicationConfiguration configuration = ApplicationConfiguration.getConfiguration();
        for (FMEntity cl: FMModel.getInstance().getClasses()) {
            Writer out;
            Map<String, Object> context = new HashMap<>();
            try {
                out = getWriter(cl.getName(), cl.getTypePackage());
                if(out != null) {
                    context.clear();
                    context.put("appPackage", configuration.getApplicationName());
                    populateContext(cl, context);
                    getTemplate().process(context, out);
                    out.flush();
                }
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void populateContext(FMEntity cl, Map<String, Object> context)
            throws TemplateException;



}
