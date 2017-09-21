package com.home.binder;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * Created by amarendra on 20/09/17.
 * This feature is required to initialize the binders.
 */
@Provider
public class Hk2Feature implements Feature{
    @Override
    public boolean configure(final FeatureContext featureContext) {
        featureContext.register(new MyApplicationBinder());
        return true;
    }
}
