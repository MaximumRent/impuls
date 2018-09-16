package com.megapapa.impulse.broadcaster;

public class BroadcasterMemory {

    private static BroadcasterMemory instance = null;
    private static Object mutex = new Object();

    private BroadcasterMemory() {}

    // TODO: store list of broadcast configuration here!

    public static BroadcasterMemory getInstance() {
        BroadcasterMemory result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new BroadcasterMemory();
            }
        }
        return result;
    }
}
