import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gui{
    private JFrame frame;
    private Seccion electronicos;
    private Seccion ropa;
    private Seccion alimentos;
    private Seccion maquinaria;

    public Gui() {
        // Inicializa las secciones del puerto
        electronicos = new Seccion("Electrónicos", 200000);
        ropa = new Seccion("Ropa", 150000);
        alimentos = new Seccion("Alimentos", 250000);
        maquinaria = new Seccion("Maquinaria", 300000);

        // Crear el frame de la ventana
        frame = new JFrame("Gestión de Contenedores");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear paneles y componentes de la interfaz
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel label = new JLabel("Seleccione una opción:");
        panel.add(label);

        JButton agregarContenedorButton = new JButton("Agregar Contenedor");
        agregarContenedorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarContenedor();
            }
        });
        panel.add(agregarContenedorButton);

        JButton mostrarContenedoresButton = new JButton("Mostrar Contenedores");
        mostrarContenedoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarContenedores();
            }
        });
        panel.add(mostrarContenedoresButton);

        JButton moverContenedorButton = new JButton("Mover Contenedor");
        moverContenedorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moverContenedor();
            }
        });
        panel.add(moverContenedorButton);

        JButton calcularPesoButton = new JButton("Calcular Peso Total");
        calcularPesoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularPeso();
            }
        });
        panel.add(calcularPesoButton);

        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar la aplicación
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); // Cierra la aplicación
                }
            }
        });
        panel.add(salirButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void agregarContenedor() {
        // Ventana emergente para ingresar los datos del contenedor
        JTextField idField = new JTextField();
        JTextField productoField = new JTextField();
        JTextField pesoField = new JTextField();
        JTextField destinoField = new JTextField();
        String[] secciones = { "Electrónicos", "Ropa", "Alimentos", "Maquinaria" };
        JComboBox<String> seccionBox = new JComboBox<>(secciones);

        Object[] message = {
            "ID:", idField,
            "Producto:", productoField,
            "Peso:", pesoField,
            "Destino:", destinoField,
            "Sección:", seccionBox
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Agregar Contenedor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String producto = productoField.getText();
            double peso = Double.parseDouble(pesoField.getText());
            String destino = destinoField.getText();
            String seccionSeleccionada = (String) seccionBox.getSelectedItem();

            Contenedor nuevoContenedor = new Contenedor(id, producto, peso, destino);
            boolean agregado = false;

            // Asignar el contenedor a la sección seleccionada
            switch (seccionSeleccionada.toLowerCase()) {
                case "electrónicos":
                    agregado = electronicos.agregarContenedor(nuevoContenedor);
                    break;
                case "ropa":
                    agregado = ropa.agregarContenedor(nuevoContenedor);
                    break;
                case "alimentos":
                    agregado = alimentos.agregarContenedor(nuevoContenedor);
                    break;
                case "maquinaria":
                    agregado = maquinaria.agregarContenedor(nuevoContenedor);
                    break;
            }

            if (agregado) {
                JOptionPane.showMessageDialog(null, "Contenedor agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo agregar el contenedor. Verifique el peso o el espacio.");
            }
        }
    }

    private void mostrarContenedores() {
        // Ventana emergente para seleccionar la sección y mostrar los contenedores
        String[] secciones = { "Electrónicos", "Ropa", "Alimentos", "Maquinaria" };
        String seccionSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione una sección:",
                "Mostrar Contenedores", JOptionPane.QUESTION_MESSAGE, null, secciones, secciones[0]);

        String listaContenedores = "";
        switch (seccionSeleccionada.toLowerCase()) {
            case "electrónicos":
                listaContenedores = electronicos.listarContenedores();
                break;
            case "ropa":
                listaContenedores = ropa.listarContenedores();
                break;
            case "alimentos":
                listaContenedores = alimentos.listarContenedores();
                break;
            case "maquinaria":
                listaContenedores = maquinaria.listarContenedores();
                break;
        }

        JOptionPane.showMessageDialog(null, listaContenedores);
    }

    private void moverContenedor() {
        // Ventana emergente para mover un contenedor de una sección a otra
        JTextField idField = new JTextField();
        String[] secciones = { "Electrónicos", "Ropa", "Alimentos", "Maquinaria" };
        JComboBox<String> seccionOrigenBox = new JComboBox<>(secciones);
        JComboBox<String> seccionDestinoBox = new JComboBox<>(secciones);

        Object[] message = {
            "ID del Contenedor:", idField,
            "Sección de Origen:", seccionOrigenBox,
            "Sección de Destino:", seccionDestinoBox
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Mover Contenedor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String seccionOrigen = (String) seccionOrigenBox.getSelectedItem();
            String seccionDestino = (String) seccionDestinoBox.getSelectedItem();

            Seccion origen = null, destino = null;

            // Asignar las secciones de origen y destino
            switch (seccionOrigen.toLowerCase()) {
                case "electrónicos":
                    origen = electronicos;
                    break;
                case "ropa":
                    origen = ropa;
                    break;
                case "alimentos":
                    origen = alimentos;
                    break;
                case "maquinaria":
                    origen = maquinaria;
                    break;
            }

            switch (seccionDestino.toLowerCase()) {
                case "electrónicos":
                    destino = electronicos;
                    break;
                case "ropa":
                    destino = ropa;
                    break;
                case "alimentos":
                    destino = alimentos;
                    break;
                case "maquinaria":
                    destino = maquinaria;
                    break;
            }

            if (origen != null && destino != null) {
                Contenedor contenedorMover = null;

                // Buscar y eliminar el contenedor de la sección de origen
                for (int i = 0; i < origen.getContenedores().length; i++) {
                    for (int j = 0; j < origen.getContenedores()[i].length; j++) {
                        if (origen.getContenedores()[i][j] != null && origen.getContenedores()[i][j].getIdentificador().equals(id)) {
                            contenedorMover = origen.getContenedores()[i][j];
                            origen.getContenedores()[i][j] = null;
                            double nuevoPeso = origen.getPesoTotal() - contenedorMover.getPeso();
                            origen.setPesoTotal(nuevoPeso);
                            break;
                        }
                    }
                }

                if (contenedorMover != null) {
                    // Intentar agregar el contenedor en la sección de destino
                    boolean agregadoMover = destino.agregarContenedor(contenedorMover);
                    if (agregadoMover) {
                        JOptionPane.showMessageDialog(null, "Contenedor movido exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo mover el contenedor. Verifique el peso o el espacio.");
                        origen.agregarContenedor(contenedorMover); // Regresar el contenedor a la sección original si no se pudo mover
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Contenedor no encontrado en la sección de origen.");
                }
            }
        }
    }

    private void calcularPeso() {
        // Ventana emergente para seleccionar la sección y mostrar el peso total
        String[] secciones = { "Electrónicos", "Ropa", "Alimentos", "Maquinaria" };
        String seccionSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione una sección:",
                "Calcular Peso Total", JOptionPane.QUESTION_MESSAGE, null, secciones, secciones[0]);

        double pesoTotal = 0;
        switch (seccionSeleccionada.toLowerCase()) {
            case "electrónicos":
                pesoTotal = electronicos.getPesoTotal();
                break;
            case "ropa":
                pesoTotal = ropa.getPesoTotal();
                break;
            case "alimentos":
                pesoTotal = alimentos.getPesoTotal();
                break;
            case "maquinaria":
                pesoTotal = maquinaria.getPesoTotal();
                break;
        }

        JOptionPane.showMessageDialog(null, "Peso total en la sección de " + seccionSeleccionada + ": " + pesoTotal + " kg");
    }

    public static void main(String[] args) {
        new Gui(); // Iniciar la interfaz gráfica
    }
}

