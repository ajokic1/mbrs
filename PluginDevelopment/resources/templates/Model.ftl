package ${appPackage}.generated.models;

import lombok.Data; 
import javax.persistence.*;

<#list Properties as property>
import ${property.type.typePackage}.${property.type.name} 
</#list>


@Entity
@Table(name = ${schema})
@Data

public class ${entityName} {

	@Id
	@GeneratedValue(strategy = GenerationType.${property.generator})
	<#list Properties as property>
    private ${property.type}  ${property.name} 
    </#list>
    
    <#list Properties as property>
    @Column(name = ${property.name})
	<#if (property.length)??>length = ${property.length}, </#if>
	nullable = <#if property.lower == 0>false, <#else>true</#if>,
	unique = <#if property.unique>true<#else>false</#if>
	
	<#if property.generator == "AUTO" || peristantProperty.generator == "IDENTITY" >
	@GeneratedValue(strategy = GenerationType.${property.generator})
	<#elseif peristantProperty.generator == "SEQUENCE">
	//@GeneratedValue(strategy = GenerationType.${property.generator}, generator = "${name?lower_case}_generator")
	//@SequenceGenerator(name = "${name?lower_case}_generator", sequenceName = "${name?lower_case}_seq")		
	</#if>
	<#else>
	@ElementCollection
	${property.visibility} Set<${property.type.name}> ${property.name} = new HashSet<${property.type.name}>();
	
    <#if property.upper == -1 && property.oppositeEnd.upper == -1>@ManyToMany<#elseif property.upper == -1 && property.oppositeEnd.upper == 1>@OneToMany<#elseif property.upper == 1 && property.oppositeEnd.upper == -1>@ManyToOne<#else>@OneToOne</#if><#rt>
	<#lt><#if (property.fetch)?? || (property.cascade)?? || (property.mappedBy)?? || (property.optional)?? || (property.orphanRemoval)??>(<#rt>
		<#if (property.cascade)??>
			<#lt>cascade = CascadeType.${property.cascade}<#rt>
		</#if>
		<#if (property.fetch)??>
			<#lt><#if (property.cascade)??>, </#if>fetch = FetchType.${property.fetch}<#rt>
		</#if>
		<#if (property.mappedBy)??>
			<#lt><#if (property.cascade)?? || (property.fetch)??>, </#if>mappedBy = "${property.mappedBy}"<#rt>
		</#if>
		<#if (property.optional)??>
			<#lt><#if (property.cascade)?? || (property.fetch)?? || (property.mappedBy)??>, </#if>optional = ${property.optional?c}<#rt>
		</#if>
	
    </#list>
}
