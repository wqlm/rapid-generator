
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
package ${basepackage}.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ${basepackage}.domain.vo.response.${className}VO;
import ${basepackage}.domain.entity.${className}Entity;
import java.util.List;


@Mapper
public interface ${className}Dao extends BaseMapper<${className}Entity> {

    IPage<${className}VO> pageFind${className}(Page<${className}Entity> pageQuery, @Param("entity") ${className}Entity entity);

    List<${className}VO>  find${className}ListByVo(@Param("entity") ${className}Entity entity);
}

