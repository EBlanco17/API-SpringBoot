package med.volt.api.domain.consulta.validaciones;

import med.volt.api.domain.consulta.DatosAgendarConsulta;
import med.volt.api.domain.medico.MedicoRepository;
import med.volt.api.infra.errores.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;
    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null)
            return;

        var medico = medicoRepository.findActivoById(datos.idMedico());

        if(!medico)
            throw new ValidacionExcepcion("El medico no se encuentra activo");
    }
}
