<#include "/macro.include"/>
<#assign className = table.className>
<#assign tableRemarks = table.remarks>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.domain.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;


@Schema(name = "${className}ReqVO", description = "${tableRemarks}VO")
public class ${className}ReqVO implements Serializable {

	private static final long serialVersionUID = 1L;

<#list table.columns as column>
<@excludeRequestVoCommonField column.sqlName>
	@Schema(description = "${column.remarks}")
	private ${column.simpleJavaType} ${column.columnNameLower};

</@excludeRequestVoCommonField>
</#list>

<@generateJavaColumns/>
}


<#macro generateJavaColumns>
<#list table.columns as column>
<@excludeRequestVoCommonField column.sqlName>
	public void set${column.columnName}(${column.simpleJavaType} value) {
		this.${column.columnNameLower} = value;
	}

	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

</@excludeRequestVoCommonField>
</#list>
</#macro>
