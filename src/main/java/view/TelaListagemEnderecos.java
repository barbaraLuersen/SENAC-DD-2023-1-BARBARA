package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.telefonia.EnderecoController;
import model.exceptions.telefonia.EnderecoInvalidoException;
import model.vo.telefonia.Endereco;

public class TelaListagemEnderecos {

	// Atributos visuais
	private JFrame frmListagemDeEndereos;
	private JButton btnBuscarTodos;
	private JTable tblEnderecos;
	private String[] nomesColunas = { "ID", "CEP", "RUA", "NÚMERO", "BAIRRO", "CIDADE", "ESTADO" };
	private JButton btnEditar;
	private JButton btnExcluir;

	// Lista para armazenar enderecos
	private ArrayList<Endereco> enderecos;

	// Local que armazena o elemento da tabela selecionado
	private Endereco enderecoSelecionado;
	private EnderecoController enderecoController;

	// Métodos usados no JTable
	private void limparTabela() {
		tblEnderecos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		tblEnderecos = new JTable(tblEnderecos.getModel()) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
	}

	private void atualizarTabelaEnderecos() {
		this.limparTabela();
		DefaultTableModel model = (DefaultTableModel) tblEnderecos.getModel();
		EnderecoController controller = new EnderecoController();
		enderecos = (ArrayList<Endereco>) controller.consultarTodos();

		// Preenche os valores na tabela linha a linha
		for (Endereco e : enderecos) {
			Object[] novaLinhaDaTabela = new Object[7];

			novaLinhaDaTabela[0] = e.getId();
			novaLinhaDaTabela[1] = e.getCep();
			novaLinhaDaTabela[2] = e.getRua();
			novaLinhaDaTabela[3] = e.getNumero();
			novaLinhaDaTabela[4] = e.getBairro();
			novaLinhaDaTabela[5] = e.getCidade();
			novaLinhaDaTabela[6] = e.getEstado();

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemEnderecos window = new TelaListagemEnderecos();
					window.frmListagemDeEndereos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemEnderecos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemDeEndereos = new JFrame();
		frmListagemDeEndereos.setTitle("Listagem de Endereços");
		frmListagemDeEndereos.setBounds(100, 100, 450, 300);
		frmListagemDeEndereos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemDeEndereos.getContentPane().setLayout(null);

		// BUSCAR LISTA DE ENDERECOS
		btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabelaEnderecos();
			}
		});
		btnBuscarTodos.setBounds(165, 35, 113, 23);
		frmListagemDeEndereos.getContentPane().add(btnBuscarTodos);

		// EDITAR
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mostra a TelaCadastroEndereco, passando o enderecoSelecionado como parâmetro
				try {
					TelaCadastroEndereco telaEdicaoEndereco = new TelaCadastroEndereco(enderecoSelecionado);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditar.setBounds(74, 227, 89, 23);
		frmListagemDeEndereos.getContentPane().add(btnEditar);

		// EXCLUIR
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(235, 227, 89, 23);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
						"Confirma a exclusão do endereço selecionado?");

				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						enderecoController.excluir(enderecoSelecionado.getId());
						JOptionPane.showMessageDialog(null, "Endereço excluído");
						atualizarTabelaEnderecos();
					} catch (EnderecoInvalidoException excecao) {
						// JOptionPane
					}
				}
			}
		});

		frmListagemDeEndereos.getContentPane().add(btnExcluir);
		frmListagemDeEndereos.getContentPane().add(btnEditar);
		// Botões iniciam bloqueados
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);

		tblEnderecos = new JTable();
		this.limparTabela();
		tblEnderecos.setBounds(15, 70, 655, 350);

		// Evento de clique em uma linha da tabela
		// Habilita/desabilita os botões "Editar" e "Excluir"
		tblEnderecos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblEnderecos.getSelectedRow();

				if (indiceSelecionado > 0) {
					// Primeira linha da tabela contém o cabeçalho, por isso o '+1'
					enderecoSelecionado = enderecos.get(indiceSelecionado + 1);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
				} else {
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
				}
			}
		});

		frmListagemDeEndereos.getContentPane().add(tblEnderecos);
	}
}