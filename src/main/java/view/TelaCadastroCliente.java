package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.telefonia.ClienteController;
import controller.telefonia.EnderecoController;
import model.exceptions.telefonia.CampoInvalidoException;
import model.exceptions.telefonia.CpfJaUtilizadoException;
import model.exceptions.telefonia.EnderecoInvalidoException;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaCadastroCliente {

	private JFrame frmNovoCliente;
	
	private JLabel lblNome;
	private JTextField txtNome;
	
	private JLabel lblCpf;
	private JFormattedTextField txtCpf;
	private MaskFormatter mascaraCpf;
	
	private JLabel lblEndereco;
	private ArrayList<Endereco> enderecos;
	private JComboBox cbEndereco;
	
	private JButton btnCadastrar;
	

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
	 * @throws ParseException 
	 */
	public TelaCadastroCliente() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frmNovoCliente = new JFrame();
		frmNovoCliente.setTitle("Novo Cliente");
		frmNovoCliente.setBounds(100, 100, 400, 280);
		frmNovoCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoCliente.getContentPane().setLayout(null);
		
		lblNome = new JLabel("Nome: ");
		lblNome.setBounds(30, 36, 46, 14);
		frmNovoCliente.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(114, 33, 251, 20);
		frmNovoCliente.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		lblCpf = new JLabel("CPF: ");
		lblCpf.setBounds(30, 87, 46, 14);
		frmNovoCliente.getContentPane().add(lblCpf);
		
		////.setValueContainsLiteralCharacters(false) = Força o componente a informar somente o valor sem mascara no getValue()
		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setBounds(114, 84, 251, 20);
		frmNovoCliente.getContentPane().add(txtCpf);
		
		lblEndereco = new JLabel("Endereço: ");
		lblEndereco.setBounds(30, 141, 74, 14);
		frmNovoCliente.getContentPane().add(lblEndereco);
		
		EnderecoController controller = new EnderecoController();
		enderecos = (ArrayList<Endereco>) controller.consultarTodos();
		
		cbEndereco = new JComboBox(enderecos.toArray());
		cbEndereco.setToolTipText("Selecione");
		cbEndereco.setSelectedIndex(-1);
		cbEndereco.setBounds(114, 137, 251, 20);
		frmNovoCliente.getContentPane().add(cbEndereco);
		
		btnCadastrar = new JButton("Salvar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente novoCliente = new Cliente();
				novoCliente.setNome(txtNome.getText());
				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(
							txtCpf.getText()); // CPF sem mascara
					novoCliente.setCpf(cpfSemMascara); 
				} catch (ParseException ex) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				novoCliente.setEndereco((Endereco) cbEndereco.getSelectedItem());

				ClienteController controller = new ClienteController();
				try {
					controller.inserir(novoCliente);

					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Cadastro com sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (CpfJaUtilizadoException | EnderecoInvalidoException | CampoInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setBounds(276, 191, 89, 23);
		frmNovoCliente.getContentPane().add(btnCadastrar);
	}
}
