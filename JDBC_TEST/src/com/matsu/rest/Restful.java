package com.matsu.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("generic")
public class Restful {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
	public String testJson;

    /**
     * Default constructor. 
     */
    public Restful() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of Restful
     * @return an instance of String
     */
    @Path("test")
    @GET
    @Produces("application/json")
    public String getXml() {
        // TODO return proper representation object

//        throw new UnsupportedOperationException();
//         
         this.testJson = "{ test: 'aaa' }";
        return this.testJson;
    }

    /**
     * PUT method for updating or creating an instance of Restful
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

}