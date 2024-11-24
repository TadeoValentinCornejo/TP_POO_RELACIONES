import java.util.ArrayList;
import java.util.Scanner;

class DetalleFactura {
    private String codigoProducto;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double descuentoAplicado;
    private double subTotal;

    // Constructor
    public DetalleFactura(String codigoProducto, String nombreProducto, int cantidadComprada, double precioUnitario, double descuentoAplicado) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentoAplicado = descuentoAplicado;
        this.subTotal = (precioUnitario * cantidad) - (descuentoAplicado * cantidad);
    }

    // Getters
    public String getCodigoArticulo() {
        return codigoProducto;
    }

    public String getNombreArticulo() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getDescuentoItem() {
        return descuentoAplicado;
    }

    public double getSubTotal() {
        return subTotal;
    }
}

class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private double totalCalculadoFactura;
    private String cliente;
    private ArrayList<DetalleFactura> listaDetalles;

    // Constructor
    public Factura() {
        this.listaDetalles = new ArrayList<>();
    }

    // Getters y Setters
    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        this.listaDetalles.add(detalle);
    }

    public void calcularMontoTotal() {
        this.totalCalculadoFactura = listaDetalles.stream()
                .mapToDouble(DetalleFactura::getSubTotal)
                .sum();
    }

    public void mostrarFactura() {
        System.out.println("Fecha: " + fechaFactura);
        System.out.println("Número: " + numeroFactura);
        System.out.println("Cliente: " + cliente);
        System.out.println("Detalles:");
        for (DetalleFactura detalle : listaDetalles) {
            System.out.printf("%s %s %d %.2f %.2f %.2f%n",
                    detalle.getCodigoArticulo(),
                    detalle.getNombreArticulo(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getDescuentoItem(),
                    detalle.getSubTotal());
        }
        System.out.println("Total: " + totalCalculadoFactura);
    }
}

public class Ejercicio_POO_Relaciones3 {
    private static final String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        // Solicitar datos de la factura
        System.out.print("Ingrese la fecha de la factura: ");
        factura.setFechaFactura(scanner.nextLine());

        System.out.print("Ingrese el número de factura: ");
        long numeroFactura;
        while (true) {
            numeroFactura = scanner.nextLong();
            if (numeroFactura > 0) {
                factura.setNumeroFactura(numeroFactura);
                break;
            } else {
                System.out.println("El número de factura debe ser mayor a cero.");
            }
        }
        scanner.nextLine(); // Consumir salto de línea

        System.out.print("Ingrese el nombre del cliente: ");
        String cliente = scanner.nextLine();
        while (cliente.isEmpty()) {
            System.out.println("El nombre del cliente no puede estar vacío.");
            cliente = scanner.nextLine();
        }
        factura.setCliente(cliente);

        // Cargar detalles de factura
        boolean continuar = true;
        while (continuar) {
            System.out.print("Ingrese el código del artículo: ");
            String codigoProducto = scanner.next();
            String[] articuloEncontrado = null;

            for (String[] articulo : articulos) {
                if (articulo[0].equals(codigoProducto)) {
                    articuloEncontrado = articulo;
                    break;
                }
            }

            if (articuloEncontrado == null) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
                continue;
            }

            System.out.print("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();
            double precioUnitario = Double.parseDouble(articuloEncontrado[2]);
            double descuentoAplicado = cantidad > 5 ? precioUnitario * 0.1 : 0.0;

            DetalleFactura detalle = new DetalleFactura(
                    articuloEncontrado[0],
                    articuloEncontrado[1],
                    cantidad,
                    precioUnitario,
                    descuentoAplicado
            );

            factura.agregarDetalle(detalle);

            System.out.print("¿Desea agregar otro artículo? (s/n): ");
            continuar = scanner.next().equalsIgnoreCase("s");
        }

        // Calcular total y mostrar la factura
        factura.calcularMontoTotal();
        factura.mostrarFactura();
    }
}
