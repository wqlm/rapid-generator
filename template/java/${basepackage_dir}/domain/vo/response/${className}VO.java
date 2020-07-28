<#include "/macro.include"/>
<#assign className = table.className>
<#assign tableRemarks = table.remarks>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.domain.response.vo;

import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * ${tableRemarks}VO
 */
public class ${className}VO implements Serializable {

	private static final long serialVersionUID = 1L;

<#list table.columns as column>
<@excludeResponseVoCommonField column.sqlName>
	/**
	 * ${column.remarks}
	 */
	private ${column.simpleJavaType} ${column.columnNameLower};

</@excludeResponseVoCommonField>
</#list>

<@generateJavaColumns/>
}


<#macro generateJavaColumns>
<#list table.columns as column>
<@excludeResponseVoCommonField column.sqlName>
	public void set${column.columnName}(${column.simpleJavaType} value) {
		this.${column.columnNameLower} = value;
	}

	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

</@excludeResponseVoCommonField>
</#list>
</#macro>
