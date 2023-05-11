package xyz.shanmugavel.rest.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import xyz.shanmugavel.rest.dto.EmployeeRecord;

@Path("/employee")
@RegisterRestClient(configKey = "employee-service")
public interface EmployeeService {

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  EmployeeRecord findEmployeeById(@PathParam("id") long id);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<EmployeeRecord> findAllEmployees();

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  EmployeeRecord createNewEmployee(EmployeeRecord employeeRecord);

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  EmployeeRecord updateEmployeeDetails(@PathParam("id") long id, EmployeeRecord employeeRecord);

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  void deleteEmployee(@PathParam("id") long id);
}
