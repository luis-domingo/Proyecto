package edu.upc.dsa.services;

import edu.upc.dsa.UsuarioManager;
import edu.upc.dsa.UsuarioManagerImpl;
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
import java.util.List;

@Api(value = "/usuarios", description = "Endpoint to Track Service")
@Path("/usuarios")
public class RegisterService {
    private UsuarioManager manager;

    public RegisterService() {
        this.manager = UsuarioManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Añadir usuario", notes = "")
    @ApiResponses(value = {
    })

    @Path("/NewUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(Usuario usuario) {
        this.manager.addUsuario(usuario.getNombre(), usuario.getPassword());
        return Response.status(201).entity(usuario).build();
    }

    @POST
    @ApiOperation(value = "Login", notes = "")
    @ApiResponses(value = {
    })

    @Path("/LogIn")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) {
        this.manager.getUsuario(usuario.getNombre(), usuario.getPassword());
        return Response.status(201).entity(usuario).build();
    }

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





/*




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
