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
package com.github.djabry.platform.vaadin.ui;


import com.github.djabry.platform.vaadin.presenter.Action;
import com.github.djabry.platform.vaadin.presenter.BannerPresenter;
import com.github.djabry.platform.vaadin.presenter.SideBarPresenter;
import com.github.djabry.platform.vaadin.view.BannerView;
import com.github.djabry.platform.vaadin.view.LoginView;
import com.github.djabry.platform.vaadin.view.SideBarView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinUI;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.SpringViewProvider;

import javax.annotation.PostConstruct;

/**
 * @author djabry
 */

@VaadinUI
@Theme("valo")
@SuppressWarnings("serial")
@PreserveOnRefresh
@Title("Test Dashboard")
//@Push
public class MainUI extends UI {

    @Autowired
    SpringViewProvider vP;

    @Autowired
    EventBus eventBus;

    @Autowired
    private BannerPresenter bannerPresenter;

    @Autowired
    private SideBarPresenter sideBarPresenter;


    private VerticalLayout body;

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setSizeFull();

        setContent(rootLayout);

        BannerView banner = bannerPresenter.getView();
        rootLayout.addComponent(banner);


        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();


        body = new VerticalLayout();
        body.setSizeFull();

        Navigator navigator = new Navigator(this, body);
        navigator.addProvider(vP);


        this.setNavigator(navigator);
        SideBarView sidebarView = sideBarPresenter.getView();

        mainLayout.addComponent(sidebarView);
        mainLayout.addComponent(body);

        rootLayout.addComponent(mainLayout);
        rootLayout.setExpandRatio(mainLayout, 10);

        sidebarView.setWidth(150, Unit.PIXELS);
        mainLayout.setExpandRatio(body, 10);
        //rootLayout.setSplitPosition(150, Unit.PIXELS);
        navigator.navigateTo(LoginView.VIEW_NAME);

        eventBus.publish(EventScope.SESSION, this, Action.START);
    }


    @PostConstruct
    public void initView() {


    }

}
