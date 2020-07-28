<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#assign tableRemarks = table.remarks>
package ${basepackage}.mapperstruct;

import ${basepackage}.domain.entity.${className}Entity;
import ${basepackage}.domain.vo.request.${className}ReqVO;
import ${basepackage}.domain.vo.response.${className}VO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ${className}Mapper extends BaseDataMapper<${className}ReqVO, ${className}VO, ${className}Entity> {
    
    ${className}Mapper INSTANCE = Mappers.getMapper(${className}Mapper.class);

}
