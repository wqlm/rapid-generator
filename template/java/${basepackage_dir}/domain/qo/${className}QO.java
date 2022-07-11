<#include "/macro.include"/>
<#assign className = table.className>
<#assign tableRemarks = table.remarks>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.domain.qo;
import ${basepackage}.domain.dto.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
<#list table.columns as column>
<@excludeQoIgnoreField column>
<@columnTypeFlag column/>
<#if oneFieldIndexFlag>
<#assign oneFieldIndex = column?counter>
<#assign oneFieldIndexFlag = false>
</#if>
</@excludeQoIgnoreField>
</#list>
<#if bigDecimalFlag>
import java.math.BigDecimal;
</#if>

/**
 * ${tableRemarks}QO
 */
public class ${className}QO extends PageDTO {

<#list table.columns as column>
<@excludeQoIgnoreField column>
    /**
     * ${column.remarks}
     */
    private ${column.simpleJavaType} ${column.columnNameLower};

</@excludeQoIgnoreField>
</#list>

<#list table.columns as column>
<@excludeQoIgnoreField column>
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

</@excludeQoIgnoreField>
</#list>

    @Override
    public String toString() {
        return "${className}QO{" +
            <#list table.columns as column>
            <@excludeQoIgnoreField column>
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
            </@excludeQoIgnoreField>
            </#list>
                '}';
    }

}