
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Crud {
    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setInt(3, producto.getCantidad());
            statement.setDouble(4, producto.getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Error al agregar un producto" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Agregando correctamente el producto.");
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection connection = Conexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");
                productos.add(new Producto(id, nombre, descripcion, cantidad, precio));
            }
        } catch (SQLException e) {
        	System.err.println("Error al obtener los productos" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Obteniendo productos.");
        return productos;
    }

    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, cantidad = ?, precio = ? WHERE id = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setInt(3, producto.getCantidad());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Error al actualizar el producto" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Producto actualizado correctamente.");
    }

    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Error al borrar un producto" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Producto elminado con exito");
    }
}



