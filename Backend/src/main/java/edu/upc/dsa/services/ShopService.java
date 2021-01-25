package edu.upc.dsa.services;


import edu.upc.dsa.dao.ShopDAO;
import edu.upc.dsa.dao.ShopDAOImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


@Api(value = "/shop", description = "Endpoint to Shop Service")
@Path("/shop")
public class ShopService {
    private ShopDAO manshop;

    public ShopService() {
        this.manshop = ShopDAOImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get Shop Items", notes = "")
    @ApiResponses(value = {
    })

    @Path("/listObjects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() throws SQLException {
        List<ShopItem> items = new LinkedList<ShopItem>();
        items = manshop.getShopItems();
        GenericEntity<List<ShopItem>> body = new GenericEntity<List<ShopItem>>(items){};
        if (body != null) {
            return Response.status(200).entity(body).build();
        } else {
            return Response.status(404).entity(null).build();
        }
    }

}
