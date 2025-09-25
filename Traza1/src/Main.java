import Traza1.Empresa;
import Traza1.Pais;
import Traza1.Provincia;
import Traza1.Localidad;
import Traza1.Domicilio;
import Traza1.Sucursal;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Empresa> empresaInMemoryRepository = new InMemoryRepository<>();

        Pais pais1 = Pais.builder().nombre("Argentina").build();

        Provincia prov1 = Provincia.builder().id(1L).nombre("Buenos Aires").pais(pais1).build();

        Localidad local1 = Localidad.builder().id(1L).nombre("CABA").provincia(prov1).build();
        Domicilio dom1 = Domicilio.builder().id(1L).calle("Av. Cordoba").numero(4100).cp(1870).localidad(local1).build();

        Localidad local2 = Localidad.builder().id(2L).nombre("La Plata").provincia(prov1).build();
        Domicilio dom2 = Domicilio.builder().id(2L).calle("Av. 1").numero(1150).cp(1900).localidad(local2).build();

        Provincia prov2 = Provincia.builder().id(2L).nombre("Cordoba").pais(pais1).build();

        Localidad local3 = Localidad.builder().id(3L).nombre("Cordoba Capital").provincia(prov2).build();
        Domicilio dom3 = Domicilio.builder().id(3L).calle("Jujuy").numero(2602).cp(5000).localidad(local3).build();

        Localidad local4 = Localidad.builder().id(4L).nombre("Villa Carlos Paz").provincia(prov2).build();
        Domicilio dom4 = Domicilio.builder().id(4L).calle("San Martin").numero(500).cp(5152).localidad(local4).build();

        Sucursal s1= Sucursal.builder().id(1L).nombre("Sucursal 1").horarioApertura(LocalTime.of(10,0)).horarioCierre(LocalTime.of(23,0)).es_casa_matriz(true).domicilio(dom1).build();
        Sucursal s2= Sucursal.builder().id(2L).nombre("Sucursal 2").horarioApertura(LocalTime.of(15,0)).horarioCierre(LocalTime.of(20,0)).es_casa_matriz(false).domicilio(dom2).build();
        Sucursal s3= Sucursal.builder().id(3L).nombre("Sucursal 3").horarioApertura(LocalTime.of(9,0)).horarioCierre(LocalTime.of(17,0)).es_casa_matriz(true).domicilio(dom3).build();
        Sucursal s4= Sucursal.builder().id(4L).nombre("Sucursal 4").horarioApertura(LocalTime.of(17,0)).horarioCierre(LocalTime.of(23,30)).es_casa_matriz(false).domicilio(dom4).build();

        Empresa emp1=Empresa.builder().id(1L).nombre("Empresa 1").cuit(345858575).razonSocial("Empresa 1 SA").logo("logo1.jpg").sucursales(new HashSet<>(Set.of(s1,s2))).build();
        Empresa emp2=Empresa.builder().id(2L).nombre("Empresa 2").cuit(123456789).razonSocial("Empresa 2 SRL").logo("logo2.jpg").sucursales(new HashSet<>(Set.of(s3,s4))).build();

        s1.setEmpresa(emp1);
        s2.setEmpresa(emp1);
        s3.setEmpresa(emp2);
        s4.setEmpresa(emp2);

        empresaInMemoryRepository.save(emp1);
        empresaInMemoryRepository.save(emp2);

        //Resultados
        // Mostrar empresas
        List<Empresa> listarEmpresas = empresaInMemoryRepository.findAll();
        listarEmpresas.forEach(System.out::println);

        //Buscar Empresa por ID
        Optional<Empresa> buscarEmpresaID = empresaInMemoryRepository.findById(1L);
        buscarEmpresaID.ifPresent(empresa -> System.out.println("Empresa con ID 1 es: "+empresa));

        //Buscar Empresa por Nombre
        List<Empresa> buscarEmpresaNombre = empresaInMemoryRepository.genericFindByField("nombre","Empresa 1");
        System.out.println("Empresas con nombre 'Empresa 1':");
        buscarEmpresaNombre.forEach(System.out::println);

        //Actualizar Buscando por ID
        Optional<Empresa> actualizarCuit = empresaInMemoryRepository.findById(2L);
        actualizarCuit.ifPresent(empresa -> empresa.setCuit(987654321));

        //Eliminar Empresa por ID
        Optional<Empresa> eliminarEmpresa = empresaInMemoryRepository.findById(1L);
        eliminarEmpresa.ifPresent(empresa -> empresaInMemoryRepository.genericDelete(1L));

    }}