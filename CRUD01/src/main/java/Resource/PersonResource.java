/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resource;

import DAO.PersonDAO;
import model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/people")
public class PersonResource {

    private final PersonDAO personDAO = new PersonDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPeople() {
        List<Person> people = personDAO.getAllPeople();
        return Response.ok(people).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("id") int id) {
        Person person = personDAO.getPersonById(id);
        if (person != null) {
            return Response.ok(person).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Person with ID " + id + " not found")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        personDAO.addPerson(person);
        return Response.status(Response.Status.CREATED)
                .entity("Person added successfully")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") int id, Person updatedPerson) {
        Person existingPerson = personDAO.getPersonById(id);
        if (existingPerson != null) {
            updatedPerson.setId(id); // Ensure the updated person has the correct ID
            personDAO.updatePerson(updatedPerson);
            return Response.ok("Person with ID " + id + " updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Person with ID " + id + " not found")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        personDAO.deletePerson(id);
        return Response.ok("Person with ID " + id + " deleted successfully").build();
    }
}

