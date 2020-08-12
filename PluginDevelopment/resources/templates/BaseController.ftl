package ${appPackage}.generated.controllers;

import ${appPackage}.generated.converters.${className}Converter;
import ${appPackage}.generated.dtos.${className}Dto;
import ${appPackage}.generated.models.${className};
import ${appPackage}.generated.services.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class Base${className}Controller {

    @Autowired
    ${className}Service ${className?lower_case}Service;

    @Autowired
    ${className}Converter ${className?lower_case}Converter;

    @GetMapping("/{id}")
    public ResponseEntity<?> get${className}(@PathVariable long id) {
        ${className} ${className?lower_case} = ${className?lower_case}Service.get${className}ById(id);
        if (${className?lower_case} == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(${className?lower_case}Converter.convertToDto(${className?lower_case}));
    }

    @GetMapping
    public ResponseEntity<?> getAll${className}s() {
        return ResponseEntity.ok(${className?lower_case}Converter.convertListToDto(${className?lower_case}Service.getAll${className}s()));
    }

    <#list findByProperty as property>
    @GetMapping("/${property.name}/{${property.name}}")
    public ResponseEntity<?> get${className}sBy${property.name?capitalize}(@PathVariable ${property.type} ${property.name}) {
        return ResponseEntity.ok(${className?lower_case}Converter.convertListToDto(${className?lower_case}Service.get${className}sBy${property.name?capitalize}(${property.name})));
    }
    </#list>

    @PostMapping
    public ResponseEntity<?> create${className}(@RequestBody ${className}Dto ${className?lower_case}Dto){
        return ResponseEntity.ok(${className?lower_case}Converter.convertToDto(
                ${className?lower_case}Service.createOrUpdate${className}(${className?lower_case}Converter.convertToEntity(${className?lower_case}Dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete${className}(@PathVariable long id) {
        try{
            ${className?lower_case}Service.delete${className}(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update${className}(@PathVariable long id, @RequestBody ${className}Dto ${className?lower_case}Dto) {
        ${className} ${className?lower_case} = ${className?lower_case}Service.get${className}ById(id);
        if (${className?lower_case} == null)
            return ResponseEntity.notFound().build();
        ${className?lower_case} = ${className?lower_case}Service.createOrUpdate${className}(${className?lower_case}Converter.updateEntityFromDto(${className?lower_case}, ${className?lower_case}Dto));
        return ResponseEntity.ok(${className?lower_case});
    }
}
