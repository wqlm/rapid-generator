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

import ${basepackage}.constants.Constants;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.domain.entity.${className}Entity;
import ${basepackage}.domain.vo.PageResultVO;
import ${basepackage}.domain.vo.request.${className}ReqVO;
import ${basepackage}.domain.vo.request.PageRequestVO;
import ${basepackage}.domain.vo.response.${className}VO;
import ${basepackage}.enums.BasicErrorEnum;
import ${basepackage}.exception.ApplicationException;
import ${basepackage}.mapperstruct.${className}Mapper;
import ${basepackage}.utils.VenusUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ${className}Service extends ServiceImpl<BaseMapper<${className}Entity>, ${className}Entity> {

	private static final Logger log = LoggerFactory.getLogger(${className}Service.class);

	@Autowired
	private ${className}Dao dao;

	/**
	 * 添加${tableRemarks}
	 *
	 * @param vo VO
	 * @return {@link Long}
	 */
	public Long save${className}(${className}ReqVO vo){
		try {
			String appKey = VenusUtil.getVenusAppKey();
			${className}Entity entity = ${className}Mapper.INSTANCE.switchRequest2Entity(vo);
			entity.setId(null);
			entity.setAppKey(appKey);
			entity.setCreateTime(LocalDateTime.now());
			entity.setCreateUser("sys");
            boolean success = this.save(entity);
			log.info("[添加${tableRemarks}数据] 执行结果={}", success);
			return entity.getId();
		} catch (Exception e) {
			log.error("[添加${tableRemarks}数据] 异常信息:", e);
			throw new ApplicationException(BasicErrorEnum.BASIC_ADD_ERROR.getCode(),
                                            BasicErrorEnum.BASIC_ADD_ERROR.getError_code(),
                                            BasicErrorEnum.BASIC_ADD_ERROR.getMessage());
		}
	}

	/**
	 * 根据id修改${tableRemarks}
	 *
	 * @param vo VO
	 * @return {@link Boolean}
	 */
    public Boolean update${className}ById(${className}ReqVO vo) {
        try {
            UpdateWrapper<${className}Entity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", vo.getId());
            updateWrapper.eq("app_key", VenusUtil.getVenusAppKey());

            ${className}Entity entity = ${className}Mapper.INSTANCE.switchRequest2Entity(vo);
            entity.setUpdateTime(LocalDateTime.now());
            entity.setUpdateUser("sys");
            boolean success = this.update(entity, updateWrapper);
            log.info("[修改${tableRemarks}数据] 执行结果={}", success);
            return success;
        } catch (Exception e) {
            log.error("[修改${tableRemarks}数据]异常信息:", e);
            throw new ApplicationException(BasicErrorEnum.BASIC_UPDATE_ERROR.getCode(),
                                            BasicErrorEnum.BASIC_UPDATE_ERROR.getError_code(),
                                            BasicErrorEnum.BASIC_UPDATE_ERROR.getMessage());
        }
    }


	/**
	 * 根据ID单条查询${tableRemarks}
	 *
	 * @param id id
	 * @return {@link ${className}VO}
	 */
	public ${className}VO findOne${className}ById(Long id) {
		QueryWrapper<${className}Entity> query = new QueryWrapper<>();
		query.eq(Constants.ID, id);
		query.eq(Constants.APP_KEY, VenusUtil.getVenusAppKey());
		query.eq(Constants.IS_DELETE, Constants.LOGIC_DELETE_NOT_DELETED);
		${className}Entity entity = this.getOne(query);
		${className}VO vo = ${className}Mapper.INSTANCE.switchEntity2Response(entity);

		log.info("[根据id查询单条${tableRemarks}数据] 执行结果={}", vo);
		return vo;
	}

	/**
	 * 分页查询${tableRemarks}
	 *
	 * @param pageRequestVO     分页入参
	 * @param ${classNameLower}ReqVo 请求VO
	 * @return {@link PageResultVO<BaseTemplateVO>}
	 */
	public PageResultVO<${className}VO> findPage${className}(PageRequestVO pageRequestVO, ${className}ReqVO ${classNameLower}ReqVo) {
		try {
			Page<${className}Entity> pageQuery = new Page<>();
			pageQuery.setCurrent(pageRequestVO.getPageNum());
			pageQuery.setSize(pageRequestVO.getPageSize());

			${className}Entity entity = ${className}Mapper.INSTANCE.switchRequest2Entity(${classNameLower}ReqVo);
			entity.setAppKey(VenusUtil.getVenusAppKey());
			// TODO : pageFind${className} 方法的具体 sql 需要自己去实现
			IPage<${className}VO> result = dao.pageFind${className}(pageQuery, entity);
			PageResultVO<${className}VO> pageResult = new PageResultVO<>(result);
			log.info("[分页查询${tableRemarks}数据] 执行结果={}", pageResult);
			return pageResult;
		} catch (Exception e) {
			log.error("[分页查询${tableRemarks}数据] 异常信息:", e);
			throw new ApplicationException(BasicErrorEnum.BASIC_FIND_ERROR.getCode(),
			BasicErrorEnum.BASIC_FIND_ERROR.getError_code(),
			BasicErrorEnum.BASIC_FIND_ERROR.getMessage());
		}
	}


	/**
	 * 根据条件查询${tableRemarks}列表
	 *
	 * @param vo VO
	 * @return {@link List<${className}VO>}
	 */
	public List<${className}VO>	find${className}ListByVo(${className}ReqVO vo){
		${className}Entity entity = ${className}Mapper.INSTANCE.switchRequest2Entity(vo);
		entity.setAppKey(VenusUtil.getVenusAppKey());
		// TODO : find${className}ListByVo(entity) 的sql需要自己去实现
		List<${className}VO> voList = dao.find${className}ListByVo(entity);
		log.info("[根据条件查询${tableRemarks}列表] 执行结果={}", voList);
		return voList;
	}

	/**
	 * 删除${tableRemarks}
	 *
	 * @param id id
	 * @return {@link Boolean}
	 */
	public Boolean delete${className}ById(Long id) {
		try {
			UpdateWrapper<${className}Entity> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq(Constants.ID, id);
			updateWrapper.eq(Constants.APP_KEY, VenusUtil.getVenusAppKey());

			${className}Entity entity = new ${className}Entity();
			entity.setId(id);
			entity.setIsDelete(Constants.LOGIC_DELETE_IS_DELETED);
			entity.setUpdateTime(LocalDateTime.now());
			entity.setUpdateUser("sys");
			boolean success = this.update(entity, updateWrapper);
			log.info("[删除${tableRemarks}数据] 执行结果={}", success);
			return success;
		} catch (Exception e) {
			log.error("[删除${tableRemarks}数据]异常信息:", e);
			return false;
		}
	}


	/**
	 * 批量删除${tableRemarks}
	 *
	 * @param deleteList id列表
	 * @return {@link Boolean}
	 */
	public Boolean deleteBatch${className}ById(List<Long> deleteList) {
		try {
			List<${className}Entity> list = new ArrayList<>();
			for (Long id : deleteList) {
				${className}Entity entity = new ${className}Entity();
				entity.setId(id);
				entity.setIsDelete(Constants.LOGIC_DELETE_IS_DELETED);
				entity.setUpdateTime(LocalDateTime.now());
				entity.setUpdateUser("sys");
				list.add(entity);
			}
			boolean success = this.updateBatchById(list);
			log.info("[批量删除${tableRemarks}数据] 执行结果={}", success);
			return success;
		} catch (Exception e) {
			log.error("批量删除${tableRemarks}数据] 异常信息:", e);
			return false;
		}
	}
}
