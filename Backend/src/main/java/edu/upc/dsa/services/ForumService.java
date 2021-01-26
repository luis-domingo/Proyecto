package edu.upc.dsa.services;

import edu.upc.dsa.dao.ForumDAO;
import edu.upc.dsa.dao.ForumDAOImpl;
import edu.upc.dsa.models.ForumPublication;
import edu.upc.dsa.models.ForumTopic;
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


@Api(value = "/forum", description = "Endpoint to Forum Service")
@Path("/forum")
public class ForumService {

    private ForumDAO manFor;

    final static Logger logger = Logger.getLogger(RegisterService.class);

    public ForumService() {
        this.manFor = ForumDAOImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Get Forum Topics", notes = "")
    @ApiResponses(value = {
    })

    @Path("/listTopics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopics() throws SQLException {
        List<ForumTopic> topics = new LinkedList<ForumTopic>();
        topics = manFor.getAllTopics();
        GenericEntity<List<ForumTopic>> body = new GenericEntity<List<ForumTopic>>(topics){};
        logger.info(body);
        if (body != null) {
            Response resp = Response.status(200).entity(body).build();
            return resp;
        } else {
            return Response.status(404).entity(null).build();
        }
    }

    @POST
    @ApiOperation(value = "Get Forum Publications of a Topic", notes = "")
    @ApiResponses(value = {
    })

    @Path("/listPublications")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPublications(ForumTopic forumTopic) throws SQLException {
        List<ForumPublication> topics = new LinkedList<ForumPublication>();
        topics = manFor.getAllPublications(forumTopic);
        GenericEntity<List<ForumPublication>> body = new GenericEntity<List<ForumPublication>>(topics){};
        logger.info(body);
        if (body != null) {
            Response resp = Response.status(200).entity(body).build();
            return resp;
        } else {
            return Response.status(404).entity(null).build();
        }
    }

    @PUT
    @ApiOperation(value = "Add Forum Publication to a Topic", notes = "")
    @ApiResponses(value = {
    })

    @Path("/addPublication")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPublication(ForumPublication forumPublication) throws SQLException {
        try {
            manFor.addPublication(forumPublication);
            return Response.status(200).build();
        } catch(SQLException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }


    @PUT
    @ApiOperation(value = "Add Forum Topic", notes = "")
    @ApiResponses(value = {
    })

    @Path("/addTopic")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTopic(ForumTopic forumTopic) throws SQLException {
        try {
            logger.info("Alguien esta añadiendo el siguiente tema a la lista " + forumTopic.toString());
            ForumTopic tp = new ForumTopic(forumTopic.getTitle(),forumTopic.getNumPublications());
            manFor.addTopic(tp);
            return Response.status(200).build();
        } catch(SQLException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}
