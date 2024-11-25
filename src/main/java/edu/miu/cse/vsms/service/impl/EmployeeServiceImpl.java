package edu.miu.cse.vsms.service.impl;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.repository.EmployeeRepository;
import edu.miu.cse.vsms.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto request) {
        Employee newEmployee = new Employee(request.name(), request.email(), request.phone(), request.hireDate());
        newEmployee = employeeRepository.save(newEmployee);
        return mapToResponseDto(newEmployee);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        return mapToResponseDto(employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public EmployeeResponseDto partiallyUpdateEmployee(Long id, Map<String, Object> updates) {
        // Fetch the employee by ID or throw an exception if not found
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));

        // Apply each update based on the key
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    employee.setName(String.valueOf(value));
                    break;
                case "email":
                    employee.setEmail(String.valueOf(value));
                    break;
                case "phone":
                    employee.setPhone(String.valueOf(value));
                    break;
                case "hireDate":
                    employee.setHireDate((LocalDate) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return mapToResponseDto(employeeRepository.save(employee));
    }

    private EmployeeResponseDto mapToResponseDto(Employee employee) {
        List<VehicleServiceResponseDto> serviceDtos = employee.getVServices().stream()
                .map(service -> new VehicleServiceResponseDto(
                        service.getId(),
                        service.getServiceName(),
                        service.getCost(),
                        service.getVehicleType()
                )).toList();

        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getHireDate(),
                serviceDtos
        );
    }
}
