package edu.upc.dsa.util;

import edu.upc.dsa.models.Usuario;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;

    }


    public static void setter(Object object, String property, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Method // invoke
        Method met = object.getClass().getDeclaredMethod("setter",value.getClass());
        met.invoke(object, value);
    }

    public static Object getter(Object object, String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Method // invoke
        Method met = object.getClass().getDeclaredMethod("getter",null);
        Object result = met.invoke(object, null);
        return result;

    }
}







