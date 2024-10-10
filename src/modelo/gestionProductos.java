package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;

public class gestionProductos {
    conexion cnn = new conexion();
    Connection conexion = cnn.Conecta();
    PreparedStatement ps = null;
    ResultSet res = null;

    public HashMap<String, modelo> productos = new HashMap<>();

    public void cargarProductos() {
        String sql = "SELECT codigo, nombre, cantidad, precio FROM productos";
        try {
            ps = conexion.prepareStatement(sql);
            res = ps.executeQuery();

            while (res.next()) {
                int codigo = res.getInt("codigo");
                String nombre = res.getString("nombre");
                int cantidad = res.getInt("cantidad");
                double precio = res.getDouble("precio");

                modelo producto = new modelo(codigo, nombre, cantidad, precio);
                productos.put(String.valueOf(codigo), producto); 
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        } finally {
            try {
                if (res != null) res.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    public String agregarArticulo(modelo p) {
        // Verificar si el producto ya existe en el HashMap
        if (productos.containsKey(String.valueOf(p.getCodigo()))) {
            return "El producto ya existe!";
        }

        // Intentar agregar el producto a la base de datos
        String sql = "INSERT INTO productos (codigo, nombre, cantidad, precio) VALUES (?, ?, ?, ?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getCantidad());
            ps.setDouble(4, p.getPrecio());
            ps.executeUpdate();

            // Agregar el producto al HashMap
            productos.put(String.valueOf(p.getCodigo()), p);
            return "Producto agregado exitosamente!";
        } catch (SQLException e) {
            return "Error al agregar el producto: " + e.getMessage();
        }
    }

    public String buscarProducto(int codigo) {
        // Verificar si el producto está en el HashMap
        if (productos.containsKey(String.valueOf(codigo))) {
            modelo producto = productos.get(String.valueOf(codigo));
            return "Producto encontrado: Código: " + producto.getCodigo() +
                    ", Nombre: " + producto.getNombre() +
                    ", Cantidad: " + producto.getCantidad() +
                    ", Precio: " + producto.getPrecio();
        } else {
            // Si no está en el HashMap, se intenta buscar en la base de datos
            String sql = "SELECT codigo, nombre, cantidad, precio FROM productos WHERE codigo = ?";
            try {
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, codigo);
                res = ps.executeQuery();

                if (res.next()) {
                    String nombre = res.getString("nombre");
                    int cantidad = res.getInt("cantidad");
                    double precio = res.getDouble("precio");

                    modelo producto = new modelo(codigo, nombre, cantidad, precio);
                    productos.put(String.valueOf(codigo), producto);  // Cachear el producto en el HashMap

                    return "Producto encontrado: Código: " + codigo +
                            ", Nombre: " + nombre +
                            ", Cantidad: " + cantidad +
                            ", Precio: " + precio;
                } else {
                    return "Producto no encontrado!";
                }
            } catch (SQLException e) {
                return "Error al buscar el producto: " + e.getMessage();
            } finally {
                try {
                    if (res != null) res.close();
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar recursos: " + e.getMessage());
                }
            }
        }
    }

    public String mostrarTodosLosProductos() {
        StringBuilder resultado = new StringBuilder();

        if (productos.isEmpty()) {
            return "No hay productos disponibles.";
        }

        // Recorrer el HashMap y agregar los detalles de cada producto al StringBuilder
        for (modelo producto : productos.values()) {
            resultado.append("Código: ").append(producto.getCodigo())
                    .append(", Nombre: ").append(producto.getNombre())
                    .append(", Cantidad: ").append(producto.getCantidad())
                    .append(", Precio: ").append(producto.getPrecio())
                    .append("\n");
        }

        return resultado.toString();
    }
    public String actualizarProducto(modelo p) {
        // Verificar si el producto existe en la base de datos
        String sql = "UPDATE productos SET nombre = ?, cantidad = ?, precio = ? WHERE codigo = ?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getCantidad());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getCodigo());

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0) {
                // Si la actualización fue exitosa, también actualizar en el HashMap
                if (productos.containsKey(String.valueOf(p.getCodigo()))) {
                    productos.put(String.valueOf(p.getCodigo()), p); // Actualizar el producto en el HashMap
                }

                return "Producto actualizado exitosamente!";
            } else {
                return "El producto con el código " + p.getCodigo() + " no existe.";
            }

        } catch (SQLException e) {
            return "Error al actualizar el producto: " + e.getMessage();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    public String eliminarProducto(int codigo) {
        String sql = "DELETE FROM productos WHERE codigo = ?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, codigo);

            int filasEliminadas = ps.executeUpdate();

            if (filasEliminadas > 0) {
                productos.remove(String.valueOf(codigo));
                return "Producto eliminado exitosamente!";
            } else {
                return "El producto con el código " + codigo + " no existe.";
            }
        } catch (SQLException e) {
            return "Error al eliminar el producto: " + e.getMessage();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}
