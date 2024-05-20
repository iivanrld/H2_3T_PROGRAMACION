import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Crud crud = new Crud();

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Ingrese el número de la opción que desea realizar: ");
            int opcion = scanner.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        // Agregar producto
                        scanner.nextLine(); // Consumir el salto de línea pendiente
                        System.out.print("Ingrese el nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la descripción del producto: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Ingrese la cantidad del producto: ");
                        int cantidad = obtenerEntero(scanner);
                        System.out.print("Ingrese el precio del producto: ");
                        double precio = obtenerDouble(scanner);

                        Producto nuevoProducto = new Producto(nombre, descripcion, cantidad, precio);
                        crud.agregarProducto(nuevoProducto);
                        break;
                    case 2:
                        // Mostrar productos
                        System.out.println("Listado de productos:");
                        for (Producto producto : crud.obtenerProductos()) {
                            System.out.println(producto);
                        }
                        break;
                    case 3:
                        // Actualizar producto
                        System.out.print("Ingrese el ID del producto que desea actualizar: ");
                        int idActualizar = obtenerEntero(scanner);
                        scanner.nextLine(); // Consumir el salto de línea pendiente
                        System.out.print("Ingrese el nuevo nombre del producto: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese la nueva descripción del producto: ");
                        String nuevaDescripcion = scanner.nextLine();
                        System.out.print("Ingrese la nueva cantidad del producto: ");
                        int nuevaCantidad = obtenerEntero(scanner);
                        System.out.print("Ingrese el nuevo precio del producto: ");
                        double nuevoPrecio = obtenerDouble(scanner);

                        Producto productoActualizado = new Producto(idActualizar, nuevoNombre, nuevaDescripcion, nuevaCantidad, nuevoPrecio);
                        crud.actualizarProducto(productoActualizado);
                        break;
                    case 4:
                        // Eliminar producto
                        System.out.print("Ingrese el ID del producto que desea eliminar: ");
                        int idEliminar = obtenerEntero(scanner);
                        crud.eliminarProducto(idEliminar);
                        break;
                    case 5:
                        // Salir
                        System.out.println("Saliendo del programa...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese el tipo de dato correcto.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    private static int obtenerEntero(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Error: Ingrese un número entero válido: ");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    private static double obtenerDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Error: Ingrese un número decimal válido: ");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }
}
