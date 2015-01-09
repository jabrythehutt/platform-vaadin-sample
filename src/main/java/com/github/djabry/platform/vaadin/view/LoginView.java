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


import com.github.djabry.platform.vaadin.account.LoginAction;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.vaadin.spring.navigator.VaadinView;

import javax.annotation.PostConstruct;

/**
 * @author djabry
 */

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(name = LoginView.VIEW_NAME)
@SuppressWarnings("serial")
public class LoginView extends BaseMainViewAbstr {

    public static final String VIEW_NAME = "login";
    private Component loginForm;
    private PasswordField passwordField;
    private TextField userField;
    @Autowired
    private LoginAction loginAction;

    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @PostConstruct
    public void initView() {

        loginForm = this.buildLoginForm();
        this.addComponent(loginForm);
        this.setComponentAlignment(loginForm, Alignment.TOP_LEFT);
    }

    private Component buildLoginForm() {
        HorizontalLayout loginPanel = new HorizontalLayout();
        //loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);

        //loginPanel.addStyleName("login-panel");

        //loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        //loginPanel.addComponent(new CheckBox("Remember me", false));
        return loginPanel;
    }

    private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);


        userField = new TextField("Username");

        userField.setIcon(FontAwesome.USER);
        userField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        //username.addStyleName(ValoTheme.TEXTFIELD_TINY);

        passwordField = new PasswordField("Password");
        passwordField.setIcon(FontAwesome.LOCK);
        passwordField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        //password.addStyleName(ValoTheme.TEXTFIELD_TINY);

        final Button signin = new Button("Sign In");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        //signin.addStyleName(ValoTheme.BUTTON_TINY);
        signin.focus();

        fields.addComponents(userField, passwordField, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                loginAction.login(userField.getValue(), passwordField.getValue());
            }
        });
        return fields;
    }

    @Override
    public void clear() {
        this.passwordField.setValue("");
        this.userField.setValue("");
    }

}
