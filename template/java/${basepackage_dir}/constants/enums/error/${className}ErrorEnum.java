<#assign className = table.className>
<#assign tableRemarks = table.remarks>
package ${basepackage}.constants.enums.error;

/**
 * ${tableRemarks}的异常枚举
 */
public enum ${className}ErrorEnum {

    ;

    private String code;
    private String message;

    ${className}ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}