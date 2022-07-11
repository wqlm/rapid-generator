<#include "/macro.include"/>
<#assign className = table.className>
<#assign tableRemarks = table.remarks>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.domain.dto;

import ${basepackage}.utils.CheckGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
<#list table.columns as column>
<@excludeUpdateDtoIgnoreField column>
<@columnTypeFlag column/>
<#if oneFieldIndexFlag>
<#assign oneFieldIndex = column?counter>
<#assign oneFieldIndexFlag = false>
</#if>
</@excludeUpdateDtoIgnoreField>
</#list>
<#if bigDecimalFlag>
import java.math.BigDecimal;
</#if>

/**
 * ${tableRemarks}UpdateDTO
 */
public class ${className}UpdateDTO {

<#list table.columns as column>
<@excludeUpdateDtoIgnoreField column>
    /**
     * ${column.remarks}
     */
    <#if column.pk>
    @NotNull(message = "id不能为空")
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};

</@excludeUpdateDtoIgnoreField>
</#list>

<#list table.columns as column>
<@excludeUpdateDtoIgnoreField column>
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

</@excludeUpdateDtoIgnoreField>
</#list>

    @Override
    public String toString() {
        return "${className}UpdateDTO{" +
            <#list table.columns as column>
            <@excludeUpdateDtoIgnoreField column>
            <#if column?counter == oneFieldIndex>
                <#if column.sqlTypeName == "VARCHAR">
                "${column.columnNameLower}='" + ${column.columnNameLower} + '\'' +
                <#else>
                "${column.columnNameLower}=" + ${column.columnNameLower} +
                </#if>
            <#else>
                <#if column.sqlTypeName == "VARCHAR">
                ", ${column.columnNameLower}='" + ${column.columnNameLower} + '\'' +
                <#else>
                ", ${column.columnNameLower}=" + ${column.columnNameLower} +
                </#if>
            </#if>
            </@excludeUpdateDtoIgnoreField>
            </#list>
                '}';
    }

}