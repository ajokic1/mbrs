package ${appPackage}.generated.entities;

import lombok.Data; 
import javax.persistence.*;
import java.util.List;

<#list properties as property>
<#if property.type.typePackage?has_content>
import ${property.type.typePackage}.${property.type.name};
</#if>
</#list>


@Entity
<#if (schema)??>
@Table(name = ${schema})
</#if>
@Data
public class ${entityName} {
	<#list generatedValueProperties as property>
    @Id
    @GeneratedValue(strategy = GenerationType.${property.strategy})
    private ${property.type.name}  ${property.name};

    </#list>
    <#list persistentProperties as property>
    <#lt>   @Column(name = "${property.name}"<#rt>
	<#lt><#if property.length!=0>, length = ${property.length} </#if><#rt>
	<#lt>, nullable = <#if property.lower == 0>false, <#else>true</#if>)
	<#lt>   private ${property.type.name} ${property.name};

	</#list>
    <#list inverseProperties as property>
        <#if (property.annotation)??>
        <#lt>   @${property.annotation}<#rt>
        <#if (property.mappedBy)??>
            <#lt>(mappedBy = "${property.mappedBy}")
        </#if>
        </#if>
        <#if property.annotation == "OneToOne">
        <#lt>   private ${property.type.name} ${property.name};
        <#else>
        <#lt>   private List<${property.type.name}> ${property.name};
        </#if>


    </#list>
	<#list owningProperties as property>
	    <#if (property.annotation)??>
        <#lt>   @${property.annotation}
        <#if property.annotation == "ManyToMany" && (property.joinTableName)??>
            <#lt>   @JoinTable(
            <#lt>       name = "${property.joinTableName}",
            <#lt>       joinColumns = @JoinColumn(name = "${property.joinColumnName}"),
            <#lt>       inverseJoinColumns = @JoinColumn(name = "${property.inverseJoinColumnName}"))
            <#lt>   private List<${property.type.name}> ${property.name};
        </#if>
        <#if (property.annotation == "ManyToOne" || property.annotation == "OneToOne") && (property.referencedColumnName)??>
            <#lt>   @JoinColumn(name = "${property.name}", referencedColumnName = "${property.referencedColumnName}")
            <#lt>   private ${property.type.name} ${property.name};
        </#if>
        </#if>


	</#list>
}
