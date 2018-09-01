package com.megapapa.impulse;

import io.bootique.Bootique;

public class ImpulseApp {

    private static final String CONFIG_PARAMETER = "--config=classpath:com/megapapa/impulse/server-config.yml";
    private static final String SERVER_PARAMETER = "--server";

    public static void main(String[] args) {
        Bootique
                .app(CONFIG_PARAMETER, SERVER_PARAMETER)
                .autoLoadModules()
                .module(new ImpulseModuleProvider())
                .exec();
    }
}
