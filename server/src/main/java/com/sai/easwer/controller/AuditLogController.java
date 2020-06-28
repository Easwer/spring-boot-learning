package com.sai.easwer.controller;

import com.sai.easwer.api.AuditlogApi;

import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is used to extend the auto generated interface because, the
 * auto generated interface doesn't support OpenAPI 3.0 annotations. Once that
 * bug is fixed. Need to remove these niteraces and use auto generated
 * interfaces directly in service class.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-06-25 13:26:02
 * @modify date 2020-06-25 13:26:02
 */
@RestController
public interface AuditLogController extends AuditlogApi {

}
