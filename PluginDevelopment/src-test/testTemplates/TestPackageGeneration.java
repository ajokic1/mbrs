package testTemplates;

import java.util.List;

//import springplugin.generator.EJBGenerator;
import springplugin.generator.fmmodel.FMEntity;
import springplugin.generator.fmmodel.FMModel;
import springplugin.generator.fmmodel.FMProperty;
import springplugin.generator.options.GeneratorOptions;
import springplugin.generator.options.ProjectOptions;

/** TestPackageGeneration: Class for package generation testing
 * @ToDo: Create another test class that loads metadata saved by MagicDraw plugin 
 * ( @see myplugin.GenerateAction#exportToXml() ) and activate code generation. 
 * This is the way to perform code generation testing without
 *  need to restart MagicDraw 
 *  */
/*
public class TestPackageGeneration {
	
	public TestPackageGeneration(){
		
	}
	
	private void initModel() {		
		
		List<FMEntity> classes = FMModel.getInstance().getClasses();
		
		classes.clear();
		
		FMEntity cl = new FMEntity("Preduzece", "ejb.orgsema", "public");
		cl.addProperty(new FMProperty("sifraPreduzeca", "String", "private", 1, 1));
		cl.addProperty(new FMProperty("nazivPreduzeca", "String", "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMEntity("Materijal", "ejb.magacin", "public");
		cl.addProperty(new FMProperty("sifraMaterijala", "String", "private", 1, 1));
		cl.addProperty(new FMProperty("nazivMaterijala", "String", "private", 1, 1));
		cl.addProperty(new FMProperty("slozen", "Boolean", "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMEntity("Odeljenje", "ejb.orgsema", "public");
		cl.addProperty(new FMProperty("sifra", "String", "private", 1, 1));
		cl.addProperty(new FMProperty("naziv", "String", "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMEntity("Osoba", "ejb", "public");
		cl.addProperty(new FMProperty("prezime", "String", "private", 1, 1));		
		cl.addProperty(new FMProperty("ime", "String", "private", 1, 1));
		cl.addProperty(new FMProperty("datumRodjenja", "Date", "private", 0, 1));
		cl.addProperty(new FMProperty("clanoviPorodice", "Osoba", "private", 0, -1));	
		cl.addProperty(new FMProperty("vestina", "String", "private", 1, 3));
		
		classes.add(cl);
		
		cl = new FMEntity("Kartica", "ejb.magacin.kartica", "public");
		cl.addProperty(new FMProperty("sifraKartice", "String", "private", 1, 1));
		cl.addProperty(new FMProperty("nazivKartice", "String", "private", 1, 1));
		
		classes.add(cl);		
	}
	
	public void testGenerator() {
		initModel();		
		GeneratorOptions go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EJBGenerator");	
		EJBGenerator g = new EJBGenerator(go);
		g.generate();
	}
	
	public static void main(String[] args) {
		TestPackageGeneration tg = new TestPackageGeneration();
		// @Todo: load project options from xml file
		
		//for test purpose only:
		GeneratorOptions ejbOptions = new GeneratorOptions("c:/temp", "ejbclass", "./resources/templates/", "{0}.java", true, "ejb"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator", ejbOptions);
				
		tg.testGenerator();
	}
	
	
	
	
}
*/