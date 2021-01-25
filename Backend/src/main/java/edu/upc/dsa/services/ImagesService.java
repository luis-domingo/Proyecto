package edu.upc.dsa.services;

import edu.upc.dsa.dao.UsuarioDAO;
import edu.upc.dsa.dao.UsuarioDAOImpl;
import edu.upc.dsa.models.UserImg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Api(value = "/images", description = "Endpoint to User Service")
@Path("/images")

public class ImagesService {
    final static Logger logger = Logger.getLogger(edu.upc.dsa.services.RegisterService.class);

}
