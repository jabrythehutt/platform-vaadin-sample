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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.djabry.platform.vaadin.account;


import com.github.djabry.platform.vaadin.view.HomeView;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.security.Security;

/**
 * @author djabry
 */

@VaadinComponent
@UIScope
public class LoginAction {

    @Autowired
    private Security security;

    @Autowired
    private EventBus eventBus;

    public void login(String username, String password) {
        security.login(username, password);

        AccountEvent accountEvent = new AccountEvent();

        Authentication authentication = security.getAuthentication();
        if (authentication != null) {
            accountEvent.setAuthenticated(authentication.isAuthenticated());
        }


        UI.getCurrent().getNavigator().navigateTo(HomeView.VIEW_NAME);
        eventBus.publish(EventScope.SESSION, this, accountEvent);

    }


}
