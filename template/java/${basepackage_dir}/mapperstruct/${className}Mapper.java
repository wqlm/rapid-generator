<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#assign tableRemarks = table.remarks>
package ${basepackage}.mapperstruct;

import ${basepackage}.domain.db.entity.${className}Entity;
import ${basepackage}.domain.dto.${className}DTO;
import ${basepackage}.domain.vo.${className}VO;
import ${basepackage}.utils.DateTransform;
import ${basepackage}.domain.dto.${className}UpdateDTO;
import ${basepackage}.domain.qo.${className}QO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import java.util.List;
<#list table.columns as column>
<@excludeEntityCommonField column>
<@columnTypeFlag column/>
</@excludeEntityCommonField>
</#list>
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper
public interface ${className}Mapper{
    
    ${className}Mapper INSTANCE = Mappers.getMapper(${className}Mapper.class);

    /**
     * 将 {@link ${className}DTO} 转换为 {@link ${className}Entity}
     *
     * @param dto
     * @return {@link ${className}Entity}
     */
    ${className}Entity dto2Entity(${className}DTO dto);


    /**
     * 将 {@link ${className}UpdateDTO} 转换为 {@link ${className}Entity}
     *
     * @param updateDTO
     * @return {@link ${className}Entity}
     */
    ${className}Entity updateDto2Entity(${className}UpdateDTO updateDTO);


    /**
     * 将 {@link ${className}Entity} 转换为 {@link ${className}VO}
     *
     * @param entity Entity
     * @return {@link ${className}VO}
     */
    ${className}VO entity2Vo(${className}Entity entity);


    /**
     * 将 {@link List<${className}Entity>} 转换为 {@link List<${className}VO>}
     *
     * @param entityList EntityList
     * @return {@link List<${className}VO>}
     */
    List<${className}VO> entityList2VoList(List<${className}Entity> entityList);
}
