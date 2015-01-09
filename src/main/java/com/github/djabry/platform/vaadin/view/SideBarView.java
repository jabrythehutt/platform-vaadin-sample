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
package com.github.djabry.platform.vaadin.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.SideBar;

import javax.annotation.PostConstruct;

/**
 * @author djabry
 */

@UIScope
@VaadinView(name = SideBarView.VIEW_NAME)
@SuppressWarnings("serial")
public class SideBarView extends VerticalLayout implements SampleView {

    public static final String VIEW_NAME = "sidebar";
    @Autowired
    private SideBar sideBar;

    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @PostConstruct
    public void init() {
        this.setSizeFull();

        HorizontalLayout titleHolder = new HorizontalLayout();
        titleHolder.addComponent(buildTitle());
        titleHolder.addStyleName(ValoTheme.LAYOUT_WELL);
        //titleHolder.setMargin(true);
        //titleHolder.setSpacing(true);
        //this.addComponent(titleHolder);
        VerticalLayout sidebarHolder = new VerticalLayout();
        //sidebarHolder.addStyleName(ValoTheme.LAYOUT_WELL);
        sidebarHolder.addStyleName(ValoTheme.MENU_ROOT);
        //sidebarHolder.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
        this.addComponent(sidebarHolder);
        sidebarHolder.setSizeFull();

        sidebarHolder.addComponent(sideBar);

        //sideBar.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
        sideBar.addStyleName(ValoTheme.MENU_PART);


        sideBar.setSizeFull();


    }

    public void setSidebarVisible(boolean visible) {
        this.sideBar.setVisible(visible);
    }


    private Component buildTitle() {

        Label t = new Label("CE");
        t.addStyleName(ValoTheme.LABEL_HUGE);
        t.addStyleName(ValoTheme.LABEL_COLORED);
        t.addStyleName(ValoTheme.LABEL_BOLD);

        //t.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
        //t.setWidth("100%");
        //t.addStyleName(ValoTheme.LABEL_BOLD);
        return t;
    }

    public void clear() {
        this.setSidebarVisible(false);
    }
}
