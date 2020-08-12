package springplugin;

import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import springplugin.analyzer.AnalyzeException;
import springplugin.analyzer.ModelAnalyzer;
import springplugin.generator.BasicGenerator;
import springplugin.generator.fmmodel.FMModel;
import springplugin.generator.options.GeneratorOptions;
import springplugin.generator.options.ProjectOptions;

/** Action that activate code generation */
@SuppressWarnings("serial")
class GenerateAction extends MDAction{
	
	
	public GenerateAction(String name) {			
		super("", name, null, null);		
	}

	public void actionPerformed(ActionEvent evt) {
		
		if (Application.getInstance().getProject() == null) return;
		Package root = Application.getInstance().getProject().getModel();
		
		if (root == null) return;
	
		ModelAnalyzer analyzer = new ModelAnalyzer(root, "ejb");	
		
		try {
			analyzer.prepareModel();	
			callGenerators();
			generateSuccess();
			exportToXml();
		} catch (AnalyzeException
				| ClassNotFoundException
				| NoSuchMethodException
				| InvocationTargetException
				| InstantiationException
				| IOException
				| IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void callGenerators() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, InstantiationException, IOException {
		for(GeneratorOptions generatorOptions: ProjectOptions.getProjectOptions().getGeneratorOptions().values()) {
			String fullClassName = BasicGenerator.class.getPackage().getName() + generatorOptions.getGeneratorClassName();
			Class<?> generatorClass = Class.forName(fullClassName);
			Constructor<?> constructor = generatorClass.getConstructor(GeneratorOptions.class);
			BasicGenerator generator = (BasicGenerator) constructor.newInstance(new Object[] {generatorOptions});
			generator.generate();
		}
	}

	private void generateSuccess() {
		JOptionPane.showMessageDialog(null, "Code is successfully generated!");
	}
	
	private void exportToXml() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") == 
			JOptionPane.OK_OPTION)
		{	
			JFileChooser jfc = new JFileChooser();
			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();
			
				XStream xstream = new XStream(new DomDriver());
				BufferedWriter out;		
				try {
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(fileName), "UTF8"));					
					xstream.toXML(FMModel.getInstance().getClasses(), out);
					xstream.toXML(FMModel.getInstance().getEnumerations(), out);
					
				} catch (UnsupportedEncodingException | FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());				
				}
			}
		}	
	}	  

}