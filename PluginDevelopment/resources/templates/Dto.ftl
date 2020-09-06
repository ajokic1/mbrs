package ${appPackage}.generated.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<#list properties as property>
<#if property.type.typePackage?has_content>
import ${property.type.typePackage}.${property.type.name};
</#if>
</#list>

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${entityName}Dto {
	<#list properties as property>
    private ${property.type.name} ${property.name}
    </#list>
}
