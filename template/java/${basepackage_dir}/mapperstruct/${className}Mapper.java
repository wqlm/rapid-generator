<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#assign tableRemarks = table.remarks>
package ${basepackage}.mapperstruct;

import ${basepackage}.domain.db.entity.${className}Entity;
import ${basepackage}.domain.dto.${className}DTO;
import ${basepackage}.domain.vo.${className}VO;

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
<#if localDateTimeFlag>
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
</#if>


@Mapper(
<#if localDateTimeFlag>
        imports = {Instant.class,LocalDateTime.class,ZoneId.class}
</#if>
)
public interface ${className}Mapper{
    
    ${className}Mapper INSTANCE = Mappers.getMapper(${className}Mapper.class);

    /**
     * 将 {@link ${className}DTO} 转换为 {@link ${className}Entity}
     *
     * @param dto ${classNameLower}DTO
     * @return {@link ${className}Entity}
     */
    @Mappings({
        <#list table.columns as column>
        <@excludeDtoIgnoreField column>
        <#if column.simpleJavaType == "LocalDateTime">
        @Mapping(expression = "java(LocalDateTime.ofInstant(Instant.ofEpochMilli(dto.get${column.columnName}()), ZoneId.systemDefault()))", target = "${column.columnNameLower}"),
        </#if>
        </@excludeDtoIgnoreField>
        </#list>
    })
    ${className}Entity ${className}Dto2${className}Entity(${className}DTO dto);



    /**
     * 将 {@link ${className}Entity} 转换为 {@link ${className}VO}
     *
     * @param entity ${classNameLower}Entity
     * @return {@link ${className}VO}
     */
    @Mappings({
        <#list table.columns as column>
        <@excludeDtoIgnoreField column>
        <#if column.simpleJavaType == "LocalDateTime">
        @Mapping(expression = "java(entity.get${column.columnName}().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())", target = "${column.columnNameLower}"),
        </#if>
        </@excludeDtoIgnoreField>
        </#list>
    })
    ${className}VO ${className}Entity2${className}Vo(${className}Entity entity);




    /**
     * 将 {@link List<${className}Entity>} 转换为 {@link List<${className}VO>}
     *
     * @param entityList ${classNameLower}EntityList
     * @return {@link List<${className}VO>}
     */
    List<${className}VO> ${className}EntityList2${className}VoList(List<${className}Entity> entityList);
}
