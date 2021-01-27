package edu.upc.dsa.services;

import edu.upc.dsa.dao.ForumDAO;
import edu.upc.dsa.dao.ForumDAOImpl;
import edu.upc.dsa.dao.MapsDAO;
import edu.upc.dsa.dao.MapsDAOImpl;
import edu.upc.dsa.models.ForumPublication;
import edu.upc.dsa.models.ForumTopic;
import edu.upc.dsa.models.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


@Api(value = "/maps", description = "Endpoint to Maps Service")
@Path("/maps")
public class MapsService {

    private MapsDAO manMaps;

    final static Logger logger = Logger.getLogger(MapsService.class);

    public MapsService() {
        this.manMaps = MapsDAOImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Get a Map", notes = "")
    @ApiResponses(value = {
    })

    @Path("/getMap")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPublications(Map map) throws SQLException {
        Map receivedMap = new Map(map.getName());
        receivedMap = manMaps.getMap(receivedMap.getName());
        logger.info(receivedMap);
        if (receivedMap != null) {
            Response resp = Response.status(200).entity(receivedMap).build();
            return resp;
        } else {
            return Response.status(404).entity(null).build();
        }
    }

}
