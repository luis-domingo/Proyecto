package edu.upc.dsa.services;


import edu.upc.dsa.dao.InvDAO;
import edu.upc.dsa.dao.InvDAOImpl;
import edu.upc.dsa.dao.ShopDAOImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

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

    public InventoryService() {
        this.manInv = InvDAOImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get Inventory Items", notes = "")
    @ApiResponses(value = {
    })

    @Path("/listObjects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() throws SQLException {
        List<UserItem> items = new LinkedList<UserItem>();
        items = manInv.getUserItems();
        GenericEntity<List<UserItem>> body = new GenericEntity<List<UserItem>>(items){};
        if (body != null) {
            return Response.status(200).entity(body).build();
        } else {
            return Response.status(404).entity(null).build();
        }
    }
}
