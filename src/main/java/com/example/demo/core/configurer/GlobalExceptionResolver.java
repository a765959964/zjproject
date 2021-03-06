package com.example.demo.core.configurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.core.ret.RetCode;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ControllerAdvice 注解的类是当前项目中所有类的统一异常处理类
 * @ExceptionHandler 注解的方勇用来定义函数针对异常类型以及异常如何处理
 * @Description: 全局异常处理
 * @author zf
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundPage404(HttpServletResponse response) {
        response.setStatus(200);
        return "views/commons/404.html";

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void notFoundPage500(HttpServletResponse response) {
        RetResult<Object> result = new RetResult<>();
        result.setCode(RetCode.INTERNAL_SERVER_ERROR).setMsg("内部服务器出错").setData(null);
        responseResult(response, result);
    }



    @ExceptionHandler(UnauthenticatedException.class)
    public void page401(HttpServletRequest req,HttpServletResponse response, UnauthenticatedException e) throws ServletException, IOException {
        RetResult<Object> result = new RetResult<>();
            result.setCode(RetCode.UNAUTHEN).setMsg("用户未登录").setData(null);
        responseResult(response, result);
        req.getRequestDispatcher("/login").forward(req,response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public void page403(HttpServletResponse response) {
        RetResult<Object> result = new RetResult<>();
        result.setCode(RetCode.UNAUTHZ).setMsg("用户没有访问权限").setData(null);
        responseResult(response, result);
    }
    /**
     * 业务异常的处理
     */
    @ExceptionHandler(value = ServiceException.class)
    public void serviceExceptionHandler(HttpServletResponse response, ServiceException e) {
        RetResult<Object> result = new RetResult<>();
        result.setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
        responseResult(response, result);
    }
    /**
     * 其他异常统一处理
     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Object exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
//        System.out.println("GlobalExceptionResolver exceptionHandler() .......");
//        Map<String,Object> map = new HashMap();
//        map.put("code",100);
//        map.put("message",e.getMessage());
//        map.put("url",req.getRequestURI().toString());
//        map.put("data","请求失败");
//        return map;
//    }
    /**
     * @param response
     * @param result
     * @Title: responseResult
     * @Description: 响应结果
     * @Reutrn void
     */
    private void responseResult(HttpServletResponse response, RetResult<Object> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
