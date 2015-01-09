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


import com.github.djabry.platform.service.security.annotations.ReadOwn;
import com.github.djabry.platform.vaadin.view.HomeView;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * @author djabry
 */
@VaadinPresenter(viewName = HomeView.VIEW_NAME)
@SuppressWarnings("serial")
@ReadOwn
public class HomePresenter extends SamplePresenter<HomeView> {

    @EventBusListenerMethod(scope = EventScope.SESSION, filter = StartupFilter.class)
    public void onStartup(Event<Action> event) {

//        
//        getView().setBanner(banner.getView());
//        getView().setHeader(header.getView());
//        getView().setBody(body.getView());
//        getView().setFooter((Component) getViewProvider().getView(FooterView.NAME));
    }

}
