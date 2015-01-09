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
package com.github.djabry.platform.vaadin.presenter;

import com.github.djabry.platform.vaadin.account.AccountEvent;
import com.github.djabry.platform.vaadin.view.SideBarView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.vaadin.spring.navigator.VaadinPresenter;
import org.vaadin.spring.security.Security;

import javax.annotation.PostConstruct;

/**
 * @author djabry
 */

@VaadinPresenter(viewName = SideBarView.VIEW_NAME)
@SuppressWarnings("serial")
public class SideBarPresenter extends SamplePresenter<SideBarView> {

    @Autowired
    private Security security;

    @PostConstruct
    public void initView() {
        Authentication authentication = security.getAuthentication();
        boolean authenticated = false;
        if (authentication != null) {
            authenticated = authentication.isAuthenticated();
        }

        //getView().setSidebarVisible(authenticated);
        getView().setSidebarVisible(false);
    }


    //@EventBusListenerMethod(filter=AccountEventFilter.class)
    public void doOnAccountEvent(AccountEvent event) {
        super.doOnAccountEvent(event);
        getView().setSidebarVisible(event.isAuthenticated());

    }

}
