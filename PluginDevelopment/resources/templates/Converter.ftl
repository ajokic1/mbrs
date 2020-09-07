package ${appPackage}.generated.converters;

import ${appPackage}.generated.dtos.${entityName}Dto;
import ${appPackage}.generated.entities.${entityName};
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ${entityName}Converter {

 @Autowired
    ModelMapper modelMapper;

    public  ${entityName}Dto convertToDto( ${entityName} ${entityName?lower_case}) {
        return modelMapper.map(${entityName?lower_case}, ${entityName}Dto.class);
    }

    public ${entityName} convertToEntity(${entityName}Dto ${entityName?lower_case}Dto) {
        return modelMapper.map(${entityName?lower_case}Dto, ${entityName}.class);
    }

    public ${entityName} updateEntityFromDto(${entityName} ${entityName?lower_case}, ${entityName}Dto ${entityName?lower_case}Dto) {
        ${entityName?lower_case}Dto.setId(${entityName?lower_case}.getId());
        modelMapper.map(${entityName?lower_case}Dto, ${entityName?lower_case});
        return ${entityName?lower_case};
    }

    public List<${entityName}Dto> convertListToDto(List<${entityName}> ${entityName?lower_case}s){
        List<${entityName}Dto> ${entityName?lower_case}Dtos = new LinkedList<>();
        for(${entityName} ${entityName?lower_case}: ${entityName?lower_case}s) {
            ${entityName?lower_case}Dtos.add(convertToDto(${entityName?lower_case}));
        }
        return ${entityName?lower_case}Dtos;
}

    public List<${entityName}> convertListToEntity(List<${entityName}Dto> ${entityName?lower_case}Dtos){
        List<${entityName}> ${entityName?lower_case}s = new LinkedList<>();
        for(${entityName}Dto ${entityName?lower_case}Dto: ${entityName?lower_case}Dtos) {
            ${entityName?lower_case}s.add(convertToEntity(${entityName?lower_case}Dto));
        }
        return ${entityName?lower_case}s;
    }
}
