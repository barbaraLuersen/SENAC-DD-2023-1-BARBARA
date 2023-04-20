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
import model.exceptions.telefonia.TelefoneJaUtilizadoException;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Telefone;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class TelaCadastroTelefone {

	private JFrame frmNovoTelefone;
	private JLabel lblDdd;
	private JFormattedTextField txtDdd;
	private JLabel lblNumero;

	private JFormattedTextField txtNumeroMovel;
	private JFormattedTextField txtNumeroFixo;
	private JCheckBox chckbxMovel;
	private JLabel lblCliente;
	private JComboBox cbCliente;
	private List<Cliente> clientes;
	private JButton btnSalvar;
	private MaskFormatter mascaraNumeroMovel;
	private MaskFormatter mascaraNumeroFixo;
	private MaskFormatter mascaraDdd;

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
	}

	/**
	 * Initialize the contents of the frame.
	 * Método chamado no construtor da tela Cria e posiciona todos os componentes
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		frmNovoTelefone = new JFrame();
		frmNovoTelefone.setTitle("Novo Telefone");
		frmNovoTelefone.setBounds(100, 100, 316, 301);
		frmNovoTelefone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoTelefone.getContentPane().setLayout(null);

		// CHECKBOX TIPO MÓVEL
		chckbxMovel = new JCheckBox("Móvel");
		chckbxMovel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumeroFixo.setVisible(false);
				txtNumeroMovel.setVisible(true);
			}
		});
		chckbxMovel.setBounds(88, 35, 167, 20);
		frmNovoTelefone.getContentPane().add(chckbxMovel);

		// DDD
		mascaraDdd = new MaskFormatter("##");
		mascaraDdd.setValueContainsLiteralCharacters(false);

		lblDdd = new JLabel("DDD: ");
		lblDdd.setBounds(32, 78, 46, 14);
		frmNovoTelefone.getContentPane().add(lblDdd);

		txtDdd = new JFormattedTextField(mascaraDdd);
		txtDdd.setBounds(88, 75, 167, 20);
		frmNovoTelefone.getContentPane().add(txtDdd);
		txtDdd.setColumns(10);

		// NÚMERO
		mascaraNumeroMovel = new MaskFormatter("#########");
		mascaraNumeroMovel.setValueContainsLiteralCharacters(false);

		mascaraNumeroFixo = new MaskFormatter("########");
		mascaraNumeroFixo.setValueContainsLiteralCharacters(false);

		lblNumero = new JLabel("Número: ");
		lblNumero.setBounds(32, 124, 64, 14);
		frmNovoTelefone.getContentPane().add(lblNumero);

		txtNumeroMovel = new JFormattedTextField(mascaraNumeroMovel);
		txtNumeroMovel.setBounds(88, 121, 167, 20);
		frmNovoTelefone.getContentPane().add(txtNumeroMovel);

		txtNumeroFixo = new JFormattedTextField(mascaraNumeroFixo);
		txtNumeroFixo.setBounds(88, 121, 167, 20);
		frmNovoTelefone.getContentPane().add(txtNumeroFixo);

		// CLIENTE/DONO DA LINHA
		lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(32, 169, 46, 14);
		frmNovoTelefone.getContentPane().add(lblCliente);

		// Busca todos os clientes cadastrados no banco
		ClienteController controller = new ClienteController();
		clientes = (ArrayList<Cliente>) controller.consultarTodos();

		cbCliente = new JComboBox(clientes.toArray());
		cbCliente.setToolTipText("Selecione");
		cbCliente.setSelectedIndex(-1);
		cbCliente.setBounds(88, 166, 167, 20);
		frmNovoTelefone.getContentPane().add(cbCliente);

		// BOTÃO DE SALVAR
		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setBackground(new Color(192, 192, 192));
		btnSalvar.setBackground(new Color(192, 192, 192));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Telefone novoTelefone = new Telefone();

				// Confere
				novoTelefone.setMovel(chckbxMovel.isSelected());
				if (novoTelefone.isMovel()) {
					try {
						String dddSemMascara = (String) mascaraDdd.stringToValue(txtDdd.getText());
						novoTelefone.setDdd(dddSemMascara);

						String numeroMovelSemMascara = (String) mascaraNumeroMovel
								.stringToValue(txtNumeroMovel.getText());
						novoTelefone.setNumero(numeroMovelSemMascara);

					} catch (ParseException er) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar telefone", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					try {
						String dddSemMascara = (String) mascaraDdd.stringToValue(txtDdd.getText());
						novoTelefone.setDdd(dddSemMascara);

						String numeroFixoSemMascara = (String) mascaraNumeroFixo.stringToValue(txtNumeroFixo.getText());
						novoTelefone.setNumero(numeroFixoSemMascara);

					} catch (ParseException er) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar telefone", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				Cliente clienteSelecionado = (Cliente) cbCliente.getSelectedItem();
				if (clienteSelecionado != null) {
					novoTelefone.setIdCliente(clienteSelecionado.getIdCliente());
					novoTelefone.setAtivo(true);
				} else {
					novoTelefone.setAtivo(false);
				}

				try {
					TelefoneController controller = new TelefoneController();
					controller.inserir(novoTelefone);
					JOptionPane.showMessageDialog(null, "Telefone cadastrado com sucesso!", "Cadastro com sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					limparTela();
				} catch (CampoInvalidoException | TelefoneJaUtilizadoException ex) {
					JOptionPane.showMessageDialog(null, "Preencha os seguintes campos: \n" + ex.getMessage(), "Atenção",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(146, 217, 109, 23);
		frmNovoTelefone.getContentPane().add(btnSalvar);
	}

	protected void limparTela() {
		this.chckbxMovel.setSelected(false);
		this.txtDdd.setText("");
		this.txtNumeroFixo.setText("");
		this.txtNumeroMovel.setText("");
		this.cbCliente.setSelectedIndex(-1);
	}
}
