package med.volt.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.volt.api.domain.consulta.AgendaConsultaService;
import med.volt.api.domain.consulta.DatosAgendarConsulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaConsultaService agendaConsultaService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra una consulta en la base de datos",
            description = "",
            tags = { "consulta", "post" })
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
        var response = agendaConsultaService.agendar(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosAgendarConsulta datos) {
        agendaConsultaService.cancelar(datos);
        return ResponseEntity.ok().build();
    }


}
