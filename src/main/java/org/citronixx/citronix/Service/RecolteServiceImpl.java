package org.citronixx.citronix.Service;

import org.citronixx.citronix.Model.MapStruct.RecolteMapper;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.citronixx.citronix.Service.Interface.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private RecolteMapper recolteMapper;

    @Override
    public ResponseRecolteDTO addRecolte(RecolteDTO recolteDTO) {
       Recolte recolte = recolteMapper.recolteDTOToRecolte(recolteDTO);


        Recolte savedRecolte = recolteRepository.save(recolte);
        return recolteMapper.recolteToResponseRecolteDTO(savedRecolte);
    }

    @Override
    public ResponseRecolteDTO getRecolteById(Long recolteId) {
        Recolte recolte = recolteRepository.findById(recolteId)
                .orElseThrow(() -> new RuntimeException("Recolte not found with id: " + recolteId));
        return mapToResponseRecolteDTO(recolte);
    }

    @Override
    public List<ResponseRecolteDTO> getAllRecoltes() {
        List<Recolte> recoltes = recolteRepository.findAll();
        return recoltes.stream()
                .map(this::mapToResponseRecolteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseRecolteDTO updateRecolte(Long recolteId, RecolteDTO recolteDTO) {
        Recolte recolte = recolteRepository.findById(recolteId)
                .orElseThrow(() -> new RuntimeException("Recolte not found with id: " + recolteId));



        Recolte updatedRecolte = recolteRepository.save(recolte);
        return mapToResponseRecolteDTO(updatedRecolte);
    }

    @Override
    public String deleteRecolte(Long recolteId) {
        if (!recolteRepository.existsById(recolteId)) {
            throw new RuntimeException("Recolte not found with id: " + recolteId);
        }
        recolteRepository.deleteById(recolteId);
        return "Recolte deleted successfully!";
    }

    // Helper method to map Recolte to ResponseRecolteDTO
    private ResponseRecolteDTO mapToResponseRecolteDTO(Recolte recolte) {
        ResponseRecolteDTO response = new ResponseRecolteDTO();
        response.setId(recolte.getId());

        return response;
    }
}
