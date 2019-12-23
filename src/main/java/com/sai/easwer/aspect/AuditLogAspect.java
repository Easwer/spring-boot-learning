package com.sai.easwer.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AuditLogAspect
{

    @AfterReturning
    public void createAuditLog()
    {

    }
}