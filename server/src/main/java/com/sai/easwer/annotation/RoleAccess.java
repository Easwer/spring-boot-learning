package com.sai.easwer.annotation;

import com.sai.easwer.constants.UserRoleEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Role Access Annotation.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-06-22 14:51:30
 * @modify date 2020-06-22 14:51:30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RoleAccess {

  UserRoleEnum role();

}