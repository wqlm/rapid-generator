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
<@excludeDtoIgnoreField column>
<@columnTypeFlag column/>
<#if oneFieldIndexFlag>
<#assign oneFieldIndex = column?counter>
<#assign oneFieldIndexFlag = false>
</#if>
</@excludeDtoIgnoreField>
</#list>
<#if bigDecimalFlag>
import java.math.BigDecimal;
</#if>

/**
 * ${tableRemarks}DTO
 */
public class ${className}DTO {

<#list table.columns as column>
<@excludeDtoIgnoreField column>
    /**
     * ${column.remarks}
     */
    private ${column.simpleJavaType} ${column.columnNameLower};

</@excludeDtoIgnoreField>
</#list>

<#list table.columns as column>
<@excludeDtoIgnoreField column>
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

</@excludeDtoIgnoreField>
</#list>

    @Override
    public String toString() {
        return "${className}DTO{" +
            <#list table.columns as column>
            <@excludeDtoIgnoreField column>
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
            </@excludeDtoIgnoreField>
            </#list>
                '}';
    }

}