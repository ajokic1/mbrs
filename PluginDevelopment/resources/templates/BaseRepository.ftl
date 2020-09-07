package ${appPackage}.generated.repositories;

import ${appPackage}.generated.entities.${entityName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Base${entityName}Repository extends JpaRepository<${entityName}, Long> {
    <#list queryableProperties as property>
    public List<${entityName}> findBy${property.name?capitalize}(int ${property.name});
    </#list>
}


