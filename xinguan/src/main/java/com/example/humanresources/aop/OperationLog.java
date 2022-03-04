package com.example.humanresources.aop;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.dao.TbLogDao;
import com.example.humanresources.entity.TbLog;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.example.humanresources.utils.IpUtil;
import com.example.humanresources.utils.JwtUtil;
import com.example.humanresources.utils.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author zf
 * @since 2021/12/09
 */
@Aspect
@Component
public class OperationLog {

    @Resource
    private TbLogDao tbLogDao;

    @Around("@annotation(com.example.humanresources.annotation.Operation)")
    public R aroundPrintLog(ProceedingJoinPoint joinPoint) throws Throwable {

        TbLog syslog = new TbLog();
        R result = null;


        // 获取方法注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Operation annotation = method.getAnnotation(Operation.class);
        // 设置注解信息
        syslog.setOperation(annotation.value());

        // 获取参数名
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        // 获取参数列表
        Object[] args = joinPoint.getArgs();
        syslog.setParams("参数名: " + Arrays.toString(parameterNames) + ",值: " + Arrays.toString(args));

        long start = System.currentTimeMillis();

        result = (R) joinPoint.proceed(args);

        // 设置操作时长
        syslog.setTime((double) (System.currentTimeMillis() - start));

        // 设置方法值
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        syslog.setMethod(className + "." + methodName + "()" + "response: " +
                "{ code : " + result.getCode() + ", msg : " + result.getMsg() + "} , ");
        syslog.setCreateTime(new Date());

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = request.getRemoteAddr();
        syslog.setIp(ip);
        String address = IpUtil.getAddress(ip);
        syslog.setLocation(address);

        try {
            String token = request.getHeader("Authorization");
            String username = JwtUtil.getUsername(token);
            syslog.setUsername(username);
        } catch (NullPointerException e) {
            //e.printStackTrace();
            throw new ServiceException(ErrorCodeEnum.SIGNATURE_NOT_MATCH);
        }

        tbLogDao.insert(syslog);

        return result;
    }
}
