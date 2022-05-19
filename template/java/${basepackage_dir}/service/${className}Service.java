<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#assign tableRemarks = table.remarks>
package ${basepackage}.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import ${basepackage}.domain.dto.PageDTO;
import ${basepackage}.domain.dto.BatchDTO;
import ${basepackage}.domain.vo.${className}VO;
import ${basepackage}.constants.enums.error.BasicErrorEnum;
import ${basepackage}.exception.ApplicationException;
import ${basepackage}.mapperstruct.${className}Mapper;

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
        ${className}Entity entity = ${className}Mapper.INSTANCE.${classNameLower}Dto2${className}Entity(dto);
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
     * @param dto dto
     */
    public void update${className}ById(${className}DTO dto) {
        ${className}Entity entityDb = get${className}ById(dto.getId());
        if (null == entityDb) {
            throw new ApplicationException(BasicErrorEnum.NOT_FOUND_HANDLER.getCode(),
                    BasicErrorEnum.NOT_FOUND_HANDLER.getMessage());
        }

        ${className}Entity entity = ${className}Mapper.INSTANCE.${classNameLower}Dto2${className}Entity(dto);
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
        ${className}VO vo = ${className}Mapper.INSTANCE.${classNameLower}Entity2${className}Vo(entity);
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
        QueryWrapper<${className}Entity> query = new QueryWrapper<>();
        query.eq(OtherConstants.ID, id);
        query.eq(OtherConstants.IS_DELETE, OtherConstants.FALSE);
        return this.getOne(query);
    }

    /**
     * 分页查询${tableRemarks}
     *
     * @param pageDTO 分页入参
     * @param dto     查询参数
     * @return {@link PageResultVO<${className}VO>}
     */
    public PageResultVO<${className}VO> findPage${className}(PageDTO pageDTO, ${className}DTO dto) {
        Page<${className}Entity> pageQuery = new Page<>();
        pageQuery.setCurrent(pageDTO.getPageNum());
        pageQuery.setSize(pageDTO.getPageSize());
        // TODO : pageFind${className} 方法的具体 sql 需要自己去实现
        IPage<${className}VO> result = dao.pageFind${className}(pageQuery, dto);
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
        UpdateWrapper<${className}Entity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(OtherConstants.ID, id);

        ${className}Entity entity = new ${className}Entity();
        entity.setId(id);
        entity.setIsDelete(OtherConstants.TRUE);
        entity.setUpdateTime(LocalDateTime.now());
        entity.setUpdateUser(OtherConstants.SYSUSER_NAME);
        boolean result = this.update(entity, updateWrapper);
        if (!result) {
            throw new ApplicationException(BasicErrorEnum.MYSQL_OPERATION_ERROR.getCode(),
                    BasicErrorEnum.MYSQL_OPERATION_ERROR.getMessage());
        }
    }


    /**
     * 批量处理${tableRemarks}
     *
     * @param batchDTO 批量处理
     */
    public void batchHandle${className}(BatchDTO<${className}DTO> batchDTO) {
        List<Long> deleteList = batchDTO.getDelete();
        if (deleteList != null) {
            batchDelete${className}(deleteList);
        }
    }

    /**
     * 批量删除${tableRemarks}
     *
     * @param deleteList id列表
     */
    public void batchDelete${className}(List<Long> deleteList) {
        List<${className}Entity> list = new ArrayList<>();
        for (Long id : deleteList) {
            ${className}Entity entity = new ${className}Entity();
            entity.setId(id);
            entity.setIsDelete(OtherConstants.TRUE);
            entity.setUpdateTime(LocalDateTime.now());
            entity.setUpdateUser(OtherConstants.SYSUSER_NAME);
            list.add(entity);
        }
        boolean result = this.updateBatchById(list);
        if (!result) {
            throw new ApplicationException(BasicErrorEnum.MYSQL_OPERATION_ERROR.getCode(),
                    BasicErrorEnum.MYSQL_OPERATION_ERROR.getMessage());
        }
    }
}
