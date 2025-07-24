package app.vista.taller2;

import app.modelo.Conexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Login extends JFrame {
    private JTextField textUsuario;
    private JPasswordField password;
    private JButton accesoButton;
    private JButton limpiarButton;
    private JPanel Panel;
    private JComboBox comboBoxPerfil;

    private Conexion conexion;

    public Login() {
        setTitle("login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(Panel);
        setLocationRelativeTo(null);

        conexion = new Conexion(); // Instanciamos la clase Conexion

        getRootPane().setDefaultButton(accesoButton);

        accesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textUsuario.getText();
                String pass = new String(password.getPassword());
                String perfil = (String) comboBoxPerfil.getSelectedItem();

                if (validarCredenciales(user, pass , perfil)) {
                    Catalogos c = new Catalogos(user);
                    c.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                    textUsuario.setText("");
                    password.setText("");
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textUsuario.setText("");
                password.setText("");
            }
        });
    }

    private boolean validarCredenciales(String usuario, String contrasena,String perfil) {
        List<String[]> lista = conexion.obtenerCredenciales();

        for (String[] cred : lista) {
            if (usuario.equals(cred[0]) && contrasena.equals(cred[1]) && perfil.equals(cred[2])  ) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
