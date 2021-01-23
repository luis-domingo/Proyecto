package edu.upc.dsa.util;

import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectHelper {
    final static Logger logger = Logger.getLogger(ObjectHelper.class);

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
        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            if (field.toString().equals(property)) {
                try {
                    //Creamos el setter
                    Method setter = Class.forName(className).getDeclaredMethod(method, classType);
                    //Lo invocamos
                    setter.invoke(object, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object getter(Object object, String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object toReturn = null;
        //Cogemos el nombre de la clase del objeto
        Class className = object.getClass();
        //Cogemos el metodo con el estilo: getName
        logger.info(className.getName());
        String method = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
        logger.info(method);
        try{
            //Creamos el getter
            Method getter = className.getDeclaredMethod(method);
            //Lo invocamos
            toReturn = getter.invoke(object);

            logger.info(toReturn.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return toReturn;

    }
}







