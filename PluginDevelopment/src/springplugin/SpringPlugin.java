package springplugin;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import com.nomagic.runtime.LogOutputStream;
import springplugin.configuration.ApplicationConfiguration;
import springplugin.configuration.ApplicationConfigurationDefault;
import springplugin.generator.options.GeneratorOptions;
import springplugin.generator.options.ProjectOptions;


import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import springplugin.generator.options.ProjectOptionsLoader;

public class SpringPlugin extends com.nomagic.magicdraw.plugins.Plugin {

	private final String PROJECT_OPTIONS_FILE = "ProjectOptions.xml";
	private String pluginDir = null;
	
	public void init() {
		pluginDir = getDescriptor().getPluginDirectory().getPath();
		createMagicDrawMenu();
		loadProjectOptions();
		loadApplicationConfiguration();
		initSuccess();
	}

	private void createMagicDrawMenu() {
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));
	}

	private void loadProjectOptions() {
		try {
			ProjectOptions projectOptions = ProjectOptionsLoader
					.loadProjectOptionsFromFile(pluginDir, PROJECT_OPTIONS_FILE);
			System.out.println("pluginDir: " + pluginDir);
			ProjectOptions.getProjectOptions().setGeneratorOptions(projectOptions.getGeneratorOptions());
			System.out.println("generatorOptionsLoaded: " + projectOptions.getGeneratorOptions().size());
			ProjectOptions.getProjectOptions().setTypeMappings(projectOptions.getTypeMappings());
			ProjectOptions.getProjectOptions().setPath(projectOptions.getPath());
			ProjectOptions.getProjectOptions().setApplicationConfiguration(projectOptions.getApplicationConfiguration());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	private void loadApplicationConfiguration() {
		//ApplicationConfiguration.setConfiguration(new ApplicationConfigurationDefault());
		ApplicationConfiguration.setConfiguration(ProjectOptions.getProjectOptions().getApplicationConfiguration());
	}

	private void initSuccess() {
		JOptionPane.showMessageDialog( null, "Spring Generator plugin successfully loaded.");
	}

	private NMAction[] getSubmenuActions()
	{
	   return new NMAction[]{
			new GenerateAction("Generate Spring app"),
	   };
	}
	
	public boolean close() {
		return true;
	}
	
	public boolean isSupported() {				
		return true;
	}
}


