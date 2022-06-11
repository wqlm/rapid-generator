<#include "/macro.include"/>
<#assign className = table.className>
<#assign tableRemarks = table.remarks>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.domain.dto;

import ${basepackage}.utils.CheckGroup;
import io.swagger.v3.oas.annotations.media.Schema;

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

@Schema(name = "${className}DTO", description = "${tableRemarks}DTO")
public class ${className}DTO {

<#list table.columns as column>
<@excludeDtoIgnoreField column>
    <#if column.pk>
    @Null(message = "无法设置id", groups = {CheckGroup.Add.class})
    @NotNull(message = "id不能为空", groups = {CheckGroup.Update.class})
    </#if>
    @Schema(description = "${column.remarks}")
    private <#if column.simpleJavaType == "LocalDateTime">Long<#else>${column.simpleJavaType}</#if> ${column.columnNameLower};

</@excludeDtoIgnoreField>
</#list>

<#list table.columns as column>
<@excludeDtoIgnoreField column>
    public void set${column.columnName}(<#if column.simpleJavaType == "LocalDateTime">Long<#else>${column.simpleJavaType}</#if> ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    public <#if column.simpleJavaType == "LocalDateTime">Long<#else>${column.simpleJavaType}</#if> get${column.columnName}() {
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