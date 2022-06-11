<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign tableRemarks = table.remarks>
package ${basepackage}.domain.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

<#list table.columns as column>
<@excludeEntityCommonField column>
<@columnTypeFlag column/>
<#if oneFieldIndexFlag>
<#assign oneFieldIndex = column?counter>
<#assign oneFieldIndexFlag = false>
</#if>
</@excludeEntityCommonField>
</#list>
<@importDepend/>

/**
 * ${tableRemarks}Entity
 */
@TableName("${table.sqlName}")
public class ${className}Entity extends BaseEntity {

<#list table.columns as column>
<@excludeEntityCommonField column>
    /**
     * ${column.remarks}
     */
    @TableField("${column.sqlName}")
    private ${column.simpleJavaType} ${column.columnNameLower};

</@excludeEntityCommonField>
</#list>

<#list table.columns as column>
<@excludeEntityCommonField column>
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

</@excludeEntityCommonField>
</#list>

    @Override
    public String toString() {
        return "${className}Entity{" +
            <#list table.columns as column>
            <@excludeEntityCommonField column>
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
            </@excludeEntityCommonField>
            </#list>
                '}';
    }
}





