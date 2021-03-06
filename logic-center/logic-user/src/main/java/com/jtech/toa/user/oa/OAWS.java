
/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.user.oa;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OAWS", targetNamespace = "http://oaws.absen.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface OAWS {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkLogin", targetNamespace = "http://oaws.absen.com/", className = "com.jtech.toa.user.oa.CheckLogin")
    @ResponseWrapper(localName = "checkLoginResponse", targetNamespace = "http://oaws.absen.com/", className = "com.jtech.toa.user.oa.CheckLoginResponse")
    public boolean checkLogin(@WebParam(name = "arg0", targetNamespace = "")String arg0);
}
