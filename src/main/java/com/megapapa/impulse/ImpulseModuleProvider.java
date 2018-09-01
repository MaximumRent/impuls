package com.megapapa.impulse;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class ImpulseModuleProvider implements BQModuleProvider {

    public Module module() {
        return new ImpulseModule();
    }

}
