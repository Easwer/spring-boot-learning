package com.sai.easwer.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * Audit Log Aspect.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:02:29
 */
@Aspect
public class AuditLogAspect {

    @AfterReturning
    public void createAuditLog() {
    }
}
