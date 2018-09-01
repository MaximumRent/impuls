package com.megapapa.impulse;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.jersey.JerseyModule;

public class ImpulseModule implements Module {


    public void configure(Binder binder) {
        //JerseyModule.extend(binder)
    }
}
