package edu.upc.dsa.services;

import edu.upc.dsa.dao.UsuarioDAO;
import edu.upc.dsa.dao.UsuarioDAOImpl;
import edu.upc.dsa.models.UserImg;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.sql.SQLException;
import java.util.Base64;

import org.apache.log4j.Logger;


@Api(value = "/usuarios", description = "Endpoint to User Service")
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

    @POST
    @ApiOperation(value = "Set Image", notes = "")
    @ApiResponses(value = {
    })

    @Path("/setImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setPicture(UserImg image) throws IOException {
        try {
            logger.info("El usuario que quiere registrar su foto tiene ID " + image.getName());
            String imatge = image.getImage().replace("\n", "");
            logger.info(imatge);
            Base64.Decoder decoder = Base64.getDecoder();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decoder.decode(imatge));
            ImageIO.write(ImageIO.read(byteArrayInputStream), "jpg", new File("../userImages/" + image.getName() + ".jpg"));
        }catch(Throwable t){
            t.printStackTrace();
        }
        return Response.status(200).build();
    }
    @POST
    @ApiOperation(value = "Set Image", notes="")
    @ApiResponses(value={
    })

    @Path("/getImage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPicture(UserImg image){
        try{
            String imagenNombre = image.getName();
            DataInputStream dis = new DataInputStream(new FileInputStream("../userImages/" + imagenNombre +".jpg"));
            logger.info("El usuario con ID " + imagenNombre + " está intentando ver su imagen");
            byte[] imgByte = dis.readAllBytes();
            Base64.Encoder encoder = Base64.getEncoder();
            String imagenString = encoder.encodeToString(imgByte);
            logger.info(imagenString);
            image.setImage(imagenString);
            return Response.status(200).entity(image).build();
        } catch (IOException e) {

            e.printStackTrace();
            return Response.status(500).entity(null).build();
        }
    }
}
