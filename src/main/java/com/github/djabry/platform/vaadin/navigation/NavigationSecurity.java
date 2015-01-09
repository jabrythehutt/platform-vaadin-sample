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

package com.github.djabry.platform.vaadin.navigation;

import com.github.djabry.platform.vaadin.presenter.Action;
import com.github.djabry.platform.vaadin.presenter.StartupFilter;
import com.google.common.collect.Sets;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import lombok.extern.java.Log;
import org.springframework.aop.support.AopUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.events.EventScope;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by djabry on 07/01/15.
 *
 * This class listens to view changes and stops navigation to any views that are not authorized
 *
 */

@UIScope
@VaadinComponent
@Log
public class NavigationSecurity {

    @EventBusListenerMethod(scope = EventScope.UI, filter = StartupFilter.class)
    public void registerSecurityListener(Action action) {

        Navigator navigator = UI.getCurrent().getNavigator();
        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                View newView = event.getNewView();


                if (!isSecuredObject(newView)) {
                    //return true;
                }

                try {
                    newView.enter(event);
                    return true;

                } catch (org.springframework.security.access.AccessDeniedException accessDeniedException) {
                    log.warning("Attempted to navigate to page without necessary authorization: " + event.getViewName());
                    //throw accessDeniedException;
                }

                return false;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });

        log.info("Registed navigator security listener");

    }

    private boolean isSecuredObject(Object o) {

        return this.isSecuredClass(AopUtils.getTargetClass(o));


    }

    private boolean isSecuredClass(Class oClass) {
        Set<Class<? extends Annotation>> annotationsToCheck = Sets.newHashSet(Secured.class, PreAuthorize.class);
        for (Class<? extends Annotation> annotation : annotationsToCheck) {
            if (oClass.getAnnotation(annotation) != null) {
                return true;
            }
        }

        return false;
    }

}
