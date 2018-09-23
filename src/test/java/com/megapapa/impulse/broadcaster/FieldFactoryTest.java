package com.megapapa.impulse.broadcaster;

import com.megapapa.impulse.broadcaster.field.Field;
import com.megapapa.impulse.broadcaster.field.FieldFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldFactoryTest {

    @Test
    public void testFactory() {
        List<String> fieldsDefinitions = new ArrayList<>();
        Collections.addAll(fieldsDefinitions, "a", "b", "c.a", "d.e.f");
        FieldFactory factory = new FieldFactory();
        List<Field> fields = factory.build(fieldsDefinitions);
        Assert.assertNotNull(fields);
        Assert.assertEquals(fieldsDefinitions.size(), fields.size());
    }
}
