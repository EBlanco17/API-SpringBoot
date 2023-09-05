package med.volt.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.volt.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(

        @NotBlank(message = "{nombre.obligatorio}")
        String nombre,
        @NotBlank(message = "{email.obligatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{phone.obligatorio}")
        String telefono,
        @NotBlank(message = "{nuip.obligatorio}")
        @Pattern(regexp = "\\d{5,10}", message = "{nuip.invalido}")
        String documento,
        @NotNull(message = "{especialidad.obligatorio}")
        Especialidad especialidad,
        @NotNull(message = "{address.obligatorio}")
        @Valid
        DatosDireccion direccion) {
}
