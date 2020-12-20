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


    public static void setter(Object object, String property, Object value) {
        //Cogemos el nombre de la clase del objeto
        String className = object.getClass().getName();
        //Cogemos el metodo con el estilo: setName
        String method = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
        //Cogemos la clase
        Class classType = value.getClass();

        try {
            //Creamos el setter
            Method setter = Class.forName(className).getDeclaredMethod(method,classType);
            //Lo invocamos
            setter.invoke(object, value);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getter(Object object, String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Method
        Object ret = null;
        Class theClass = object.getClass();
        //Methods of type getters are usually like getProperty while property is defined as property in the Object
        String sMethod = "get"+ property.substring(0,1).toUpperCase()+property.substring(1);
        Method getter = theClass.getMethod(sMethod);
        // Invoke
        ret = getter.invoke(object);
        return ret;

    }
}







