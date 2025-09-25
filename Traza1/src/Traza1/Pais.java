package Traza1;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@ToString
public class Pais {
    private Long id;
    private String nombre;
    private Set<Pais> paises = new HashSet<>();
}
