package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.telefonia.EnderecoController;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroCliente {

	private JFrame frmNovoCliente;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblEndereco;
	private JFormattedTextField txtCpf;
	private JTextField txtNome;
	private JComboBox cbEndereco;
	private MaskFormatter mascaraCpf;
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente window = new TelaCadastroCliente();
					window.frmNovoCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNovoCliente = new JFrame();
		frmNovoCliente.setTitle("Novo Cliente");
		frmNovoCliente.setBounds(100, 100, 450, 260);
		frmNovoCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoCliente.getContentPane().setLayout(null);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(28, 38, 46, 14);
		frmNovoCliente.getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(97, 35, 294, 20);
		frmNovoCliente.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		// --------------------------------------
		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(28, 86, 30, 14);
		frmNovoCliente.getContentPane().add(lblCpf);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setBounds(97, 83, 294, 20);
		frmNovoCliente.getContentPane().add(txtCpf);
		// --------------------------------------------

		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(28, 133, 59, 14);
		frmNovoCliente.getContentPane().add(lblEndereco);

		EnderecoController enderecoController = new EnderecoController();
		List<Endereco> todosOsEnderecos = enderecoController.consultarTodos();

		cbEndereco = new JComboBox(todosOsEnderecos.toArray());
		cbEndereco.setBackground(new Color(128, 128, 128));
		cbEndereco.setBounds(97, 129, 294, 20);
		frmNovoCliente.getContentPane().add(cbEndereco);

		// ------------------------------------------------
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente novoCliente = new Cliente();
				novoCliente.setNome(txtNome.getText());
				novoCliente.setCpf(txtCpf.getText());
				novoCliente.setEndereco((Endereco) cbEndereco.getSelectedItem());
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 0));
		// como mudar a cor de fundo de um campo:
		// btnSalvar.setOpaque(true);
		btnSalvar.setBackground(new Color(128, 128, 128));
		btnSalvar.setBounds(302, 177, 89, 20);
		frmNovoCliente.getContentPane().add(btnSalvar);
	}
}
