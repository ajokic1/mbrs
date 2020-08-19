package ${appPackage}.generated.controllers;

import ${appPackage}.generated.converters.${entityName}Converter;
import ${appPackage}.generated.dtos.${entityName}Dto;
import ${appPackage}.generated.models.${entityName};
import ${appPackage}.generated.services.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class Base${entityName}Controller {

    @Autowired
    ${entityName}Service ${entityName?lower_case}Service;

    @Autowired
    ${entityName}Converter ${entityName?lower_case}Converter;

    @GetMapping("/{id}")
    public ResponseEntity<?> get${entityName}(@PathVariable long id) {
        ${entityName} ${entityName?lower_case} = ${entityName?lower_case}Service.get${entityName}ById(id);
        if (${entityName?lower_case} == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(${entityName?lower_case}Converter.convertToDto(${entityName?lower_case}));
    }

    @GetMapping
    public ResponseEntity<?> getAll${entityName}s() {
        return ResponseEntity.ok(${entityName?lower_case}Converter.convertListToDto(${entityName?lower_case}Service.getAll${entityName}s()));
    }

    <#list queryableProperties as property>
    @GetMapping("/${property.name}/{${property.name}}")
    public ResponseEntity<?> get${entityName}sBy${property.name?capitalize}(@PathVariable ${property.type} ${property.name}) {
        return ResponseEntity.ok(${entityName?lower_case}Converter.convertListToDto(${entityName?lower_case}Service.get${entityName}sBy${property.name?capitalize}(${property.name})));
    }
    </#list>

    @PostMapping
    public ResponseEntity<?> create${entityName}(@RequestBody ${entityName}Dto ${entityName?lower_case}Dto){
        return ResponseEntity.ok(${entityName?lower_case}Converter.convertToDto(
                ${entityName?lower_case}Service.createOrUpdate${entityName}(${entityName?lower_case}Converter.convertToEntity(${entityName?lower_case}Dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete${entityName}(@PathVariable long id) {
        try{
            ${entityName?lower_case}Service.delete${entityName}(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update${entityName}(@PathVariable long id, @RequestBody ${entityName}Dto ${entityName?lower_case}Dto) {
        ${entityName} ${entityName?lower_case} = ${entityName?lower_case}Service.get${entityName}ById(id);
        if (${entityName?lower_case} == null)
            return ResponseEntity.notFound().build();
        ${entityName?lower_case} = ${entityName?lower_case}Service.createOrUpdate${entityName}(${entityName?lower_case}Converter.updateEntityFromDto(${entityName?lower_case}, ${entityName?lower_case}Dto));
        return ResponseEntity.ok(${entityName?lower_case});
    }
}
