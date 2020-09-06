package ${appPackage}.manual.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${appPackage}.generated.services.Base${entityName}Controller;

@RestController
@RequestMapping("/${entityName?lower_case}s")
public class ${entityName}Controller extends Base${entityName}Controller{

}