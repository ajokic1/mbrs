package ${appPackage}.generated.services;

import ${appPackage}.generated.entities.${entityName};
import ${appPackage}.generated.repositories.Base${entityName}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class Base${entityName}Service{

    @Autowired
    Base${entityName}Repository ${entityName?lower_case}Repository;

    public ${entityName} get${entityName}ById(long id) {
        Optional<${entityName}> ${entityName?lower_case}Optional = ${entityName?lower_case}Repository.findById(id);
        return ${entityName?lower_case}Optional.orElse(null);
    }

    public List<${entityName}> getAll${entityName}s() {
        return ${entityName?lower_case}Repository.findAll();
    }
    <#list queryableProperties as property>
    public List<${entityName}> get${entityName}sBy${property.name?capitalize}(${property.type} ${property.name}) {
        return ${entityName?lower_case}Repository.findBy${property.name?capitalize}(${property.name});
    }
    </#list>

    public void delete${entityName}(long id) throws IllegalArgumentException {
        ${entityName?lower_case}Repository.deleteById(id);
    }

    public ${entityName} createOrUpdate${entityName}(${entityName} ${entityName?lower_case}) {
        return ${entityName?lower_case}Repository.save(${entityName?lower_case});
    }
}
