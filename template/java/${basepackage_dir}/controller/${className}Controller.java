<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#assign tableRemarks = table.remarks>
package ${basepackage}.controller;

import ${basepackage}.domain.vo.PageResultVO;
import ${basepackage}.domain.dto.${className}DTO;
import ${basepackage}.domain.dto.PageDTO;
import ${basepackage}.domain.dto.BatchDTO;
import ${basepackage}.domain.vo.${className}VO;
import ${basepackage}.utils.CheckGroup;
import ${basepackage}.service.${className}Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ${basepackage}.domain.dto.${className}UpdateDTO;
import ${basepackage}.domain.qo.${className}QO;
import java.util.List;

@Tag(name = "${tableRemarks}")
@RestController
// TODO : url根据需要进行修改
@RequestMapping("/${classNameLower}")
public class ${className}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${className}Controller.class);

    @Autowired
    private ${className}Service service;

    /**
     * 添加${tableRemarks}
     *
     * @param dto 请求参数
     * @return id
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long add${className}(@Validated @RequestBody ${className}DTO dto) {
        LOG.info("[添加${tableRemarks}],请求入参={}", dto);
        return service.add${className}(dto);
    }

    /**
     * 更新${tableRemarks}
     *
     * @param updateDTO 请求参数
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update${className}(@Validated @RequestBody ${className}UpdateDTO updateDTO) {
        LOG.info("[更新${tableRemarks}],请求入参={}", updateDTO);
        service.update${className}ById(updateDTO);
    }

    /**
     * 根据ID查询${tableRemarks}
     *
     * @param id id
     * @return {@link ${className}VO}
     */
    @PostMapping("/id")
    public ${className}VO find${className}ById(Long id) {
        LOG.info("[根据ID查询${tableRemarks}],请求入参id={}", id);
        return service.find${className}ById(id);
    }

    /**
     * 根据条件分页查询${tableRemarks}
     *
     * @param qo     请求参数
     * @return {@link PageResultVO<${className}VO>}
     */
    @PostMapping("/page")
    public PageResultVO<${className}VO> findPage${className}(
            @RequestBody @Validated(CheckGroup.Query.class) ${className}QO qo) {
        LOG.info("[根据条件分页查询${tableRemarks}],请求入参={} ", qo);
        return service.findPage${className}(qo);
    }


    /**
     * 删除${tableRemarks}
     *
     * @param id id
     */
    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete${className}(Long id) {
        LOG.info("[删除${tableRemarks}],请求入参:{}", id);
        service.delete${className}ById(id);
    }


    /**
     * 查询全部${tableRemarks}
     *
     * @return {@link List<${className}VO>}
     */
    @PostMapping("/all")
    public List<${className}VO> getAll() {
        return service.getAll();
    }

}
