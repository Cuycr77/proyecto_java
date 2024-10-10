package controlador;
import modelo.gestionProductos;
import modelo.modelo;
import vista.vista;

public class controlador {
    gestionProductos listaProductos = new gestionProductos();
    vista v = new vista();

    public void Control(){
        listaProductos.cargarProductos();
        boolean control = true;
        while (control){
            int opcion = v.menuI();
            switch (opcion) {
                case 1:
                    int codigo = v.pedirCodigo();
                    String nombre = v.pedirNombre();
                    int cantidad = v.pedirCantidad();
                    double precio = v.pedirPrecio();
                    modelo i = new modelo(codigo, nombre, cantidad, precio);
                    String resultadoAgregar = listaProductos.agregarArticulo(i);
                    v.imprimir(resultadoAgregar);
                    break;
                case 2:
                    int cod = v.pedirCodigo();
                    String resultadoBuscar = listaProductos.buscarProducto(cod);
                    v.imprimir(resultadoBuscar);
                    break;
                case 3:
                    v.imprimir(listaProductos.mostrarTodosLosProductos()); // Mostrar los productos
                    break;
                case 4:
                    int codigoCambiar = v.pedirCodigoCambiar();
                    String nombreCambiar = v.pedirNombreCambiar();
                    int cantidadCambiar = v.pedirCantidadCambiar();
                    double precioCambiar = v.pedirPrecioCambiar();
                    modelo productoActualizado = new modelo(codigoCambiar, nombreCambiar, cantidadCambiar, precioCambiar);
                    String resultadoCambiar = listaProductos.actualizarProducto(productoActualizado);
                    v.imprimir(resultadoCambiar);
                    break;
                case 5:
                    int codigoEliminar = v.pedirCodigoE();
                    String resultadoEliminar = listaProductos.eliminarProducto(codigoEliminar  );
                    v.imprimir(resultadoEliminar);
                    break;
                case 6:
                    control = false;
                    break;
                default:
                    String resultadoFalla = v.mensajeError();
                    v.imprimir(resultadoFalla);
                    break;
            }
        }
    }
}
