package com.home.binder;

import com.home.service.DataService;
import com.home.service.PersonService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * Created by amarendra on 20/09/17.
 */
public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(PersonService.class).to(DataService.class).in(Singleton.class);
    }
}
