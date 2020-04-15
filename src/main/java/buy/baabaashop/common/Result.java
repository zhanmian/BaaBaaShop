package buy.baabaashop.common;

public class Result<T> {

	//状态码
	private Integer code;

	//返回信息
	private String message;

	//返回类型
	private T data;

	protected Result() {}

	public Result(T data, ResultEnum code) {
		super();
		this.data = data;
		this.code = code.getCode();
	}

	public Result(T data, Integer code, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
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

    public static <T> Result<T> success() {
        return new Result<T>(null, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(T data) {
		return new Result<T>(data, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
	}

	public static <T> Result<T> success(T data, String message) {
		return new Result<T>(data, ResultEnum.SUCCESS.getCode(), message);
	}

    public static <T> Result<T> failed() {
        return new Result<T>(null, ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }

    public static <T> Result<T> failed(String message) {
        return new Result<T>(null, ResultEnum.ERROR.getCode(), message);
    }

    public static <T> Result<T> failed(T data) {
        return new Result<T>(data, ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }

	public static <T> Result<T> unauthorized(T data) {
		return new Result<T>(data, ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getMessage());
	}

	public static <T> Result<T> forbidden(T data) {
		return new Result<T>(data, ResultEnum.FORBIDDEN.getCode(), ResultEnum.FORBIDDEN.getMessage());
	}

	public static <T> Result<T> validateFailed(String message) {
		return new Result<T>(null, ResultEnum.VALIDATE_FAILED.getCode(), message);
	}
}
