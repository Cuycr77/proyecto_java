package vista;

import controlador.controlador;

import javax.swing.*;
import java.util.Scanner;

public class vista {
    Scanner scanner = new Scanner(System.in);

    public void imprimir(String texto) {
        JOptionPane.showMessageDialog(null,
                texto,
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }
    // vista del menu inventario
    public int menuI(){
        int opcion = 0;
        while (opcion < 1 || opcion > 6) {
            String input = JOptionPane.showInputDialog(null,
                    "Menu Productos\n"+
                            "1. Crear Producto.\n" +
                            "2. Buscar Producto.\n" +
                            "3. Mostrar Productos.\n" +
                            "4. Actualizar producto.\n" +
                            "5. Eliminar producto.\n" +
                            "6. Salir.\n" +
                            "Ingrese la opcion que desea hacer:",
                    "menu productos",
                    JOptionPane.QUESTION_MESSAGE);
            if (input != null){
                try {
                    opcion = Integer.parseInt(input);
                }catch (NumberFormatException i ){
                    opcion = 0;
                }
            }else {
                opcion = 6;
            }

        }
        return opcion;
    }

    public int pedirCodigo() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el código del producto:", "Código del Producto", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    public int pedirCodigoE() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el código del producto a eliminar:", "Eliminar Producto", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    public int pedirCodigoCambiar() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el código del producto a modificar:", "Modificar Producto", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    public String pedirNombre() {
        return JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:", "Nombre del Producto", JOptionPane.QUESTION_MESSAGE);
    }

    public String pedirNombreCambiar() {
        return JOptionPane.showInputDialog(null, "Ingrese el nombre del producto a cambiar:", "Cambiar Nombre del Producto", JOptionPane.QUESTION_MESSAGE);
    }

    public int pedirCantidad() {
        String input = JOptionPane.showInputDialog(null, "Ingrese la cantidad del producto:", "Cantidad del Producto", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    public int pedirCantidadCambiar() {
        String input = JOptionPane.showInputDialog(null, "Ingrese la cantidad del producto a cambiar:", "Cambiar Cantidad del Producto", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    public double pedirPrecio() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el precio del producto:", "Precio del Producto", JOptionPane.QUESTION_MESSAGE);
        return Double.parseDouble(input);
    }

    public double pedirPrecioCambiar() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el precio del producto a cambiar:", "Precio del Producto a cambiar", JOptionPane.QUESTION_MESSAGE);
        return Double.parseDouble(input);
    }

    public String mensajeError() {
        JOptionPane.showMessageDialog(null, "Opción no válida, intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        return "Opción no válida, intente de nuevo.";
    }

    public void Conectar(){
        controlador control = new controlador();
        control.Control();
    }
    // menu principal
    public int menu(){
        int opcion = 0;
        while (opcion < 1 || opcion > 3) {
            String input = JOptionPane.showInputDialog(null,
                    "Menu Principal\n"+
                            "1. Iniciar sesion.\n" +
                            "2. Registrarse.\n" +
                            "3. Salir.\n" +
                            "Ingrese la opcion que desea hacer:",
                    "menu principal",
                    JOptionPane.QUESTION_MESSAGE);
            if (input != null){
                try {
                    opcion = Integer.parseInt(input);
                }catch (NumberFormatException i ){
                    opcion = 0;
                }
            }else {
                opcion = 3;
            }

        }
        return opcion;
    }
    // funciones de registrarse
    public int pedirIdentificacion() {
        String input = JOptionPane.showInputDialog(null, "Ingrese identificacion del usuario:", "Identificacion del Usuario", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    public String pedirNombreU() {
        return JOptionPane.showInputDialog(null, "Ingrese el nombre del usuario:", "Nombre del Usuario", JOptionPane.QUESTION_MESSAGE);
    }

    public String pedirApellido() {
        return JOptionPane.showInputDialog(null, "Ingrese el apellido del usuario:", "Apellido del Usuario", JOptionPane.QUESTION_MESSAGE);
    }

    public String pedirCorreo() {
        return JOptionPane.showInputDialog(null, "Ingrese el correo del usuario:", "Correo del Usuario", JOptionPane.QUESTION_MESSAGE);
    }

    public String pedirNombreEstablecimiento() {
        return JOptionPane.showInputDialog(null, "Ingrese el nombre del establecimiento del usuario:", "Nombre del Establecimiento", JOptionPane.QUESTION_MESSAGE);
    }

    public String pedirTipoEstablecimiento() {
        return JOptionPane.showInputDialog(null, "Ingrese el tipo de establecimiento del usuario:", "Tipo de Establecimiento", JOptionPane.QUESTION_MESSAGE);
    }

    public String pedirDireccion() {
        return JOptionPane.showInputDialog(null, "Ingrese la dirección del usuario:", "Dirección del Usuario", JOptionPane.QUESTION_MESSAGE);
    }

    public int pedirTelefono() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el teléfono del usuario:", "Teléfono del Usuario", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    public String pedirFuncion() {
        String[] opciones = {"Cliente", "Usuario"};
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Seleccione la función del usuario:",
                "Función del Usuario",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);
        if (seleccion >= 0) {
            return opciones[seleccion];
        } else {
            return null;
        }
    }
}
