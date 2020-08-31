package ${appPackage}.generated.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<#list Properties as property>
import ${property.type.typePackage}.${property.type.name} 
</#list>

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${entityName}Dto {

	<#list Properties as property>
   private ${property.type}  ${property.name} 
    </#list>
   
}
