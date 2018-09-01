package com.megapapa.impulse;

import io.bootique.Bootique;

public class Impulse {

    public static void main(String[] args) {
        Bootique
                .app(args)
                .autoLoadModules()
                .module(new ImpulseModuleProvider())
                .exec()
                .exit();
    }
}
