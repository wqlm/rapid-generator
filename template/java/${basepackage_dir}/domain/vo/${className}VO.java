<#include "/macro.include"/>
<#assign className = table.className>
<#assign tableRemarks = table.remarks>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.domain.vo;

<#list table.columns as column>
<@excludeVoIgnoreField column>
<@columnTypeFlag column/>
<#if oneFieldIndexFlag>
<#assign oneFieldIndex = column?counter>
<#assign oneFieldIndexFlag = false>
</#if>
</@excludeVoIgnoreField>
</#list>
<#if bigDecimalFlag>
import java.math.BigDecimal;
</#if>
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;

/**
 * ${tableRemarks}VO
 */
public class ${className}VO {

<#list table.columns as column>
<@excludeVoIgnoreField column>
    /**
     * ${column.remarks}
     */
   <#if column.simpleJavaType == "Long">
    @JsonSerialize(using = ToStringSerializer.class)
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};

</@excludeVoIgnoreField>
</#list>

<#list table.columns as column>
<@excludeVoIgnoreField column>
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

</@excludeVoIgnoreField>
</#list>

    @Override
    public String toString() {
        return "${className}VO{" +
            <#list table.columns as column>
            <@excludeVoIgnoreField column>
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
            </@excludeVoIgnoreField>
            </#list>
                '}';
    }

}