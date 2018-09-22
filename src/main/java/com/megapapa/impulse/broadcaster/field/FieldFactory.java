package com.megapapa.impulse.broadcaster.field;

/**
 * Field has next structure: name OR name.innerField.andMore
 */
public class FieldFactory {

    private static final String FIELD_CONNECTOR = ".";
    public static final int SIMPLE_FIELD_LENGTH = 1;
    public static final int INITIAL_DEPTH = 0;
    public static final int FIRST_TOKEN = 0;

    public Field build(String fieldDefinition) {
        return build(fieldDefinition, INITIAL_DEPTH);
    }

    /**
     * Take field definition (e.g. name OR name.innerField.andMore) and return appropriate field
     * @param fieldDefinition
     * @return field by definition
     */
    public Field build(String fieldDefinition, int depth) {
        String[] tokens = fieldDefinition.split(FIELD_CONNECTOR);
        Field field = null;
        if (tokens.length == SIMPLE_FIELD_LENGTH) {
            field = new SimpleField();

        } else {
            field = parseComplexField(tokens, depth);
        }
        field.setName(tokens[FIRST_TOKEN]);
        return field;
    }

    private ComplexField parseComplexField(String[] tokens, int depth) {
        ComplexField complexField = new ComplexField();

    }
}
