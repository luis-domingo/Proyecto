package edu.upc.dsa.util;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        //INSERT INTO [table name] (Column, Column) VALUES (?,?);

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");
        //INSERT INTO Usuario (

        String [] fields = ObjectHelper.getFields(entity);
        for (int i=0; i< fields.length-1; i++) {
            sb.append(fields[i]);
            sb.append(", ");
        }
        sb.append(fields[fields.length-1]);

        sb.append(") VALUES (?");
        //INSERT INTO Usuario (id, nombre, password) VALUES (?

        for (int i=0; i< fields.length-1; i++) {
            sb.append(", ?");
        }

        sb.append(")");
        //INSERT INTO Usuario (id, nombre, password) VALUES (?, ?, ?)

        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

}
