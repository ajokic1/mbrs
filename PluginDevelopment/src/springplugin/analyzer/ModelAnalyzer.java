package springplugin.analyzer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import springplugin.configuration.ApplicationConfiguration;
import springplugin.generator.fmmodel.*;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import springplugin.generator.fmmodel.enums.AnnotationType;
import springplugin.generator.fmmodel.enums.CascadeType;
import springplugin.generator.fmmodel.enums.FetchType;
import springplugin.generator.fmmodel.enums.GenerationType;


/** Model Analyzer takes necessary metadata from the MagicDraw model and puts it in 
 * the intermediate data structure (@see myplugin.generator.fmmodel.FMModel) optimized
 * for code generation using freemarker. Model Analyzer now takes metadata only for ejb code 
 * generation

 * @ToDo: Enhance (or completely rewrite) myplugin.generator.fmmodel classes and  
 * Model Analyzer methods in order to support GUI generation. */ 


public class ModelAnalyzer {
	private final Package root;
	private final String filePackage;

	private final String ENTITY_CLASS_NAME = "Entity";
	private final String PERSISTENT_PROPERTY = "PersistentProperty";
	private final String GENERATED_VALUE_PROPERTY = "GeneratedValue";
	private final String JOIN_PROPERTY = "JoinProperty";
	private final String OWNING_PROPERTY = "OwningProperty";
	private final String INVERSE_PROPERTY = "InverseProperty";

	private static Map<String, FMJoinProperty> joinPropertyMap = new HashMap<>();

	
	public ModelAnalyzer(Package root, String filePackage) {
		super();
		this.root = root;
		this.filePackage = filePackage;
	}
	
	public void prepareModel() throws AnalyzeException {
		FMModel.getInstance().getClasses().clear();
		FMModel.getInstance().getEnumerations().clear();
		processPackage(root, filePackage);
	}
	
	private void processPackage(Package pack, String packageOwner) throws AnalyzeException {
		if (pack.getName() == null)
			throw new AnalyzeException("Packages must have names!");
		
		if (pack.hasOwnedElement()) {
			for (Element ownedElement : pack.getOwnedElement()) {
				processElement(ownedElement, pack, packageOwner);
			}
		}

		//setJoinPropertyDefaults();
	}

	private void processElement(Element element, Package pack, String packageOwner) throws AnalyzeException {
		if (element instanceof Class) {
			Class cl = (Class) element;
			processEntity(cl, getPackageName(pack, packageOwner));
		}

		if (element instanceof Enumeration) {
			Enumeration enumeration = (Enumeration) element;
			processEnumeration(enumeration, getPackageName(pack, packageOwner));
		}
	}

	private void processEntity(Class mdClass, String packageName) throws AnalyzeException {
		if(mdClass.getName() == null)
			throw new AnalyzeException("Classes must have names");

		FMEntity fmEntity = new FMEntity(mdClass.getName(), packageName);
		Stereotype appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(mdClass, ENTITY_CLASS_NAME);
		if (appliedStereotype != null) {
			processEntityAttributes(appliedStereotype, mdClass, fmEntity);
		}
		processEntityProperties(mdClass, fmEntity);
		System.out.println("Processed entity " + fmEntity.getName() + " with " + fmEntity.getProperties().size() +  " properties");
		FMModel.getInstance().getClasses().add(fmEntity);
	}

	private void processEntityAttributes(Stereotype appliedStereotype, Class mdClass, FMEntity fmEntity) {
		for (Property entityAttribute : appliedStereotype.getOwnedAttribute()) {
			List<?> values = StereotypesHelper.getStereotypePropertyValue(mdClass, appliedStereotype, entityAttribute.getName());
			if(values.isEmpty())
				continue;
			if(entityAttribute.getName().equals("schema")){
				if(values.get(0) instanceof String) {
					String schema = (String)values.get(0);
					fmEntity.setSchema(schema);
				}
			}
			if(entityAttribute.getName().equals("name")){
				if(values.get(0) instanceof String) {
					String name = (String)values.get(0);
					fmEntity.setName(name);
				}
			}
		}
	}

	private void processEntityProperties(Class mdClass, FMEntity fmEntity) throws AnalyzeException {
		System.out.println("Processing properties for " + fmEntity.getName() + "...");
		Iterator<Property> iterator = ModelHelper.attributes(mdClass);
		while (iterator.hasNext()) {
			processEntityProperty(iterator.next(), fmEntity);
		}
	}

	private void processEntityProperty(Property property, FMEntity fmEntity) throws AnalyzeException {
		if(property.getName() == null)
			throw new AnalyzeException("Properties must have names.");

		FMProperty fmProperty = new FMProperty();
		fmProperty.setName(property.getName());
		fmProperty.setLower(property.getLower());
		fmProperty.setUpper(property.getUpper());
		fmProperty.setVisibility(property.getVisibility().toString());
		fmProperty.setType(getType(property));
		fmProperty.setParentEntity(fmEntity);

		if(StereotypesHelper.hasStereotypeOrDerived(property, PERSISTENT_PROPERTY)
		|| StereotypesHelper.hasStereotypeOrDerived(property, GENERATED_VALUE_PROPERTY))
			processPersistentProperty(property, fmEntity, fmProperty);
		else if(StereotypesHelper.hasStereotypeOrDerived(property, OWNING_PROPERTY)
		|| StereotypesHelper.hasStereotypeOrDerived(property, INVERSE_PROPERTY))
			processJoinProperty(property, fmEntity, fmProperty);
	}

	private FMType getType(Property property) {
		String typeName = property.getType().getName();
		String typePackage = "";
		ApplicationConfiguration configuration = ApplicationConfiguration.getConfiguration();

		// U entitetu tip moze da bude ili primitivni, ili importovani (npr java.util.Date) ili
		// neki od ostalih entiteta/enumeracija
		if(configuration.getJavaTypes().contains(typeName));
		else if(configuration.getImportedTypes().containsKey(typeName)) {
			typePackage = configuration.getImportedTypes().get(typeName);
		} else {
			typePackage = configuration.getGeneratedEntitiesPackage();
		}
		return new FMType(typeName, typePackage);
	}

	private void processPersistentProperty(Property property, FMEntity fmEntity, FMProperty fmProperty) {
		Stereotype appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(property, PERSISTENT_PROPERTY);
		if(appliedStereotype==null)
			appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(property, GENERATED_VALUE_PROPERTY);
		if(appliedStereotype==null)
			return;
		FMPersistentProperty fmPersistentProperty = new FMPersistentProperty(fmProperty);
		for(Property propertyAttribute: appliedStereotype.getOwnedAttribute()) {
			List<?> values = StereotypesHelper
					.getStereotypePropertyValue(property, appliedStereotype, propertyAttribute.getName());
			if(values.size() == 0) continue;
			if(propertyAttribute.getName().equals("length") && values.get(0) instanceof Integer) {
				fmPersistentProperty.setLength((Integer) values.get(0));
			} else if(propertyAttribute.getName().equals("name") && values.get(0) instanceof String) {
				fmPersistentProperty.setName((String) values.get(0));
			}
			else if(propertyAttribute.getName().equals("isQueryable") && values.get(0) instanceof Boolean) {
				fmPersistentProperty.setQueryable((Boolean) values.get(0));
			}
		}

		if(StereotypesHelper.hasStereotypeOrDerived(property, GENERATED_VALUE_PROPERTY)) {
			processGeneratedValueProperty(property, fmEntity, fmPersistentProperty);
		} else{
			fmEntity.addProperty(fmPersistentProperty);
			if(fmPersistentProperty.isQueryable())
				fmEntity.addQueryableProperty(fmPersistentProperty);
		}
	}

	private void processGeneratedValueProperty(Property property, FMEntity fmEntity, FMPersistentProperty fmPersistentProperty) {
		Stereotype appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(property, GENERATED_VALUE_PROPERTY);
		if(appliedStereotype==null) return;
		FMGeneratedValueProperty fmGeneratedValueProperty = new FMGeneratedValueProperty(fmPersistentProperty);
		for(Property propertyAttribute: appliedStereotype.getOwnedAttribute()) {
			List<?> values = StereotypesHelper
					.getStereotypePropertyValue(property, appliedStereotype, propertyAttribute.getName());
			if(values.size() == 0) continue;
			if(propertyAttribute.getName().equals("strategy") && values.get(0) instanceof EnumerationLiteral) {
				EnumerationLiteral enumerationLiteral = (EnumerationLiteral) values.get(0);
				GenerationType generationType = GenerationType.valueOf(enumerationLiteral.getName());
				fmGeneratedValueProperty.setStrategy(generationType);
			}
		}
		fmEntity.addProperty(fmGeneratedValueProperty);
	}

	private void processJoinProperty(Property property, FMEntity fmEntity, FMProperty fmProperty) {
		Stereotype appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(property, JOIN_PROPERTY);
		if(appliedStereotype==null)
			appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(property, OWNING_PROPERTY);
		if(appliedStereotype==null)
			appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(property, INVERSE_PROPERTY);
		if(appliedStereotype==null)
			return;
		FMJoinProperty fmJoinProperty = new FMJoinProperty(fmProperty);
		for(Property propertyAttribute: appliedStereotype.getOwnedAttribute()) {
			List<?> values = StereotypesHelper
					.getStereotypePropertyValue(property, appliedStereotype, propertyAttribute.getName());
			if(values.size() == 0) continue;
			if(propertyAttribute.getName().equals("name") && values.get(0) instanceof String) {
				fmJoinProperty.setName((String) values.get(0));
			} else if(propertyAttribute.getName().equals("optional") && values.get(0) instanceof Boolean) {
				fmJoinProperty.setOptional((Boolean) values.get(0));
			} else if(propertyAttribute.getName().equals("fetch") && values.get(0) instanceof EnumerationLiteral) {
				EnumerationLiteral enumerationLiteral = (EnumerationLiteral) values.get(0);
				FetchType fetchType = FetchType.valueOf(enumerationLiteral.getName());
				fmJoinProperty.setFetch(fetchType);
			} else if(propertyAttribute.getName().equals("annotation") && values.get(0) instanceof EnumerationLiteral) {
				EnumerationLiteral enumerationLiteral = (EnumerationLiteral) values.get(0);
				AnnotationType annotationType = AnnotationType.valueOf(enumerationLiteral.getName());
				fmJoinProperty.setAnnotation(annotationType);
			}
		}

		fmJoinProperty.setOppositeEnd(createOppositeInstance(property));
		fmJoinProperty = setJoinPropertyAnnotations(fmJoinProperty);
//		joinPropertyMap.put(fmJoinProperty.getName(), fmJoinProperty);
//		FMJoinProperty opposite = joinPropertyMap.get(property.getOpposite().getName());
//		if(opposite != null) {
//			opposite.setOppositeEnd(fmJoinProperty);
//			fmJoinProperty.setOppositeEnd(opposite);
//
//			System.out.println("LINK: " + fmJoinProperty.getName() + "----" + opposite.getName());
//		}

		if(StereotypesHelper.hasStereotypeOrDerived(property, OWNING_PROPERTY)) {
			processOwningProperty(property, fmEntity, fmJoinProperty);
		} else if(StereotypesHelper.hasStereotypeOrDerived(property, INVERSE_PROPERTY)) {
			processInverseProperty(property, fmEntity, fmJoinProperty);
		} else{
			fmEntity.addProperty(fmJoinProperty);
		}
	}

	private void processOwningProperty(Property property, FMEntity fmEntity, FMJoinProperty fmJoinProperty) {
		Stereotype appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(property, OWNING_PROPERTY);
		if(appliedStereotype==null) return;
		FMOwningProperty fmOwningProperty = new FMOwningProperty(fmJoinProperty);
		setOwningPropertyDefaults(fmOwningProperty);
		for(Property propertyAttribute: appliedStereotype.getOwnedAttribute()) {
			List<?> values = StereotypesHelper
					.getStereotypePropertyValue(property, appliedStereotype, propertyAttribute.getName());
			if(values.size() == 0) continue;
			if(propertyAttribute.getName().equals("referencedColumnName") && values.get(0) instanceof String) {
				fmOwningProperty.setReferencedColumnName((String) values.get(0));
			} else if(propertyAttribute.getName().equals("joinColumnName") && values.get(0) instanceof String) {
				fmOwningProperty.setJoinColumnName((String) values.get(0));
			} else if(propertyAttribute.getName().equals("inverseJoinColumnName") && values.get(0) instanceof String) {
				fmOwningProperty.setInverseJoinColumnName((String) values.get(0));
			}
		}
		fmEntity.addProperty(fmOwningProperty);
	}

	private void processInverseProperty(Property property, FMEntity fmEntity, FMJoinProperty fmJoinProperty) {
		Stereotype appliedStereotype = StereotypesHelper.getAppliedStereotypeByString(property, INVERSE_PROPERTY);
		if(appliedStereotype==null) return;
		FMInverseProperty fmInverseProperty = new FMInverseProperty(fmJoinProperty);
		setInversePropertyDefaults(fmInverseProperty);
		for(Property propertyAttribute: appliedStereotype.getOwnedAttribute()) {
			List<?> values = StereotypesHelper
					.getStereotypePropertyValue(property, appliedStereotype, propertyAttribute.getName());
			if(values.size() == 0) continue;
			if(propertyAttribute.getName().equals("mappedBy") && values.get(0) instanceof String) {
				fmInverseProperty.setMappedBy((String) values.get(0));
			} else if(propertyAttribute.getName().equals("cascade") && values.get(0) instanceof EnumerationLiteral) {
				EnumerationLiteral enumerationLiteral = (EnumerationLiteral) values.get(0);
				CascadeType cascadeType = CascadeType.valueOf(enumerationLiteral.getName());
				fmInverseProperty.setCascade(cascadeType);
			}
		}

		fmEntity.addProperty(fmInverseProperty);
	}

//	private void setJoinPropertyDefaults() {
//		for(String propertyKey: joinPropertyMap.keySet()) {
//			FMJoinProperty property = joinPropertyMap.get(propertyKey);
//			if(property instanceof FMOwningProperty) {
//				setOwningPropertyDefaults(property);
//			}
//			else if(property instanceof FMInverseProperty) {
//				setInversePropertyDefaults(property);
//			}
//		}
//	}

	private FMJoinProperty setJoinPropertyAnnotations(FMJoinProperty property) {
		int upper = property.getUpper();
		if (property.getOppositeEnd() == null){
			System.out.println("PROPERTY " + property.getName() + " has no opposite link.");
			return null;
		}

		int oppositeUpper = property.getOppositeEnd().getUpper();

		if(upper == -1 && oppositeUpper == -1)
			property.setAnnotation(AnnotationType.ManyToMany);
		else if(upper == -1 && oppositeUpper == 1)
			property.setAnnotation(AnnotationType.OneToMany);
		else if(upper == 1 && oppositeUpper == -1)
			property.setAnnotation(AnnotationType.ManyToOne);
		else
			property.setAnnotation(AnnotationType.OneToOne);
		return property;
	}


	private void setOwningPropertyDefaults(FMOwningProperty fmOwningProperty) {
		if(fmOwningProperty.getOppositeEnd() == null){
			System.out.println("No opposite for " + fmOwningProperty.getName());
			return;
		}
		if(fmOwningProperty.getAnnotation() == AnnotationType.ManyToOne || fmOwningProperty.getAnnotation() == AnnotationType.OneToOne){
			fmOwningProperty.setReferencedColumnName("id");
		}
		else if(fmOwningProperty.getAnnotation() == AnnotationType.ManyToMany) {
			// thisentity_id
			fmOwningProperty.setJoinColumnName(fmOwningProperty
					.getParentEntity().getName().toLowerCase() + "_id");
			// relatedentity_id
			fmOwningProperty.setInverseJoinColumnName(fmOwningProperty.getOppositeEnd()
					.getParentEntity().getName().toLowerCase() + "_id");
			fmOwningProperty.setJoinTableName(fmOwningProperty.getParentEntity().getName() + "_"
					+ fmOwningProperty.getOppositeEnd().getParentEntity().getName());
		}
	}

	private void setInversePropertyDefaults(FMInverseProperty fmInverseProperty) {
		if(fmInverseProperty.getOppositeEnd() == null){
			System.out.println("No opposite for " + fmInverseProperty.getName());
			return;
		}
		fmInverseProperty.setMappedBy(fmInverseProperty.getOppositeEnd().getName());
	}

	private void processEnumeration(Enumeration enumeration, String packageName) throws AnalyzeException {
		if(enumeration.getName() == null)
			throw new AnalyzeException("Enumerations must have names");

		FMEnumeration fmEnumeration = new FMEnumeration(enumeration.getName(), packageName);
		for(EnumerationLiteral literal: enumeration.getOwnedLiteral()) {
			fmEnumeration.addValue(literal.getName());
		}
		FMModel.getInstance().getEnumerations().add(fmEnumeration);
	}

	private FMJoinProperty createOppositeInstance(Property property) {
		FMJoinProperty opposite = new FMJoinProperty();
		opposite.setName(property.getOpposite().getName());
		opposite.setLower(property.getOpposite().getLower());
		opposite.setUpper(property.getOpposite().getUpper());
		opposite.setType(getType(property.getOpposite()));
		opposite.setParentEntity(new FMEntity(((Class)property.getOpposite().getOwner()).getName(), ""));
		return opposite;
	}

	private String getPackageName(Package pack, String packageOwner) {
		if (pack != root)
			return packageOwner + pack.getName();
		return packageOwner;
	}

	public Package getRoot() {
		return root;
	}
}
