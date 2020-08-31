package ${appPackage}.generated.controllers;

import lombok.Data;

import javax.persistence.*;

<#list Properties as property>
import ${property.type.typePackage}.${property.type.name} 
</#list>


@Entity
@Table(name = ${schema})
@Data

public class ${entityName} {

    
}
