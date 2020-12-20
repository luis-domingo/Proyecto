package edu.upc.dsa.util;

import edu.upc.dsa.dao.UsuarioDAOImpl;
import org.apache.log4j.Logger;

public class QueryHelper {

    final static Logger logger = Logger.getLogger(QueryHelper.class);

    public static String createQueryINSERT(Object entity) {

        //INSERT INTO [table name] (Column, Column) VALUES (?,?);
        logger.info("Preparando la sentencia");
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");
        //INSERT INTO Usuario (
        logger.info(sb.toString());
        String [] fields = ObjectHelper.getFields(entity);
        for (int i=1; i< fields.length; i++) {
            sb.append(fields[i]);
            sb.append(", ");
        }
        sb.append(fields[fields.length]);
        logger.info(sb.toString());
        sb.append(") VALUES (?");
        logger.info(sb.toString());
        //INSERT INTO Usuario (id, nombre, password) VALUES (?

        for (int i=1; i< fields.length; i++) {
            sb.append(", ?");
        }
        logger.info(sb.toString());
        sb.append(")");
        //INSERT INTO Usuario (id, nombre, password) VALUES (?, ?, ?)
        logger.info(sb.toString());
        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

}
