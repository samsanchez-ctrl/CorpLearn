package com.corplearn.curso.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.corplearn.curso.model.Curso;
import com.corplearn.curso.repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @Mock
    private CursoRepository repository; //Simulo la base de datos

    @InjectMocks
    private CursoService service; //Inyecta el repositorio simulado dentro del servicio

    private Curso cursoMuestra;

    @BeforeEach
    void setUp() {
        //Configuro un curso de prueba antes de cada test
        cursoMuestra = new Curso();
        cursoMuestra.setId(1L);
        cursoMuestra.setTitulo("Capacitacion Java FullStack");
        cursoMuestra.setDescripcion("Curso para nivelar competencias laborales de los empleados de una empresa");
        cursoMuestra.setDuracion("4 Semanas");
        cursoMuestra.setImagenUrl("http://empleadoimagenejemplo.png");
    }

    @Test
    @DisplayName("Deberia retornar un curso exitosamente cuando el ID existe")
    void obtenerPorId_CasoExitoso() {
        // 1. Arranged (Preparar): Cuando el repositorio busque el ID 1L, debe retornar el curso de muestra que cree como prueba
        when(repository.findById(1L)).thenReturn(Optional.of(cursoMuestra));
        // 2. Act (Actuar): Llama al método real del servicio
        Curso resultado = service.obtenerPorId(1L);
        // 3. Assert (Afirmar): Valida que el resultado no sea nulo y que los datos coincidan  
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Capacitacion Java FullStack", resultado.getTitulo());

        // Verifica que el repositorio se haya consultado exactamente una vez
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deberia lanzar RuntimeException cuando el curso solicitado no existe")
    void obtenerPorId_CursoNoEncontrado_LanzaException() {
        // 1. Arranged (Preparar): Simulo que el repositorio no encuentra nada (Retorno un Optional vacio)
        when(repository.findById(99L)).thenReturn(Optional.empty());
        // 2. Act y Assert (Actuar y Afirmar): Verifica que se lance la excepcion correcta con el mensaje
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            service.obtenerPorId(99L);
        });

        assertEquals("Curso no encontrado con el ID: 99", excepcion.getMessage());
        // Verifica nuevamente que el repositorio se haya consultado solo una vez
        verify(repository, times(1)).findById(99L);

    }

}
