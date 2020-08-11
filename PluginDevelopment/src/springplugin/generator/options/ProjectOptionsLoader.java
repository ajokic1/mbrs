package springplugin.generator.options;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ProjectOptionsLoader {
    public static ProjectOptions loadProjectOptionsFromFile(String path, String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(path + File.separator + fileName);
        XStream xStream = new XStream(new StaxDriver());

        ProjectOptions projectOptions = (ProjectOptions) xStream.fromXML(fileReader);

        for(GeneratorOptions generatorOptions:projectOptions.getGeneratorOptions().values()) {
            generatorOptions.setTemplateDir(path + File.separator + generatorOptions.getTemplateDir());
        }

        return projectOptions;
    }
}
