package com.mackenzie.entregaTG2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class UsuarioTeste {

    private Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario();
    }

    @Test
    public void testeCadastrarUsuarioComSucesso() {
        // Arrange
        Map<String, Object> preferencias = new HashMap<>();
        preferencias.put("vegetariano", true);
        preferencias.put("alergias", "lactose");

        // Act
        usuario.cadastrar("João Silva", "joao@email.com", "senha123", preferencias, "Receita Favorita");

        // Assert
        assertEquals("João Silva", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertNotNull(usuario.getSenhaHash());
        assertEquals(preferencias, usuario.getPreferenciasAlimentares());
        assertEquals("Receita Favorita", usuario.getReceita());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeCadastrarUsuarioComEmailInvalido() {
        // Act & Assert
        usuario.cadastrar("Maria Silva", "emailinvalido", "senha123", null, null);
    }

    @Test
    public void testeAutenticarComCredenciaisCorretas() {
        // Arrange
        usuario.cadastrar("Pedro Santos", "pedro@email.com", "minhasenha", null, null);

        // Act
        boolean resultado = usuario.autenticar("pedro@email.com", "minhasenha");

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void testeAutenticarComSenhaIncorreta() {
        // Arrange
        usuario.cadastrar("Ana Costa", "ana@email.com", "senha_correta", null, null);

        // Act
        boolean resultado = usuario.autenticar("ana@email.com", "senha_errada");

        // Assert
        assertFalse(resultado);
    }
}
