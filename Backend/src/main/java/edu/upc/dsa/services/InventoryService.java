package edu.upc.dsa.services;


import edu.upc.dsa.dao.InvDAO;
import edu.upc.dsa.dao.InvDAOImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


@Api(value = "/inventory", description = "Endpoint to Track Service")
@Path("/inventory")
public class InventoryService {
    private InvDAO manInv;

    final static Logger logger = Logger.getLogger(RegisterService.class);

    public InventoryService() {
        this.manInv = InvDAOImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Get Inventory Items", notes = "")
    @ApiResponses(value = {
    })

    @Path("/listObjects")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getItems(String id) throws SQLException {
        List<UserItem> items = new LinkedList<UserItem>();
        items = manInv.getUserItems(id);
        GenericEntity<List<UserItem>> body = new GenericEntity<List<UserItem>>(items){};
        logger.info(body);
        if (body != null) {
            logger.info(Response.status(200).entity(body).build().toString());
            return Response.status(200).entity(body).build();
        } else {
            return Response.status(404).entity(null).build();
        }
    }
}
