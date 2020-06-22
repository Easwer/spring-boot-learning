package com.sai.easwer.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.UUID;

import com.sai.easwer.annotation.RoleAccess;
import com.sai.easwer.model.ResponseStatus;
import com.sai.easwer.model.Response;
import com.sai.easwer.util.SecurityUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * User role access control aspect for REST API.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-06-19 22:21:09
 */
@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Slf4j
public class UserAccessAspect {

  @Autowired
  private SecurityUtils securityUtils;

  private static final String FIRST_PARAMETER = "execution(public * com.sai.easwer.service.*Service.*(@com.sai.easwer.annotation.RoleAccess (*), ..)) || ";

  private static final String MIDDLE_PARAMETER = "execution(public * com.sai.easwer.service.*Service.*(.., @com.sai.easwer.annotation.RoleAccess (*), ..)) || ";

  private static final String LAST_PARAMETER = "execution(public * com.sai.easwer.service.*Service.*(.., @com.sai.easwer.annotation.RoleAccess (*)))";

  private static final String ASPECT_CONDITION = FIRST_PARAMETER + MIDDLE_PARAMETER + LAST_PARAMETER;

  private static final String EXCEPTION_WHILE_CHECKING_AUTHORIZATION_DUE_TO = "Exception while checking authorization due to ";

  private static final String DON_T_HAVE_ACCESS_TO_THIS_API = "Don't have access to this API";

  /**
   * Aspect to check user role access for public API's in service methods.
   * 
   * @param joinPoint {@link ProceedingJoinPoint}
   * @return {@link Object}
   * @throws Throwable Exception
   */
  @Around(ASPECT_CONDITION)
  public Object before(final ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      final Object[] args = joinPoint.getArgs();
      final MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
      final Method method = methodSignature.getMethod();
      final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
      assert args.length == parameterAnnotations.length;
      for (int argIndex = 0; argIndex < args.length; argIndex++) {
        for (final Annotation annotation : parameterAnnotations[argIndex]) {
          if (annotation != null && annotation instanceof RoleAccess) {
            final RoleAccess requestParam = (RoleAccess) annotation;
            try {
              if (!securityUtils.checkAuthorization(requestParam.role().toString(), (UUID) args[argIndex])) {
                return createResponse(DON_T_HAVE_ACCESS_TO_THIS_API, ResponseStatus.FAILURE, null,
                    HttpStatus.UNAUTHORIZED);
              }
            } catch (final Exception e) {
              return createResponse(e.getMessage(), ResponseStatus.FAILURE, null, HttpStatus.UNAUTHORIZED);
            }
          }
        }
      }
    } catch (final Exception e) {
      log.error(EXCEPTION_WHILE_CHECKING_AUTHORIZATION_DUE_TO, e.getMessage());
    }
    return joinPoint.proceed();
  }

  /**
   * This method is used to create {@link ResponseEntity}<{@link Response}> for
   * all REST API's.
   * 
   * @param message    {@link String}
   * @param status     {@link ResponseStatus}
   * @param object     {@link Object}
   * @param httpStatus {@link HttpStatus}
   * @return REST API Response as {@link ResponseEntity}<{@link Response}>
   */
  public ResponseEntity<Response> createResponse(final String message, final ResponseStatus status, final Object object,
      final HttpStatus httpStatus) {
    final Response response = new Response();
    response.setMessage(message);
    response.setObject(object);
    response.setStatus(status);
    return new ResponseEntity<Response>(response, httpStatus);
  }

}
