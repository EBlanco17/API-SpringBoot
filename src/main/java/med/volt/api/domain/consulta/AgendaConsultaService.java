package med.volt.api.domain.consulta;

import med.volt.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.volt.api.domain.medico.Medico;
import med.volt.api.domain.medico.MedicoRepository;
import med.volt.api.domain.paciente.PacienteRepository;
import med.volt.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos) {

        if(!pacienteRepository.findById(datos.idPaciente()).isPresent())
            throw new ValidacionDeIntegridad("Paciente no encontrado");

        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico()))
            throw new ValidacionDeIntegridad("Medico no encontrado");

        //validaciones
        validadores.forEach(v -> v.validar(datos));


        var medico = seleccionarMedico(datos);
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        if(medico == null)
            throw new ValidacionDeIntegridad("No hay medico disponible para ese horario y especialidad");

        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if (datos.idMedico() != null)
            return medicoRepository.findById(datos.idMedico()).get();
        if (datos.especialidad() == null)
            throw new ValidacionDeIntegridad("Especialidad no valida");

        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }

    public void cancelar(DatosAgendarConsulta datos) {
        if(!consultaRepository.existsById(datos.id()))
            throw new ValidacionDeIntegridad("Consulta no encontrada");

        consultaRepository.deleteById(datos.id());
    }
}
