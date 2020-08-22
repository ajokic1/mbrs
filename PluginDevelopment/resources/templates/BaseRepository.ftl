package ${appPackage}.generated.repositories;

import ${appPackage}.generated.controllers.${entityName}Controler;
import ${appPackage}.generated.converters.${entityName}Converter;
import ${appPackage}.generated.dtos.${entityName}Dto;
import ${appPackage}.generated.models.${entityName};
import ${appPackage}.generated.services.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

 <#list queryableProperties as property>
public interface Base${entityName}Repository extends JpaRepository<${entityName}, Long> {
    public List<${entityName}> findBy${property.name?capitalize}(int ${property.name});
}
</#list>

