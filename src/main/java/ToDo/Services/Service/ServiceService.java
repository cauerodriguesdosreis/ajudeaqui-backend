package ToDo.Services.Service;

import ToDo.Services.DTO.ServiceDTO;
import ToDo.Services.Entity.ServiceEntity;
import ToDo.Services.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    // CREATE
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        ServiceEntity service = toEntity(serviceDTO);
        ServiceEntity savedService = serviceRepository.save(service);
        return toDTO(savedService);
    }

    // READ ALL
    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public ServiceDTO getServiceById(Long id) {
        Optional<ServiceEntity> service = serviceRepository.findById(id);
        return service.map(this::toDTO).orElse(null);
    }

    // UPDATE
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        if (!serviceRepository.existsById(id)) {
            return null;
        }
        ServiceEntity service = toEntity(serviceDTO);
        service.setId(id);
        ServiceEntity updatedService = serviceRepository.save(service);
        return toDTO(updatedService);
    }

    // DELETE
    public boolean deleteService(Long id) {
        if (!serviceRepository.existsById(id)) {
            return false;
        }
        serviceRepository.deleteById(id);
        return true;
    }

    // Converter Entity para DTO
    private ServiceDTO toDTO(ServiceEntity service) {
        return new ServiceDTO(
                service.getId(),
                service.getNome(),
                service.getDescricao(),
                service.getPrecoBase(),
                service.getAtividades(),
                service.getCategoria()
        );
    }

    // Converter DTO para Entity
    private ServiceEntity toEntity(ServiceDTO serviceDTO) {
        return new ServiceEntity(
                serviceDTO.getNome(),
                serviceDTO.getDescricao(),
                serviceDTO.getPrecoBase(),
                serviceDTO.getAtividades(),
                serviceDTO.getCategoria()
        );
    }
}
