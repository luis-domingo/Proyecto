package edu.upc.dsa.services;

import edu.upc.dsa.dao.UsuarioDAO;
import edu.upc.dsa.dao.UsuarioDAOImpl;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
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
}
