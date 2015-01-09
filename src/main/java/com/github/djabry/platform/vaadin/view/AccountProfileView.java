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

package com.github.djabry.platform.vaadin.view;

import com.github.djabry.platform.domain.api.User;
import com.github.djabry.platform.service.api.AuthenticationService;
import com.github.djabry.platform.service.api.DomainServices;
import com.github.djabry.platform.service.security.annotations.ReadOwn;
import com.github.djabry.platform.vaadin.presenter.Sections;
import com.google.common.collect.Maps;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Field;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by djabry on 07/01/15.
 */

@UIScope
@VaadinView(name = AccountProfileView.VIEW_NAME)
@ReadOwn
@SideBarItem(order = 1, sectionId = Sections.ACCOUNT, caption = "Profile")
public class AccountProfileView extends BaseMainViewAbstr {

    public static final String VIEW_NAME = "profile";


    @Override
    public void clear() {

    }

    /**
     * This view is navigated to.
     * <p/>
     * This method is always called before the view is shown on screen.
     * {@link ViewChangeEvent#getParameters() event.getParameters()} may contain
     * extra parameters relevant to the view.
     *
     * @param event ViewChangeEvent representing the view change that is
     *              occurring. {@link ViewChangeEvent#getNewView()
     *              event.getNewView()} returns <code>this</code>.
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {


    }

    @PostConstruct
    public void initProfileView() {


        AuthenticationService authenticationService = DomainServices.getInstance().findService(AuthenticationService.class);
        User currentUser = authenticationService.getCurrentUser();
        BeanItem item = new BeanItem(currentUser);


        BeanFieldGroup form = new BeanFieldGroup(currentUser.getClass());
        form.setItemDataSource(item);
        //Collection<Object> propertyIds = form.getUnboundPropertyIds();
        //Iterator<Object> iterator = propertyIds.iterator();

        Map<String, Boolean> propertyIds = Maps.newLinkedHashMap();
        propertyIds.put("username", false);
        propertyIds.put("email", true);
        Iterator<Map.Entry<String, Boolean>> iterator = propertyIds.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, Boolean> next = iterator.next();
            Object propertyId = next.getKey();
            Field<?> field = form.buildAndBind(propertyId);
            field.setReadOnly(!next.getValue());
            this.addComponent(field);
            field.setWidth(30, Unit.REM);
            // this.setComponentAlignment(field, Alignment.TOP_LEFT);

        }
        
        
    }
}
