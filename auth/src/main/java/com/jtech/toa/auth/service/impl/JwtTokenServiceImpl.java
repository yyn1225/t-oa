/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.auth.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jtech.toa.auth.constants.WebConst;
import com.jtech.toa.auth.service.IJwtTokenService;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;


/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class JwtTokenServiceImpl implements IJwtTokenService {


    private static final String JWT_SECRET;
    private static final String ISSUER = "http://xh.jing-tong.com";
    private static final Algorithm HMAC_ALGORITHM;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenServiceImpl.class);

    static {
        JWT_SECRET = Base64.encodeBase64String("toa#hefei@shushanqu$jingtong^tech".getBytes());
        try {
            HMAC_ALGORITHM = Algorithm.HMAC256(JWT_SECRET);
        } catch (UnsupportedEncodingException e) {
            logger.error("create Algorithm has error!", e);
            throw new RuntimeException("create Algorithm has error!", e);
        }
    }




    @Override
    public String createToken(long userId, int expiresDays) {
        if (expiresDays <= 0) {
            expiresDays = 1;
        }
        final DateTime now = DateTime.now();
        final Date expiresAtDate = now.plusDays(expiresDays).toDate();
//        final Date expiresAtDate = now.plusMinutes(1).toDate();
        return JWT.create()
                .withClaim(WebConst.JWT_CLAIM_UID, userId)
                .withClaim(PublicClaims.EXPIRES_AT, expiresAtDate)
                .withClaim(PublicClaims.ISSUED_AT, now.plusDays(-1).toDate())
                .withIssuer(ISSUER)
                .sign(HMAC_ALGORITHM);
    }

    @Override
    public String createToken(long userId, int expiresDays,String language) {
        if (expiresDays <= 0) {
            expiresDays = 1;
        }
        final DateTime now = DateTime.now();
        final Date expiresAtDate = now.plusDays(expiresDays).toDate();
        return JWT.create()
                .withClaim(WebConst.JWT_CLAIM_UID, userId)
                .withClaim(PublicClaims.EXPIRES_AT, expiresAtDate)
                .withClaim(PublicClaims.ISSUED_AT, now.plusDays(-1).toDate())
                .withClaim("language", org.apache.commons.lang3.StringUtils.isEmpty(language)?"zh":language)
                .withIssuer(ISSUER)
                .sign(HMAC_ALGORITHM);
    }

    @Override
    public DecodedJWT verify(String jwt) {
        JWTVerifier verifier;
        verifier = JWT.require(HMAC_ALGORITHM).withIssuer(ISSUER).build();
        return verifier.verify(jwt);
    }
}
