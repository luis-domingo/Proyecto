package edu.upc.dsa.services;

import edu.upc.dsa.dao.UsuarioDAO;
import edu.upc.dsa.dao.UsuarioDAOImpl;
import edu.upc.dsa.models.UserImg;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.sql.SQLException;
import java.util.Base64;

import org.apache.log4j.Logger;
import sun.jvm.hotspot.utilities.BitMap;


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
    public Response getPicture(String id){
        try{
            String image = id.substring(1, id.length()-1);
            DataInputStream dis = new DataInputStream(new FileInputStream("../userImages/" + image +".jpg"));
            byte[] imgByte = new byte[dis.available()];
            Base64.Encoder encoder = Base64.getEncoder();
            String imagenString = "'" + encoder.encodeToString(imgByte) + "'";
            return Response.status(200).entity(imagenString).build();
        } catch (IOException e) {

            e.printStackTrace();
            return Response.status(500).entity(null).build();
        }
    }
}
