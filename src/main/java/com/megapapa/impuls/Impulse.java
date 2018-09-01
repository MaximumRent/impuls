package com.megapapa.impuls;

import io.bootique.Bootique;

public class Impulse {

    public static void main(String[] args) {
        Bootique
                .app(args)
                .autoLoadModules()
                .exec()
                .exit();
    }
}
