package Traza1;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;

@Data
@Builder
@ToString(exclude = "empresa")
public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_casa_matriz;
    private Empresa empresa;
    private Domicilio domicilio;
}