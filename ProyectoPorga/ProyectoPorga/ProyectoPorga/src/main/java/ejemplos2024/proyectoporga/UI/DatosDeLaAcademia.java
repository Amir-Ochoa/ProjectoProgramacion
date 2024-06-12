package ejemplos2024.proyectoporga.UI;

import ejemplos2024.proyectoporga.Helpers.ConexionDB;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosDeLaAcademia {
    private JFrame frame;
    private JTextField nombreField;
    private JTextField ciudadField;
    private JTextField facultadField;
    private JTextField conocimientoField;
    private JTextField investigacionField;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JButton adjuntarArchivoButton;
    private JLabel archivoLabel;
    private JButton adjuntarArchivoAdicionalButton;
    private JLabel archivoAdicionalLabel;
    private JComboBox<String> actividadComboBox;

    private File archivoSeleccionado;
    private File archivoAdicionalSeleccionado;

    public DatosDeLaAcademia() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Datos de la Academia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        nombreField = new JTextField(20);
        panel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Ciudad:"), gbc);

        gbc.gridx = 1;
        ciudadField = new JTextField(20);
        panel.add(ciudadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Facultad:"), gbc);

        gbc.gridx = 1;
        facultadField = new JTextField(20);
        panel.add(facultadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Área de Conocimiento:"), gbc);

        gbc.gridx = 1;
        conocimientoField = new JTextField(20);
        panel.add(conocimientoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Línea de Investigación:"), gbc);

        gbc.gridx = 1;
        investigacionField = new JTextField(20);
        panel.add(investigacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Programa Educativo:"), gbc);

        gbc.gridx = 1;
        adjuntarArchivoButton = new JButton("Adjuntar archivo...");
        panel.add(adjuntarArchivoButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        archivoLabel = new JLabel();
        panel.add(archivoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Adjuntar Archivo Adicional:"), gbc);

        gbc.gridx = 1;
        adjuntarArchivoAdicionalButton = new JButton("Adjuntar archivo...");
        panel.add(adjuntarArchivoAdicionalButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        archivoAdicionalLabel = new JLabel();
        panel.add(archivoAdicionalLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Actividad:"), gbc);

        gbc.gridx = 1;
        actividadComboBox = new JComboBox<>(new String[]{"activo", "inactivo"});
        panel.add(actividadComboBox, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBackground(Color.RED);
        cancelarButton.setOpaque(true);
        cancelarButton.setForeground(Color.WHITE);
        buttonPanel.add(guardarButton);
        buttonPanel.add(cancelarButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(400, 550);
        frame.setVisible(true);

        // Añadir manejadores de eventos
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        adjuntarArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarArchivo();
            }
        });

        adjuntarArchivoAdicionalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarArchivoAdicional();
            }
        });

        actividadComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarActividad();
            }
        });
    }

    private void guardarDatos() {
        String nombre = nombreField.getText();
        String ciudad = ciudadField.getText();
        String facultad = facultadField.getText();
        String conocimiento = conocimientoField.getText();
        String investigacion = investigacionField.getText();
        String actividad = (String) actividadComboBox.getSelectedItem();

        if (nombre.isEmpty() || ciudad.isEmpty() || facultad.isEmpty() || conocimiento.isEmpty() || investigacion.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try (Connection conn = ConexionDB.obtenerInstancia()) {
                String instMySQL = "INSERT INTO academia (nombre, ciudad, facultad, areaConocimiento, lineaInvestigación, programaEducativo, Actividad) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(instMySQL);
                pstmt.setString(1, nombre);
                pstmt.setString(2, ciudad);
                pstmt.setString(3, facultad);
                pstmt.setString(4, conocimiento);
                pstmt.setString(5, investigacion);
                pstmt.setString(7, actividad); // Índice correcto

                // Adjuntar el archivo PDF como datos binarios
                if (archivoSeleccionado != null) {
                    try (FileInputStream fis = new FileInputStream(archivoSeleccionado)) {
                        pstmt.setBinaryStream(6, fis, (int) archivoSeleccionado.length());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    pstmt.setNull(6, java.sql.Types.BLOB);
                }

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Datos guardados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = fileChooser.getSelectedFile();
            if (archivoSeleccionado != null && archivoSeleccionado.exists() && archivoSeleccionado.isFile()) {
                archivoLabel.setText(archivoSeleccionado.getName());
            } else {
                JOptionPane.showMessageDialog(frame, "Archivo seleccionado no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarArchivoAdicional() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            archivoAdicionalSeleccionado = fileChooser.getSelectedFile();
            archivoAdicionalLabel.setText(archivoAdicionalSeleccionado.getName());
        }
    }

    private void cambiarActividad() {
        String actividadSeleccionada = (String) actividadComboBox.getSelectedItem();
        int respuesta = JOptionPane.showConfirmDialog(frame, "¿Desea cambiar la actividad a " + actividadSeleccionada + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(frame, "La actividad ha sido cambiada a " + actividadSeleccionada, "Cambio Realizado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            actividadComboBox.setSelectedItem(actividadSeleccionada.equals("activo") ? "inactivo" : "activo");
        }
    }

    public JPanel getPanel() {
        return (JPanel)frame.getContentPane();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DatosDeLaAcademia();
            }
        });
    }
}

