package springplugin.generator.fmmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FMEntity extends FMType {

	private String schema;

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	//Class properties
	private final List<FMProperty> FMProperties = new ArrayList<>();

	private final List<FMPersistentProperty> queryableProperties = new ArrayList<>();
	
	public FMEntity(String name, String classPackage) {
		super(name, classPackage);
	}	
	
	public List<FMProperty> getProperties(){
		return FMProperties;
	}
	
	public Iterator<FMProperty> getPropertyIterator(){
		return FMProperties.iterator();
	}
	
	public void addProperty(FMProperty property){
		FMProperties.add(property);		
	}
	
	public int getPropertyCount(){
		return FMProperties.size();
	}
	
	public int getImportedCount(){
		return FMProperties.size();
	}

	public void addQueryableProperty(FMPersistentProperty property) {
		queryableProperties.add(property);
	}

	public List<FMPersistentProperty> getQueryableProperties() {
		return queryableProperties;
	}
}
