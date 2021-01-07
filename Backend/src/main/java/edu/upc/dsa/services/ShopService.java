package edu.upc.dsa.services;


import edu.upc.dsa.dao.ShopDAO;
import edu.upc.dsa.dao.ShopDAOImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/shop", description = "Endpoint to Track Service")
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
        List<ShopItem> list = this.manshop.getShopItems();
        if (list != null) {
            return Response.status(200).entity(list).build();
        } else {
            return Response.status(404).entity(null).build();
        }
    }
/*
    @POST
    @ApiOperation(value = "Login", notes = "")
    @ApiResponses(value = {
    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) throws SQLException {
        //Usuario u = this.manager.getUsuario(usuario.getNombre(), usuario.getPassword());
        Usuario u = this.manuser.getUsuario(usuario.getNombre(), usuario.getPassword());

        if (u!=null) {
            return Response.status(200).entity(usuario).build();
        } else {
            return Response.status(404).entity(null).build();
        }

    }
/*
    @DELETE
    @ApiOperation(value = "Borrar usuario", notes = "")
    @ApiResponses(value = {
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Usuario u =  this.manager.getUsuario(id);
        if(u==null){
            return Response.status(404).build();
        }
        else{
            this.manager.eliminarUsuario(id);
            return Response.status(201).build();
        }
    }










    @GET
    @ApiOperation(value = "get all Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks() {

        List<Track> tracks = this.tm.findAll();

        GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(tracks) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteTrack(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.tm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }



*/




}
