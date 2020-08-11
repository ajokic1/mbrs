package springplugin.generator.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/** ProjectOptions: singleton class that guides code generation process
 * @ToDo: enable save to xml file and load from xml file for this class */

public class ProjectOptions {
	private String path;
	private List<TypeMapping> typeMappings = new ArrayList<TypeMapping>();
	private Map<String, GeneratorOptions> generatorOptions = new HashMap<String, GeneratorOptions>();
	private static ProjectOptions projectOptions = null; 
	
	public List<TypeMapping> getTypeMappings() {
		return typeMappings;
	}

	public void setTypeMappings(List<TypeMapping> typeMappings) {
		this.typeMappings = typeMappings;
	}

	public Map<String, GeneratorOptions> getGeneratorOptions() {
		return generatorOptions;
	}

	public void setGeneratorOptions(Map<String, GeneratorOptions> generatorOptions) {
		this.generatorOptions = generatorOptions;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private ProjectOptions() {
		
	}
	
	public static ProjectOptions getProjectOptions() {
		if (projectOptions ==null) { 
			projectOptions = new ProjectOptions();	
		}	
		return projectOptions;
	}

}
