package med.volt.api.domain.consulta.validaciones;

import med.volt.api.domain.consulta.ConsultaRepository;
import med.volt.api.domain.consulta.DatosAgendarConsulta;
import med.volt.api.infra.errores.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);

        if(pacienteConConsulta)
            throw new ValidacionExcepcion("El paciente ya tiene una consulta agendada para ese dia");
    }
}
