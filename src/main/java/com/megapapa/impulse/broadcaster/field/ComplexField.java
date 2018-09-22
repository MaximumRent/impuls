package com.megapapa.impulse.broadcaster.field;

import java.util.ArrayList;
import java.util.List;

public class ComplexField extends Field {

    private List<Field> fields;

    public ComplexField() {
        fields = new ArrayList<>();
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
