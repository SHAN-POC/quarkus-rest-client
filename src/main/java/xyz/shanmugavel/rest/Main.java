package xyz.shanmugavel.rest;

import io.quarkus.logging.Log;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import xyz.shanmugavel.rest.client.EmployeeService;
import xyz.shanmugavel.rest.dto.EmployeeRecord;

@QuarkusMain
public class Main implements QuarkusApplication {

  @Inject
  @RestClient
  private EmployeeService employeeClient;

  @Override
  public int run(String... args) throws Exception {
    Log.info("Starting app....");
    Log.infof("RestClient = %s", employeeClient);
    List<EmployeeRecord> employees = employeeClient.findAllEmployees();
    employees.stream()
            .forEach(employeeInfo -> Log.infof("Employee=[%s]", employeeInfo));

    EmployeeRecord newEmployee = new EmployeeRecord(0L, "test name", "test role");
    newEmployee = employeeClient.createNewEmployee(newEmployee);
    Log.infof("Create enw employee Employee=[%s]", newEmployee );

    EmployeeRecord updatedEmployee = new EmployeeRecord(0L, "test upd name", "test upd role");
    updatedEmployee = employeeClient.updateEmployeeDetails(newEmployee.id(), updatedEmployee);
    Log.infof("Create enw employee Employee=[%s]", updatedEmployee);

    Log.infof("Find employee by id Employee=[%s]", employeeClient.findEmployeeById(updatedEmployee.id()));

    employeeClient.deleteEmployee(updatedEmployee.id());
    Log.infof("Delete employee by id [%d]", updatedEmployee.id());

    employees = employeeClient.findAllEmployees();
    employees.stream()
        .forEach(employeeInfo -> Log.infof("Employee=[%s]", employeeInfo));

    Log.info("Stopping app.....");
    return 0;
  }
}
