package com.logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import com.logic.Usuario;
import com.structure.login;

public class loginTest {

    private login loginInstance;

    @BeforeEach
    public void setUp() {
        loginInstance = new login();
        
        // Agregar usuarios para prueba
        ArrayList<Usuario> testUsers = new ArrayList<>();
        testUsers.add(new Usuario("usuario", 0, "password123"));
        testUsers.add(new Usuario("arbitro", 1, "arbitro#pass"));
        testUsers.add(new Usuario("gestor", 2, "gestor0pass"));
        testUsers.add(new Usuario("director", 4, "Dir@ct0r"));
        loginInstance.setUsers(testUsers);
    }

    // Prueba correcta: usuario y contraseña válidos
    @Test
    public void testValidUserAndPassword() {
        String username = "usuario";
        String password = "password123";
        boolean result = loginInstance.userPassCheck(username, password);
        printTestResult("testValidUserAndPassword", result, username, password);
        assertTrue(result, "El usuario y la contraseña deberían ser correctos.");
    }

    // Prueba errónea: usuario no existe
    @Test
    public void testInvalidUser() {
        String username = "invalidUser";
        String password = "password123";
        boolean result = loginInstance.userPassCheck(username, password);
        printTestResult("testInvalidUser", result, username, password);
        assertFalse(result, "El nombre de usuario debería ser incorrecto.");
    }

    // Prueba errónea: contraseña incorrecta
    @Test
    public void testInvalidPassword() {
        String username = "user1";
        String password = "wrongPassword";
        boolean result = loginInstance.userPassCheck(username, password);
        printTestResult("testInvalidPassword", result, username, password);
        assertFalse(result, "La contraseña debería ser incorrecta.");
    }

    // Prueba correcta: otro usuario y contraseña válidos
    @Test
    public void testValidUserAndPasswordUser2() {
        String username = "director";
        String password = "Dir@ct0r";
        boolean result = loginInstance.userPassCheck(username, password);
        printTestResult("testValidUserAndPasswordUser2", result, username, password);
        assertTrue(result, "El usuario y la contraseña deberían ser correctos.");
    }

    // Prueba con usuario y contraseña vacíos
    @Test
    public void testEmptyCredentials() {
        String username = "";
        String password = "";
        boolean result = loginInstance.userPassCheck(username, password);
        printTestResult("testEmptyCredentials", result, username, password);
        assertFalse(result, "No se debería aceptar un nombre de usuario o contraseña vacíos.");
    }

    // Método para imprimir resultados en consola de forma bonita y clara
    private void printTestResult(String testName, boolean result, String username, String password) {
        System.out.println("----------------------------------------------------");
        System.out.println("Resultado de la prueba: " + testName);
        System.out.println("Usuario probado: " + username);
        System.out.println("Contraseña probada: " + password);
        System.out.println("Estado: " + (result ? "Éxito" : "Fallo"));
        System.out.println("Resultado esperado: " + (result ? "Correcto" : "Incorrecto"));
        System.out.println("----------------------------------------------------\n");
    }
}
