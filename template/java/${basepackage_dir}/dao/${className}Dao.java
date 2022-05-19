<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#assign tableRemarks = table.remarks>
package ${basepackage}.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import ${basepackage}.domain.dto.${className}DTO;
import ${basepackage}.domain.vo.${className}VO;
import ${basepackage}.domain.db.entity.${className}Entity;

@Mapper
public interface ${className}Dao extends BaseMapper<${className}Entity> {

    /**
     * 分页查询${tableRemarks}
     *
     * @param pageQuery 分页参数
     * @param dto       查询条件
     * @return {@link IPage<${className}VO>}
     */
    IPage<${className}VO> pageFind${className}(IPage<${className}Entity> pageQuery, @Param("dto")  ${className}DTO dto);

}

