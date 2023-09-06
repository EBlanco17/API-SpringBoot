package med.volt.api.domain.consulta.validaciones;

import med.volt.api.domain.consulta.DatosAgendarConsulta;
import med.volt.api.infra.errores.ValidacionExcepcion;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{
    public void validar(DatosAgendarConsulta datos){
        var fechaActual = LocalDateTime.now();
        var fechaConsulta = datos.fecha();

        var diferencia = Duration.between(fechaActual, fechaConsulta).toMinutes() < 30;

        if(diferencia)
            throw new ValidacionExcepcion("La consulta debe agendarse con al menos 30 minutos de anticipación");
    }
}
