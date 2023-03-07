package com.yautumn.common.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultUtil {

    public ResultUtil() {
    }

    private int resultCode;
    private String resultMsg;
    private Object resultData;

    public ResultUtil(int resultCode, String resultMsg, Object resultData) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    public ResultUtil(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResultUtil(String resultMsg, Object resultData) {
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    public ResultUtil(Object resultData) {
        this.resultData = resultData;
    }

    public ResultUtil(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public static ResultUtil success(){
        return new ResultUtil(1,"操作成功");
    }

    public static ResultUtil success(String resultMsg){
        return new ResultUtil(1,resultMsg);
    }

    public static ResultUtil success(String resultMsg,Object resultData){
        return new ResultUtil(1,resultMsg,resultData);
    }

    public static ResultUtil success(Object resultData){
        return new ResultUtil(1,"操作成功",resultData);
    }

    public static ResultUtil error(){
        return new ResultUtil(-1,"操作失败");
    }

    public static ResultUtil error(String resultMsg){
        return new ResultUtil(-1,resultMsg);
    }

    public static ResultUtil error(String resultMsg,Object resultData){
        return new ResultUtil(-1,resultMsg,resultData);
    }
}
