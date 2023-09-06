package med.volt.api.domain.consulta.validaciones;

import med.volt.api.domain.consulta.DatosAgendarConsulta;
import med.volt.api.infra.errores.ValidacionExcepcion;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamiento implements ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos) {

        var domigo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());

        var horaAntesApertura = datos.fecha().getHour() < 7;
        var horaDespuesCierre = datos.fecha().getHour() > 19;

        if(domigo || horaAntesApertura || horaDespuesCierre)
            throw new ValidacionExcepcion("El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas;");
    }
}
