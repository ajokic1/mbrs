package ${appPackage}.generated.services;

import ${appPackage}.generated.converters.${entityName}Converter;
import ${appPackage}.generated.dtos.${entityName}Dto;
import ${appPackage}.generated.models.${entityName};
import ${appPackage}.generated.controllers.${entityName}Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Service
public abstract class Base${entityName}Service{

    @Autowired
    ${entityName}Repository ${entityName?lower_case}Repository;

    public ${entityName} get${entityName}ById(long id) {
        Optional<${entityName}> ${entityName?lower_case}Optional = ${entityName?lower_case}Repository.findById(id);
        return ${entityName?lower_case}Optional.orElse(null);
    }

    public List<${entityName}> getAll${entityName}s() {
        return ${entityName?lower_case}Repository.findAll();
    }

    public List<${entityName}> get${entityName}sByYear(int year) {
        return ${entityName?lower_case}Repository.findByYear(year);
    }

    public void delete${entityName}(long id) throws IllegalArgumentException {
        ${entityName?lower_case}Repository.deleteById(id);
    }

    public ${entityName} createOrUpdate${entityName}(${entityName} ${entityName?lower_case}) {
        return ${entityName?lower_case}Repository.save(${entityName?lower_case});
    }
}
