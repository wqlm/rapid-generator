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

/**
 * ${tableRemarks}VO
 */
public class ${className}VO {

<#list table.columns as column>
<@excludeVoIgnoreField column>
    /**
     * ${column.remarks}
     */
    private <#if column.simpleJavaType == "LocalDateTime">Long<#else>${column.simpleJavaType}</#if> ${column.columnNameLower};

</@excludeVoIgnoreField>
</#list>

<#list table.columns as column>
<@excludeVoIgnoreField column>
    public void set${column.columnName}(<#if column.simpleJavaType == "LocalDateTime">Long<#else>${column.simpleJavaType}</#if> value) {
        this.${column.columnNameLower} = value;
    }

    public <#if column.simpleJavaType == "LocalDateTime">Long<#else>${column.simpleJavaType}</#if> get${column.columnName}() {
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