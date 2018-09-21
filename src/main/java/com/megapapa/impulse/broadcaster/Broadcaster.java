package com.megapapa.impulse.broadcaster;

import com.megapapa.impulse.broadcaster.exception.TranslationException;
import org.apache.cayenne.BaseDataObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which provides logic about translation between cayenne-objects and DTO's.
 */
public class Broadcaster {

    private static final String GETTER_PREFIX = "get";
    private static final int FIELD_FIRST_LETTER_INDEX = 0;

    private BroadcastConfiguration config;


    public Broadcaster() {
        config = new BroadcastConfiguration();
    }

    public Map<String, Object> translate(BaseDataObject cayenneObject, int depth) {
        Class cayenneClass = cayenneObject.getClass().getSuperclass();
        Field[] fields = cayenneClass.getDeclaredFields();
        Map<String, Object> jsonMap = new HashMap<>();
        for (Field field : fields) {
            if (!Modifier.isFinal(field.getModifiers())) {
                try {
                    String fieldName = field.getName();
                    if (matched(fieldName)) {
                        Object fieldValue = getFieldValue(fieldName, cayenneObject, depth);
                        jsonMap.put(fieldName, fieldValue);
                    }
                } catch (Exception exception) {
                    throw new TranslationException(exception);
                }
            }
        }
        return jsonMap;
    }

    private Object getFieldValue(String fieldName, BaseDataObject cayenneObject, int depth)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object fieldValue = getValueFromField(fieldName, cayenneObject);
        if (depth >= config.getMaxDepth()) {
            if (fieldValue instanceof BaseDataObject) {
                fieldValue = translate((BaseDataObject) fieldValue, ++depth);
            }
        }
        return fieldValue;
    }

    private boolean matched(String fieldName) {
        boolean matched = false;
        if (config.isIncludeAll()) {
            return true;
        }
        for (String includeField : config.getIncludedFields()) {
            if (fieldName.equals(includeField)) {
                matched = true;
            }
        }
        for (String excludeField : config.getExcludedFields()) {
            if (fieldName.equals(excludeField)) {
                matched = false;
            }
        }
        return matched;
    }

    private Object getValueFromField(String fieldName, BaseDataObject cayenneObject) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        String getterName = getGetterName(fieldName);
        Method getter = cayenneObject.getClass().getSuperclass().getDeclaredMethod(getterName);
        return getter.invoke(cayenneObject);
    }

    private static String getGetterName(String fieldName) {
        StringBuilder builder = new StringBuilder();
        builder.append(GETTER_PREFIX)
                .append(Character.toUpperCase(fieldName.charAt(FIELD_FIRST_LETTER_INDEX)));
        for (int i = 1; i < fieldName.length(); i++) {
            builder.append(fieldName.charAt(i));
        }
        return builder.toString();
    }


    // "Self configurable" methods
    public Broadcaster maxDepth(int maxDepth) {
        config.setMaxDepth(maxDepth);
        return this;
    }

    public Broadcaster includeAll() {
        config.includeAll();
        return this;
    }

    public Broadcaster include(String fieldName) {
        config.include(fieldName);
        return this;
    }

    public Broadcaster exclude(String fieldName) {
        config.exclude(fieldName);
        return this;
    }
}
