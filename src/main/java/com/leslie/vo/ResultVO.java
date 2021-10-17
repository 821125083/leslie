package com.leslie.vo;

public class ResultVO {

    private Integer code;//

    private String message;

    private Object data;

    public static ResultVO success(){
        ResultVO result = new ResultVO();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    public static ResultVO success(Object data){
        ResultVO result = new ResultVO();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static ResultVO error(String message) {
        ResultVO result = new ResultVO();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    public ResultVO() {
    }

    public ResultVO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
