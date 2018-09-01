package com.megapapa.impulse;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.megapapa.impulse.controller.MainController;
import io.bootique.jersey.JerseyModule;

public class ImpulseModule implements Module {


    public void configure(Binder binder) {
        JerseyModule.extend(binder).addResource(MainController.class);
    }
}
