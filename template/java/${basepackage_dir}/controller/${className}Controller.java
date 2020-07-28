<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#assign tableRemarks = table.remarks>
package ${basepackage}.controller;

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
// TODO : 路径根据需要进行修改
@RequestMapping("/${classNameLower}")
public class ${className}Controller {

    private static final Logger log = LoggerFactory.getLogger(${className}Controller.class);

    @Autowired
    private ${className}Service service;

    /**
     * 添加${tableRemarks}
     *
     * @param vo VO
     * @return {@link ResponseEntity<Long>}
     */
    @Operation(description = "添加${tableRemarks}")
    @PostMapping
    public ResponseEntity<Long> add${className}(@Validated(GobalRule.Add.class) @RequestBody ${className}ReqVO vo) {
        log.info("[添加${tableRemarks}]请求入参={}", vo);
        return new ResponseEntity<>(service.save${className}(vo), HttpStatus.CREATED);
    }

    /**
     * 根据id修改${tableRemarks}
     *
     * @param vo VO
     * @return {@link Boolean}
     */
    @Operation(description = "根据id修改${tableRemarks}")
    @PutMapping
    public Boolean update${className}ById(@Validated(GobalRule.Update.class) @RequestBody ${className}ReqVO vo) {
        log.info("[根据id修改${tableRemarks}]请求入参={}", vo);
        return service.update${className}ById(vo);
    }

    /**
     * 根据ID单条查询${tableRemarks}
     *
     * @param id id
     * @return {@link ${className}VO}
     */
    @Operation(description = "根据ID单条查询${tableRemarks}")
    @GetMapping("/{id}")
    public ${className}VO find${className}ById(@PathVariable("id") Long id) {
        log.info("[根据ID查询${tableRemarks}]请求入参id={}", id);
        return service.findOne${className}ById(id);
    }

    /**
     * 根据条件分页查询${tableRemarks}
     *
     * @param pageRequestVO 分页请求VO
     * @param vo            VO
     * @return {@link PageResultVO<${className}VO>}
     */
    @Operation(description = "根据条件分页查询${tableRemarks}")
    @GetMapping("/page-list")
    public PageResultVO<${className}VO> findPage${className}(PageRequestVO pageRequestVO, @Validated(GobalRule.Query.class) ${className}ReqVO vo) {
        log.info("[根据条件分页查询${tableRemarks}]请求入参={} ", vo);
        return service.findPage${className}(pageRequestVO, vo);
    }

    /**
     * 根据条件查询${tableRemarks}列表
     *
     * @param vo VO
     * @return {@link List<${className}VO>}
     */
    @Operation(description = "根据条件查询${tableRemarks}列表")
    @GetMapping("/list")
    public List<${className}VO> find${className}ListByVo(${className}ReqVO vo) {
        log.info("[根据条件查询${tableRemarks}列表]请求入参={}", vo);
        return service.find${className}ListByVo(vo);
    }

    /**
     * 删除${tableRemarks}
     *
     * @param id id
     * @return {@link ResponseEntity<Boolean>}
     */
    @DeleteMapping("/{id}")
    @Operation(description = "删除${tableRemarks}")
    public ResponseEntity<Boolean> delete${className}ById(@PathVariable Long id) {
        log.info("[删除${tableRemarks}]请求入参:{}", id);
        return new ResponseEntity<>(service.delete${className}ById(id), HttpStatus.NO_CONTENT);
    }


    /**
     * 批量删除${tableRemarks}
     *
     * @param batchVo id列表
     * @return {@link ResponseEntity<Boolean>}
     */
    @Operation(description = "批量删除${tableRemarks}")
    @PostMapping("/batch")
    public ResponseEntity<Boolean> batch(@RequestBody BatchVO<Long> batchVo) {
        log.info("[批量删除${tableRemarks}]请求入参:{}", batchVo);
        return new ResponseEntity<>(service.deleteBatch${className}ById(batchVo.getDelete()), HttpStatus.NO_CONTENT);
    }

}
