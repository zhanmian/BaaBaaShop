package buy.baabaashop.common;

public enum ResultEnum {
	ERROR(300, "失败"),
	SUCCESS(100, "成功"),
	FORBIDDEN(405, "没有相关权限"),
	UNAUTHORIZED(406, "暂未登录或token已经过期"),
	VALIDATE_FAILED(407, "参数检验失败");

	private Integer code;
	private String message;

	ResultEnum(Integer code, String message) {
		this.code=code;
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
