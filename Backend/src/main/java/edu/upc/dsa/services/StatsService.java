package edu.upc.dsa.services;

import edu.upc.dsa.dao.StatsDAO;
import edu.upc.dsa.dao.StatsDAOImpl;
import edu.upc.dsa.models.ShopItem;
import edu.upc.dsa.models.Stats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/stats", description = "Endpoint to Shop Service")
@Path("/stats")
public class StatsService {
    private StatsDAO manStats;

    public StatsService() {
        this.manStats = StatsDAOImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get All Stats", notes = "")
    @ApiResponses(value = {
    })

    @Path("/getAllStats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() throws SQLException {
        List<Stats> listStats = manStats.getAllStats();
        GenericEntity<List<Stats>> body = new GenericEntity<List<Stats>>(listStats){};
        if (body != null) {
            return Response.status(200).entity(body).build();
        } else {
            return Response.status(404).entity(null).build();
        }
    }

}

