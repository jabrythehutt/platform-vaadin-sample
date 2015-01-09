/*
 * Copyright (c) 2015 Daniel Jabry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.djabry.platform.vaadin.config;

import com.github.djabry.platform.domain.api.SecurityToken;
import com.github.djabry.platform.service.api.DefaultSignUpRequest;
import com.github.djabry.platform.service.api.SpringAuthenticationService;
import com.github.djabry.platform.service.profile.Dev;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by djabry on 06/01/15.
 */
@Component
@Dev
@Log
public class StartupUsersSignup {

    @Autowired
    private SpringAuthenticationService springAuthenticationService;


    @PostConstruct
    public void createDummyUsers() {

        DefaultSignUpRequest request = new DefaultSignUpRequest();
        request.setEmail("john@example.com");
        request.setUsername("john.smith");
        request.setPassword("password");
        SecurityToken securityToken = springAuthenticationService.signUp(request);
        if (securityToken == null) {
            log.severe("Failed to sign up test users");
        } else {
            log.info("Successfully signed up as: username = " + securityToken.getUser().getUsername());
        }


    }
}
