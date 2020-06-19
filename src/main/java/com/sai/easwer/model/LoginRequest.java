/**
 * Login REST API Request model.
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-05-01 15:10:55
 * @modify date 2020-05-01 15:10:55
 */
package com.sai.easwer.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement
public class LoginRequest {

  @XmlAttribute(required = true)
  private String username;

  @XmlAttribute(required = true)
  private String password;

}