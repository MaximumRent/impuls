package com.megapapa.impulse.translator;

import com.megapapa.impulse.translator.exception.TranslationException;
import org.apache.cayenne.BaseDataObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Retranslator {

    private static final String GETTER_PREFIX = "get";
    private static final int FIELD_FIRST_LETTER_INDEX = 0;

    public RetranslatorAccum startTranslation() {
        RetranslatorAccum accum = new RetranslatorAccum();
        return accum;
    }

    public RetranslatorAccum includeAll() {

    }

    public Map<String, Object> translate(BaseDataObject cayenneObject) {
        Class cayenneClass = cayenneObject.getClass().getSuperclass();
        Field[] fields = cayenneClass.getDeclaredFields();
        Map<String, Object> jsonMap = new HashMap<>();
        for (Field field : fields) {
            if (!Modifier.isFinal(field.getModifiers())) {
                try {
                    String fieldName = field.getName();
                    Object fieldValue = getValueFromField(fieldName, cayenneObject);
                    if (fieldValue instanceof BaseDataObject) {
                        fieldValue = translate((BaseDataObject) fieldValue);
                    }
                    jsonMap.put(fieldName, fieldValue);
                } catch (Exception exception) {
                    throw new TranslationException(exception);
                }
            }
        }
        return jsonMap;
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
}
