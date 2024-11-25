package edu.miu.cse.vsms.service.impl;

import edu.miu.cse.vsms.dto.request.ServiceRequestDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.exception.ResourceNotFoundException;
import edu.miu.cse.vsms.model.VService;
import edu.miu.cse.vsms.repository.EmployeeRepository;
import edu.miu.cse.vsms.repository.VehicleServiceRepository;
import edu.miu.cse.vsms.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleServiceRepository vehicleServiceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public VehicleServiceResponseDto assignService(ServiceRequestDto request) {
        var employee = employeeRepository.findById(request.employeeId()).orElseThrow(() -> new ResourceNotFoundException("Cannot find associate employee with id=" + request.employeeId()));
        VService vService = new VService(request.serviceName(), request.cost(), request.vehicleType(), employee);
        var savedVService = vehicleServiceRepository.save(vService);
        employee.getVServices().add(savedVService);
        employeeRepository.saveAndFlush(employee);
        return mapToDto(savedVService);
    }

    private VehicleServiceResponseDto mapToDto(VService vService) {
        return new VehicleServiceResponseDto(vService.getId(), vService.getServiceName(), vService.getCost(), vService.getVehicleType());
    }
}
