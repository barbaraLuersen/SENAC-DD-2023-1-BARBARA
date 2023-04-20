package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.telefonia.ClienteController;
import controller.telefonia.TelefoneController;
import model.exceptions.telefonia.CampoInvalidoException;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Telefone;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;

public class TelaCadastroTelefone {

	private JFrame frmNovoTelefone;
	private JLabel lblNumero;

	private JButton btnSalvar;
	private JLabel lblTipo;
	private JLabel lblCliente;
	private JRadioButton rdbtnFixo;
	private JRadioButton rdbtnMovel;
	private JFormattedTextField txtTelefoneFixo;
	private JFormattedTextField txtTelefoneMovel;
	private JComboBox cbClientes;
	private MaskFormatter mascaraTelefoneFixo;
	private MaskFormatter mascaraTelefoneMovel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroTelefone window = new TelaCadastroTelefone();
					window.frmNovoTelefone.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public TelaCadastroTelefone() throws ParseException {
		initialize();
		esconderComponentes();
	}

	private void esconderComponentes() {
		lblNumero.setVisible(false);
		txtTelefoneFixo.setVisible(false);
		lblCliente.setVisible(false);
		cbClientes.setVisible(false);
		btnSalvar.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		frmNovoTelefone = new JFrame();
		frmNovoTelefone.setTitle("Novo Telefone");
		frmNovoTelefone.setBounds(100, 100, 312, 298);
		frmNovoTelefone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoTelefone.getContentPane().setLayout(null);

		lblTipo = new JLabel("Tipo: ");
		lblTipo.setBounds(25, 42, 46, 14);
		frmNovoTelefone.getContentPane().add(lblTipo);

		lblNumero = new JLabel("Número: ");
		lblNumero.setBounds(25, 93, 46, 14);
		frmNovoTelefone.getContentPane().add(lblNumero);

		lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(25, 150, 46, 14);
		frmNovoTelefone.getContentPane().add(lblCliente);

		ButtonGroup grupo = new ButtonGroup();
		
		
		rdbtnFixo = new JRadioButton("Fixo");
		rdbtnFixo.setBounds(81, 38, 61, 23);
		frmNovoTelefone.getContentPane().add(rdbtnFixo);

		rdbtnMovel = new JRadioButton("Móvel");
		rdbtnMovel.setBounds(200, 38, 69, 23);
		frmNovoTelefone.getContentPane().add(rdbtnMovel);

		mascaraTelefoneFixo = new MaskFormatter("(##)####-####");
		
		
		mascaraTelefoneFixo.setValueContainsLiteralCharacters(false);

		txtTelefoneFixo = new JFormattedTextField();
		txtTelefoneFixo.setBounds(81, 90, 188, 20);
		frmNovoTelefone.getContentPane().add(txtTelefoneFixo);

		txtTelefoneMovel = new JFormattedTextField();
		txtTelefoneMovel.setBounds(81, 90, 188, 20);
		frmNovoTelefone.getContentPane().add(txtTelefoneMovel);

		cbClientes = new JComboBox();
		cbClientes.setBounds(81, 146, 188, 22);
		frmNovoTelefone.getContentPane().add(cbClientes);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(180, 203, 89, 23);
		frmNovoTelefone.getContentPane().add(btnSalvar);
	}
}
