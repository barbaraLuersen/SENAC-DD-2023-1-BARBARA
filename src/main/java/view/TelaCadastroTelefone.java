package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.telefonia.ClienteController;
import controller.telefonia.TelefoneController;
import model.exceptions.telefonia.CampoInvalidoException;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Telefone;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TelaCadastroTelefone {

	private JFrame frmNovoTelefone;
	private JLabel lblDdd;
	private JTextField txtDdd;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblMovel;
	private JCheckBox chckbxMovel;
	private JLabel lblCliente;
	private JComboBox cbCliente;
	private List<Cliente> clientes;
	private JButton btnSalvar;


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
	 */
	public TelaCadastroTelefone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNovoTelefone = new JFrame();
		frmNovoTelefone.setTitle("Novo Telefone");
		frmNovoTelefone.setBounds(100, 100, 400, 300);
		frmNovoTelefone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoTelefone.getContentPane().setLayout(null);
		
		lblDdd = new JLabel("DDD: ");
		lblDdd.setBounds(32, 36, 46, 14);
		frmNovoTelefone.getContentPane().add(lblDdd);
		
		txtDdd = new JTextField();
		txtDdd.setBounds(106, 33, 249, 20);
		frmNovoTelefone.getContentPane().add(txtDdd);
		txtDdd.setColumns(10);
		
		lblNumero = new JLabel("Número: ");
		lblNumero.setBounds(32, 82, 64, 14);
		frmNovoTelefone.getContentPane().add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(106, 79, 249, 20);
		frmNovoTelefone.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		lblMovel = new JLabel("Móvel: ");
		lblMovel.setBounds(32, 123, 46, 14);
		frmNovoTelefone.getContentPane().add(lblMovel);
		
		chckbxMovel = new JCheckBox("(selecione se o telefone for móvel)");
		chckbxMovel.setBounds(106, 119, 249, 20);
		frmNovoTelefone.getContentPane().add(chckbxMovel);
		
		lblCliente= new JLabel("Dono: ");
		lblCliente.setBounds(32, 169, 46, 14);
		frmNovoTelefone.getContentPane().add(lblCliente);
		
		ClienteController controller = new ClienteController();
		clientes = (ArrayList<Cliente>) controller.consultarTodos();
		
		cbCliente= new JComboBox(clientes.toArray());
		cbCliente.setToolTipText("Selecione");
		cbCliente.setSelectedIndex(-1);
		cbCliente.setBounds(106, 165, 249, 20);
		frmNovoTelefone.getContentPane().add(cbCliente);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Telefone novoTelefone = new Telefone();
				Cliente clienteSelecionado = (Cliente) cbCliente.getSelectedItem();
				if (clienteSelecionado != null) {
					novoTelefone.setIdCliente(clienteSelecionado.getIdCliente());
					novoTelefone.setAtivo(true);
				} else {
					novoTelefone.setAtivo(false);
				}
				TelefoneController controller = new TelefoneController();
				try {
					controller.inserir(novoTelefone);

					JOptionPane.showMessageDialog(null, "Telefone cadastrado com sucesso!", "Cadastro com sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (CampoInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(246, 209, 109, 23);
		frmNovoTelefone.getContentPane().add(btnSalvar);
	}
}
