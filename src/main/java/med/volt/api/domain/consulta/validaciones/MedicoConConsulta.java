package med.volt.api.domain.consulta.validaciones;

import med.volt.api.domain.consulta.ConsultaRepository;
import med.volt.api.domain.consulta.DatosAgendarConsulta;
import med.volt.api.infra.errores.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null)
            return;
        var medicoConConsulta = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());

        if(medicoConConsulta)
            throw new ValidacionExcepcion("El medico ya tiene una consulta agendada en esa fecha");
    }
}
