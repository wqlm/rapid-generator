<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

@TableName("${table.sqlName}")
public class ${className}Entity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	<#list table.columns as column>
	<@excludeEntityCommonField column.sqlName>
	/**
	 * ${column.remarks}
	 */
	@TableField("${column.sqlName}")
	private ${column.simpleJavaType} ${column.columnNameLower};

	</@excludeEntityCommonField>
	</#list>

	<@generateJavaColumns/>
}


<#macro generateJavaColumns>
<#list table.columns as column>
<@excludeEntityCommonField column.sqlName>
	public void set${column.columnName}(${column.simpleJavaType} value) {
		this.${column.columnNameLower} = value;
	}

	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

</@excludeEntityCommonField>
</#list>
</#macro>
