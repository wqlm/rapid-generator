<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#assign tableRemarks = table.remarks>
package ${basepackage}.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}.constants.OtherConstants;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.domain.db.entity.${className}Entity;
import ${basepackage}.domain.vo.PageResultVO;
import ${basepackage}.domain.dto.${className}DTO;
import ${basepackage}.domain.dto.${className}UpdateDTO;
import ${basepackage}.domain.dto.PageDTO;
import ${basepackage}.domain.qo.${className}QO;
import ${basepackage}.domain.vo.${className}VO;
import ${basepackage}.constants.enums.error.BasicErrorEnum;
import ${basepackage}.exception.ApplicationException;
import ${basepackage}.mapperstruct.${className}Mapper;
import ${basepackage}.utils.TenantIdUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ${className}Service extends ServiceImpl<BaseMapper<${className}Entity>, ${className}Entity> {

    private static final Logger LOG = LoggerFactory.getLogger(${className}Service.class);

    @Autowired
    private ${className}Dao dao;

    /**
     * 添加${tableRemarks}
     *
     * @param dto dto
     * @return {@link Long}
     */
    public Long add${className}(${className}DTO dto) {
        // TODO 进行业务校验
        ${className}Entity entity = ${className}Mapper.INSTANCE.dto2Entity(dto);
        entity.setTenantId(TenantIdUtil.getTenantId());
        entity.setCreateUser(OtherConstants.SYSUSER_NAME);
        boolean result = this.save(entity);
        if (!result) {
            throw new ApplicationException(BasicErrorEnum.MYSQL_OPERATION_ERROR.getCode(),
                    BasicErrorEnum.MYSQL_OPERATION_ERROR.getMessage());
        }
        return entity.getId();

    }

    /**
     * 根据id修改${tableRemarks}
     *
     * @param updateDTO
     */
    public void update${className}ById(${className}UpdateDTO updateDTO) {
        ${className}Entity entityDb = get${className}ById(updateDTO.getId());
        if (null == entityDb) {
            throw new ApplicationException(BasicErrorEnum.NOT_FOUND_HANDLER.getCode(),
                    BasicErrorEnum.NOT_FOUND_HANDLER.getMessage());
        }

        ${className}Entity entity = ${className}Mapper.INSTANCE.updateDto2Entity(updateDTO);
        entity.setUpdateUser(OtherConstants.SYSUSER_NAME);
        boolean result = this.lambdaUpdate().eq(BaseEntity::getId,entity.getId()).update(entity);
        if (!result) {
            throw new ApplicationException(BasicErrorEnum.MYSQL_OPERATION_ERROR.getCode(),
                    BasicErrorEnum.MYSQL_OPERATION_ERROR.getMessage());
        }
    }


    /**
     * 单条查询${tableRemarks}
     *
     * @param id id
     * @return {@link ${className}VO}
     */
    public ${className}VO find${className}ById(Long id) {
        ${className}Entity entityDb = get${className}ById(id);
        if (null == entityDb) {
            throw new ApplicationException(BasicErrorEnum.NOT_FOUND_HANDLER.getCode(),
                    BasicErrorEnum.NOT_FOUND_HANDLER.getMessage());
        }
        ${className}Entity entity = get${className}ById(id);
        ${className}VO vo = ${className}Mapper.INSTANCE.entity2Vo(entity);
        LOG.info("[根据id查询单条${tableRemarks}数据] 执行结果={}", vo);
        return vo;
    }

    /**
     * 根据ID查询${tableRemarks}
     *
     * @param id id
     * @return {@link ${className}Entity}
     */
    public ${className}Entity get${className}ById(Long id) {
        return this.lambdaQuery().eq(BaseEntity::getId, id).eq(BaseEntity::getIsDelete, OtherConstants.FALSE).one();
    }

    /**
     * 分页查询${tableRemarks}
     *
     * @param qo     查询参数
     * @return {@link PageResultVO<${className}VO>}
     */
    public PageResultVO<${className}VO> findPage${className}(${className}QO qo) {
        Page<${className}Entity> pageQuery = new Page<>();
        pageQuery.setCurrent(qo.getPageNum());
        pageQuery.setSize(qo.getPageSize());
        // TODO : pageFind${className} 方法的具体 sql 需要自己去实现
        IPage<${className}VO> result = dao.pageFind${className}(pageQuery, qo, TenantIdUtil.getTenantId());
        PageResultVO<${className}VO> pageResult = new PageResultVO<>(result);
        LOG.info("[分页查询${tableRemarks}数据] 执行结果={}", pageResult);
        return pageResult;
    }


    /**
     * 删除${tableRemarks}
     *
     * @param id id
     */
    public void delete${className}ById(Long id) {
        ${className}Entity entityDb = get${className}ById(id);
        if (null == entityDb) {
            throw new ApplicationException(BasicErrorEnum.NOT_FOUND_HANDLER.getCode(),
                    BasicErrorEnum.NOT_FOUND_HANDLER.getMessage());
        }

        ${className}Entity entity = new ${className}Entity();
        entity.setId(id);
        entity.setIsDelete(OtherConstants.TRUE);
        entity.setUpdateTime(LocalDateTime.now());
        entity.setUpdateUser(OtherConstants.SYSUSER_NAME);
        boolean result = this.lambdaUpdate().eq(BaseEntity::getId, entity.getId()).update(entity);
        if (!result) {
            throw new ApplicationException(BasicErrorEnum.MYSQL_OPERATION_ERROR.getCode(),
                    BasicErrorEnum.MYSQL_OPERATION_ERROR.getMessage());
        }
    }

    /**
     * 查询全部${tableRemarks}
     *
     * @return {@link List<${className}VO>}
     */
    public List<${className}VO> getAll() {
        List<${className}Entity> entityList = this.lambdaQuery()
        .eq(${className}Entity::getTenantId, TenantIdUtil.getTenantId())
        .eq(BaseEntity::getIsDelete, OtherConstants.FALSE).list();
        return ${className}Mapper.INSTANCE.entityList2VoList(entityList);
    }
}
