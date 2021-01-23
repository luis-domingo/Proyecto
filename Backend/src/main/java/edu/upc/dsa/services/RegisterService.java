package edu.upc.dsa.services;

import edu.upc.dsa.dao.UsuarioDAO;
import edu.upc.dsa.dao.UsuarioDAOImpl;
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
import java.util.List;
import org.apache.log4j.Logger;


@Api(value = "/usuarios", description = "Endpoint to Track Service")
@Path("/usuarios")
public class RegisterService {
    private UsuarioDAO manuser;

    public RegisterService() {
        this.manuser = UsuarioDAOImpl.getInstance();
    }
    final static Logger logger = Logger.getLogger(RegisterService.class);

    @POST
    @ApiOperation(value = "Añadir usuario", notes = "")
    @ApiResponses(value = {
    })

    @Path("/newuser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(Usuario usuario) throws SQLException {
        this.manuser.addUsuario(usuario.getNombre(), usuario.getPassword());
        return Response.status(200).entity(usuario).build();
    }

    @POST
    @ApiOperation(value = "Login", notes = "")
    @ApiResponses(value = {
    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) throws SQLException {
        Usuario u = this.manuser.getUsuario(usuario.getNombre(), usuario.getPassword());
        logger.info("El usuario que envio es " + u.toString());
        if (u!=null) {
            return Response.status(200).entity(u).build();
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
