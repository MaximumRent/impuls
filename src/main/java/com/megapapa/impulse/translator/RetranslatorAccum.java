package com.megapapa.impulse.translator;

import java.util.LinkedList;
import java.util.List;

/**
 * Accumulator for translation options
 */
class RetranslatorAccum {

    private boolean includeAll;
    private List<String> includeFields;
    private List<String> excludeFields;

    public RetranslatorAccum() {
        includeFields = new LinkedList<>();
        excludeFields = new LinkedList<>();
    }

    public RetranslatorAccum includeAll() {
        includeAll = true;
        return this;
    }

    public RetranslatorAccum includeField(String fieldName) {
        includeFields.add(fieldName);
        return this;
    }

    public RetranslatorAccum excludeField(String fieldName) {
        excludeFields.add(fieldName);
        return this;
    }

}
