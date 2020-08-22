package ${appPackage}.generated.services;

import ${appPackage}.generated.converters.${entityName}Converter;
import ${appPackage}.generated.dtos.${entityName}Dto;
import ${appPackage}.generated.models.${entityName};
import ${appPackage}.generated.controllers.${entityName}Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Service
public class ${entityName}Service extends Base${entityName}Service {
}