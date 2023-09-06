package med.volt.api.domain.consulta.validaciones;

import med.volt.api.domain.consulta.DatosAgendarConsulta;
import med.volt.api.domain.paciente.PacienteRepository;
import med.volt.api.infra.errores.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;
    public void validar(DatosAgendarConsulta datos) {
        if(datos.idPaciente() == null)
            return;

        var paciente = pacienteRepository.findActivoById(datos.idPaciente());

        if(!paciente)
            throw new ValidacionExcepcion("El paciente no se encuentra activo");
    }

}
