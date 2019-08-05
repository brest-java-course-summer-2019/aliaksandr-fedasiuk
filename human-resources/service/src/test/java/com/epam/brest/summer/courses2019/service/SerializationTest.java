package com.epam.brest.summer.courses2019.service;

import org.junit.jupiter.api.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerializationTest {

    private static final String VALUE = "123456789";

    @Test
    void test() throws IOException, ClassNotFoundException {
        SerializableObject obj = create();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(out);
        stream.close();
        stream.writeObject(obj);

        byte[] bytes = out.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Object result = new ObjectInputStream(in).readObject();

        assertTrue(result instanceof SerializableObject);
        obj = (SerializableObject) result;
        assertEquals(VALUE, obj.getA());
    }

    @Test
    void testXml() {
        SerializableObject obj = create();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        encoder.writeObject(obj);
        encoder.close();

        byte[] bytes = out.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Object result = new XMLDecoder(in).readObject();

        assertTrue(result instanceof SerializableObject);
        obj = (SerializableObject) result;
        assertEquals(VALUE, obj.getA());
    }

    private SerializableObject create() {
        SerializableObject object = new SerializableObject();
        object.setA(VALUE);
        return object;
    }

    public static class SerializableObject implements Serializable {

        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }
}
