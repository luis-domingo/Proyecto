package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.models.Stats;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsDAOImpl implements StatsDAO{

    private static StatsDAO instance;

    final static Logger logger = Logger.getLogger(StatsDAOImpl.class);

    private StatsDAOImpl() {
    }

    public static StatsDAO getInstance() {
        if (instance==null) instance = new StatsDAOImpl();
        return instance;
    }

    @Override
    public void setStats(String id, String games, String best, String last) throws SQLException {
        Session session = null;
        ResultSet res = null;
        Stats s = new Stats();
        s.setId(id);
        try {
            session = FactorySession.openSession();

            //We get the values of the games and the best to set the actual values
            res = (ResultSet)session.get(s);
            //Games
            String actualgames = res.getString(2);
            int g = Integer.parseInt(actualgames);
            g++;
            //Best
            String actualbest = res.getString(3);
            double bdatabase = Double.parseDouble(actualbest);
            double blastgame = Double.parseDouble(best);
            if(blastgame < bdatabase){
                bdatabase = blastgame;
            }

            //Set the new values
            s.setId(id);
            s.setGames(String.valueOf(g));
            s.setBest(String.valueOf(bdatabase));
            s.setLast(last);
            session.save(s);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }

    @Override
    public Stats getStats(String id) throws SQLException{
        Session session = null;
        ResultSet res = null;
        Stats s = new Stats();
        s.setId(id);
        try {
            session = FactorySession.openSession();

            //We get the values of the games and the best to set the actual values
            res = (ResultSet)session.get(s);

            //Set the values to return
            s.setGames(res.getString(2));
            s.setBest(res.getString(3));
            s.setLast(res.getString(4));
        }
        catch (IOException e) {
            logger.warn("Exception message: "  + e.getMessage());
        }
        finally {
            session.close();
        }
        return s;
    }
}
