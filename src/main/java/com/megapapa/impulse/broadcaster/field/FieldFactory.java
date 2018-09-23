package com.megapapa.impulse.broadcaster.field;

import java.util.ArrayList;
import java.util.List;

/**
 * Field has next structure: name OR name.innerField.andMore
 */
public class FieldFactory {

    private static final String FIELD_CONNECTOR = ".";
    public static final int SIMPLE_FIELD_LENGTH = 1;
    public static final int INITIAL_DEPTH = 0;
    public static final int FIRST_TOKEN = 0;

    public List<Field> build(List<String> fieldsDefintions) {
        return build(fieldsDefintions, INITIAL_DEPTH);
    }

    /**
     * Take field definition (e.g. name OR name.innerField.andMore) and return appropriate field
     * @param fieldsDefinitions
     * @return fields by definition
     */
    public List<Field> build(List<String> fieldsDefinitions, int depth) {
        List<Field> fields = new ArrayList<>();
        fieldsDefinitions.forEach(fieldDefinition -> {
            String[] tokens = fieldDefinition.split(FIELD_CONNECTOR);
            Field field = null;
            if (tokens.length == SIMPLE_FIELD_LENGTH) {
                field = new SimpleField();

            } else {
                field = parseComplexField(tokens, depth);
            }
            field.setName(tokens[FIRST_TOKEN]);
            fields.add(field);
        });
        return fields;
    }

    private ComplexField parseComplexField(String[] tokens, int depth) {
        ComplexField complexField = new ComplexField();
        int currentTokenNumber = INITIAL_DEPTH + depth;
        if (tokens.length > currentTokenNumber) {
            Field innerField = parseComplexField(tokens, ++depth);
            complexField.getFields().add(innerField);
        }
        complexField.setName(tokens[currentTokenNumber]);
//        for (int i = 0 + depth; i < tokens.length; i++) {
//
//        }
    }
}
