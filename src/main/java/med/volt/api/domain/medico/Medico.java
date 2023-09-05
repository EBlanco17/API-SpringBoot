package med.volt.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.volt.api.domain.direccion.Direccion;
import org.springframework.web.bind.annotation.RequestBody;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String telefono;
    private String email;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
    }

    public void actualizarDatos(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null)
            this.nombre = datosActualizarMedico.nombre();
        if (datosActualizarMedico.email() != null)
            this.email = datosActualizarMedico.email();
        if (datosActualizarMedico.direccion() != null)
            this.direccion = direccion.actualizar(datosActualizarMedico.direccion());
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
