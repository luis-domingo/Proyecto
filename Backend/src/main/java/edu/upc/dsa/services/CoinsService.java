package edu.upc.dsa.services;

import edu.upc.dsa.dao.CoinsDAO;
import edu.upc.dsa.dao.CoinsDAOImpl;
import edu.upc.dsa.models.Coins;
import edu.upc.dsa.models.ForumPublication;
import edu.upc.dsa.models.Stats;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Api(value = "/coins", description = "Endpoint to Shop Service")
@Path("/coins")
public class CoinsService {

    private CoinsDAO manCoins;

    final static Logger logger = Logger.getLogger(CoinsService.class);

    public CoinsService(){
        this.manCoins = CoinsDAOImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Get Coins", notes = "")
    @ApiResponses(value = {
    })

    @Path("/getCoins")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoins(Usuario usuario) throws SQLException {
        int numCoins = -1;
        numCoins =this.manCoins.getCoins(usuario.getId());
        Coins coins = new Coins(usuario.getId(), String.valueOf(numCoins));
        logger.info("Las monedas de "+usuario.getId()+" son "+coins);

        if (numCoins != -1) {
            return Response.status(200).entity(coins).build();
        } else {
            return Response.status(404).entity(null).build();
        }
    }

    @PUT
    @ApiOperation(value = "Update Coins", notes = "")
    @ApiResponses(value = {
    })

    @Path("/updateCoins")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCoins(Coins coins) throws SQLException {
        try {
            manCoins.updateCoins(coins.getId(), coins.getCoins());
            return Response.status(200).build();
        } catch(SQLException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }





}
