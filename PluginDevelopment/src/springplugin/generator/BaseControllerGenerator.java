package springplugin.generator;

import freemarker.template.TemplateException;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.fmmodel.FMProperty;
import springplugin.generator.options.GeneratorOptions;

import java.util.ArrayList;
import java.util.Map;

public class BaseControllerGenerator extends PerClassGenerator {


    public BaseControllerGenerator(GeneratorOptions generatorOptions) {
        super(generatorOptions);
    }

    @Override
    protected void populateContext(FMEntity cl, Map<String, Object> context) throws TemplateException {
        context.put("className", cl.getName());
        //TODO: Add searchable properties to list
        context.put("findByProperty", new ArrayList<FMProperty>());
    }
}
