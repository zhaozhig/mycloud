package com.example.auth.endpoint;

import com.example.auth.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Autowired
    ConsumerTokenServices consumerTokenServices;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseBody
    public Result revokeToken(String token) {
        consumerTokenServices.revokeToken(token);
        return Result.successResponse();
    }
}
