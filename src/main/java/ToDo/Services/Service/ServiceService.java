package ToDo.Services.Service;

import ToDo.Services.DTO.ServiceDTO;
import ToDo.Services.Entity.ServiceEntity;
import ToDo.Services.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

   @PostMapping(value = "/criando")
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        ServiceEntity service = toEntity(serviceDTO);
        ServiceEntity savedService = serviceRepository.save(service);
        return toDTO(savedService);
    }

    @GetMapping("/all")
    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> serviceDTOs = new ArrayList<>();
        List<ServiceEntity> services = serviceRepository.findAll();
        for (ServiceEntity service : services) {
            serviceDTOs.add(toDTO(service));
        }
        return serviceDTOs;
    }

    @GetMapping(value = "/{id}")
    public ServiceDTO getServiceById(Long id) {
        ServiceEntity service = serviceRepository.findById(id).orElse(null);
        if (service == null) {
            return null;
        }
        return toDTO(service);
    }

    @PutMapping(value = "/editando")
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        if (!serviceRepository.existsById(id)) {
            return null;
        }
        ServiceEntity service = toEntity(serviceDTO);
        service.setId(id);
        ServiceEntity updatedService = serviceRepository.save(service);
        return toDTO(updatedService);
    }

    @DeleteMapping(value = "/deletando")
    public boolean deleteService(Long id) {
        if (!serviceRepository.existsById(id)) {
            return false;
        }
        serviceRepository.deleteById(id);
        return true;
    }

    private ServiceDTO toDTO(ServiceEntity service) {
        ServiceDTO dto = new ServiceDTO();
        dto.setId(service.getId());
        dto.setNome(service.getNome());
        dto.setDescricao(service.getDescricao());
        dto.setPrecoBase(service.getPrecoBase());
        dto.setAtividades(service.getAtividades());
        dto.setCategoria(service.getCategoria());
        return dto;
    }

    private ServiceEntity toEntity(ServiceDTO serviceDTO) {
        ServiceEntity entity = new ServiceEntity();
        entity.setNome(serviceDTO.getNome());
        entity.setDescricao(serviceDTO.getDescricao());
        entity.setPrecoBase(serviceDTO.getPrecoBase());
        entity.setAtividades(serviceDTO.getAtividades());
        entity.setCategoria(serviceDTO.getCategoria());
        return entity;
    }
}
