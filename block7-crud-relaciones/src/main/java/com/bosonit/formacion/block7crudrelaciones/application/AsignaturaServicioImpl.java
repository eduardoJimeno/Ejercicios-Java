package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Asignatura;
import com.bosonit.formacion.block7crudrelaciones.domain.Estudiante;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudrelaciones.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudrelaciones.repository.AsignaturaRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaServicioImpl implements AsignaturaServicio{
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto) {
        Asignatura asignatura = new Asignatura(asignaturaInputDto);

        // No asignamos estudiantes aquí, simplemente guardamos la asignatura
        asignatura = asignaturaRepository.save(asignatura);
        return asignatura.asignaturaToAsignaturaOutputDto();
    }

    @Override
    public AsignaturaOutputDto addEstudiantesToAsignatura(int asignatura_id, int estudiante_id) {
        Estudiante estudiante = estudianteRepository.findById(estudiante_id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + estudiante_id));
        Asignatura asignatura = asignaturaRepository.findById(asignatura_id)
                .orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrada con ID: " + asignatura_id));

        asignatura.getEstudiantes().add(estudiante);
        Asignatura asignaturaActualizada = asignaturaRepository.save(asignatura);
        return asignaturaActualizada.asignaturaToAsignaturaOutputDto();
    }

    @Override
    public AsignaturaOutputDto removeEstudiantesToAsignatura(int asignatura_id, int estudiante_id) {
        Estudiante estudiante = estudianteRepository.findById(estudiante_id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + estudiante_id));
        Asignatura asignatura = asignaturaRepository.findById(asignatura_id)
                .orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrada con ID: " + asignatura_id));

        asignatura.getEstudiantes().remove(estudiante);
        Asignatura asignaturaActualizada = asignaturaRepository.save(asignatura);
        return asignaturaActualizada.asignaturaToAsignaturaOutputDto();
    }

    @Override
    public AsignaturaOutputDto updateAsignatura(AsignaturaInputDto asignaturaInputDto) {
        asignaturaRepository.findById(asignaturaInputDto.getId_asignatura())
                .orElseThrow(()-> new EntityNotFoundException("No se encontró la asignatura con Id: "+asignaturaInputDto.getId_asignatura()));
        return asignaturaRepository.save(new Asignatura(asignaturaInputDto))
                .asignaturaToAsignaturaOutputDto();
    }

    @Override
    public void deleteAsignaturaById(int id_asignatura) {
        Asignatura asignatura = asignaturaRepository.findById(id_asignatura)
                .orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrada con ID: " + id_asignatura));

        if (!asignatura.getEstudiantes().isEmpty()) {
            throw new UnprocessableEntityException("No se puede eliminar la asignatura ya que tiene estudiantes asociados.");
        }

        asignaturaRepository.deleteById(id_asignatura);
    }

    @Override
    public List<AsignaturaOutputDto> getAsignaturasPorEstudiante(int estudiante_id) {
        Estudiante estudiante = estudianteRepository.findById(estudiante_id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + estudiante_id));

        return estudiante.getAsignaturas().stream()
                .map(Asignatura::asignaturaToAsignaturaOutputDto)
                .collect(Collectors.toList());
    }


}
