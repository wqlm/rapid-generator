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
     * @return {@link ResponseEntity<Long>}
     */
    @Operation(description = "添加${tableRemarks}")
    @PostMapping
    public ResponseEntity<Long> add${className}(@Validated(CheckGroup.Add.class) @RequestBody ${className}DTO dto) {
        LOG.info("[添加${tableRemarks}],请求入参={}", dto);
        return new ResponseEntity<>(service.add${className}(dto), HttpStatus.CREATED);
    }

    /**
     * 更新${tableRemarks}
     *
     * @param dto 请求参数
     * @return {@link ResponseEntity}
     */
    @Operation(description = "更新${tableRemarks}")
    @PutMapping
    public ResponseEntity update${className}(@Validated(CheckGroup.Update.class) @RequestBody ${className}DTO dto) {
        LOG.info("[更新${tableRemarks}],请求入参={}", dto);
        service.update${className}ById(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * 根据ID查询${tableRemarks}
     *
     * @param id id
     * @return {@link ${className}VO}
     */
    @Operation(description = "根据ID查询${tableRemarks}")
    @GetMapping("/{id}")
    public ${className}VO find${className}ById(@PathVariable("id") Long id) {
        LOG.info("[根据ID查询${tableRemarks}],请求入参id={}", id);
        return service.find${className}ById(id);
    }

    /**
     * 根据条件分页查询${tableRemarks}
     *
     * @param pageDTO 分页dto
     * @param dto     请求参数
     * @return {@link PageResultVO<${className}VO>}
     */
    @Operation(description = "根据条件分页查询${tableRemarks}")
    @GetMapping
    public PageResultVO<${className}VO> findPage${className}(@Validated(CheckGroup.Query.class) PageDTO pageDTO, @Validated(CheckGroup.Query.class) ${className}DTO dto) {
        LOG.info("[根据条件分页查询${tableRemarks}],请求入参={} ", dto);
        return service.findPage${className}(pageDTO, dto);
    }


    /**
     * 删除${tableRemarks}
     *
     * @param id id
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/{id}")
    @Operation(description = "删除${tableRemarks}")
    public ResponseEntity delete${className}(@PathVariable Long id) {
        LOG.info("[删除${tableRemarks}],请求入参:{}", id);
        service.delete${className}ById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * 批量添加、修改、删除${tableRemarks}
     *
     * @param batchDTO 请求参数
     * @return {@link ResponseEntity}
     */
    @PostMapping("/batch")
    @Operation(description = "批量处理${tableRemarks}")
    public ResponseEntity batchHandle${className}(@RequestBody BatchDTO<${className}DTO> batchDTO) {
        LOG.info("[批量处理${tableRemarks}],请求入参:{}", batchDTO);
        service.batchHandle${className}(batchDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
