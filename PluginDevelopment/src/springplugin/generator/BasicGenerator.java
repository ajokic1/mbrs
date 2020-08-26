package springplugin.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.options.GeneratorOptions;
import springplugin.generator.options.ProjectOptions;

import javax.swing.*;

/**
 * Abstract generator that creates necessary environment for code generation 
 * (creating directory for code generation, fetching template, creating file with given name 
 * for code generation etc). It should be ancestor for all generators in this project. 
*/

public abstract class BasicGenerator {

	private GeneratorOptions generatorOptions;
	private String filePackage;
	private Configuration cfg;
	private Template template;	
	
	public BasicGenerator(GeneratorOptions generatorOptions) {
		this.generatorOptions = generatorOptions;
		this.filePackage = generatorOptions.getFilePackage();
	}

	public void generate() throws IOException {		
		checkIfRequiredOptionsNull();
		final String tName = generatorOptions.getTemplateName() + ".ftl";
		try {
			configureFreemarker(tName);
			createOutputDir();
		} catch (IOException e) {
			throw new IOException("Can't find template " + tName + ".", e);
		}

	}

	private void checkIfRequiredOptionsNull() throws IOException {
		if (generatorOptions.getOutputPath() == null) {
			throw new IOException("Output path is not defined!");
		}
		if (generatorOptions.getTemplateName() == null) {
			throw new IOException("Template name is not defined!");
		}
		if (generatorOptions.getOutputFileName() == null) {
			throw new IOException("Output file name is not defined!");
		}
		if (filePackage == null) {
			throw new IOException("Package name for code generation is not defined!");
		}
	}

	private void configureFreemarker(String tName) throws IOException {
		cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		cfg.setDirectoryForTemplateLoading(new File(generatorOptions.getTemplateDir()));
		template = cfg.getTemplate(tName);
		DefaultObjectWrapperBuilder builder =
				new DefaultObjectWrapperBuilder(cfg.getIncompatibleImprovements());
		cfg.setObjectWrapper(builder.build());

	}

	private void createOutputDir() throws IOException {
		System.out.println("OutputPath: " + getOutputPath());
		File op = new File(getOutputPath());
		if (!op.exists() && !op.mkdirs()) {
			throw new IOException(
					"An error occurred during folder creation " + getOutputPath());
		}
	}

	public Writer getWriter(String fileNamePart, String packageName) throws IOException {
		if (!packageName.equals(filePackage)) {
			filePackage = packageToPath(packageName);
		}
		String fullPath = getOutputFileFullPath(fileNamePart);
		File outputFile = createOutputFile(fullPath);

		if(outputFile == null)
			return null;

		return new OutputStreamWriter(new FileOutputStream(outputFile));

	}

	private String getOutputFileFullPath(String fileNamePart) {
		return getOutputPath()
				+ File.separator
				+ generatorOptions.getOutputFileName().replace("{entity}", fileNamePart);
	}

	private String getOutputPath() {
		return generatorOptions.getOutputPath()
				.replace("{path}", ProjectOptions.getProjectOptions().getPath())
				.replace("{app_name}", packageToPath(ApplicationConfiguration.getConfiguration().getApplicationName()))
				.replace("{app_package}", packageToPath(ApplicationConfiguration.getConfiguration().getApplicationPackage() + "." + generatorOptions.getFilePackage()));
	}

	private File createOutputFile(String fullPath) throws IOException {
		File outputFile = new File(fullPath);
		//JOptionPane.showMessageDialog(null, "FILE:" + outputFile.getAbsolutePath());
		if (!outputFile.getParentFile().exists()) {
			if (!outputFile.getParentFile().mkdirs()) {
				throw new IOException("An error occurred during output folder creation "
						+ generatorOptions.getOutputPath());
			}
		}
		System.out.println(outputFile.getPath());
		System.out.println(outputFile.getName());
		if (!generatorOptions.getOverwrite() && outputFile.exists()) {
			return null;
		}
		return outputFile;
	}

	protected String packageToPath(String pack) {
		return pack.replace(".", File.separator);
	}

	public Writer getWriter() throws IOException {
		return getWriter("", filePackage);

	}

	public Configuration getCfg() {
		return cfg;
	}


	public Template getTemplate() {
		return template;
	}


}
