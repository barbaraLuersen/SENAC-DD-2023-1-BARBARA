package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class MenuTelefonia {

	private JFrame frmMenuTelefonia;
	private JMenu mnCliente;
	private JMenuItem mntmCadastrarCliente;
	private JMenuItem mntmExcluirCliente;
	private JMenuItem mntmAtualizarCliente;
	private JMenuItem mntmListarClientes;
	private JMenu mnEndereco;
	private JMenuItem mntmCadastrarEndereco;
	private JMenuItem mntmExcluirEndereco;
	private JMenuItem mntmAtualizarEndereco;
	private JMenuItem mntmListarEnderecos;
	private JMenu mnTelefone;
	private JMenuItem mntmCadastrarTelefone;
	private JMenuItem mntmExcluirTelefone;
	private JMenuItem mntmAtualizarTelefone;
	private JMenuItem mntmListarTelefones;
	private JMenu mnSobre;
	private JMenuItem mntmAjuda;
	private JMenuItem mntmManual;
	private JMenuItem mntmAutores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuTelefonia window = new MenuTelefonia();
					window.frmMenuTelefonia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuTelefonia() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuTelefonia = new JFrame();
		frmMenuTelefonia.setTitle("Menu Telefonia");
		frmMenuTelefonia.setBounds(100, 100, 452, 260);
		frmMenuTelefonia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMenuTelefonia.setJMenuBar(menuBar);
		
		//CLIENTE
		mnCliente = new JMenu("Cliente");
		mnCliente.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Cliente/icons8-usuário-de-gênero-neutro-32.png")));
		menuBar.add(mnCliente);
		
		mntmCadastrarCliente = new JMenuItem("Cadastrar");
		mntmCadastrarCliente.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Cliente/icons8-adicionar-usuário-masculino-32.png")));
		mnCliente.add(mntmCadastrarCliente);
		
		mntmExcluirCliente = new JMenuItem("Excluir");
		mntmExcluirCliente.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Cliente/icons8-remover-usuário-masculino-32.png")));
		mnCliente.add(mntmExcluirCliente);
		
		mntmAtualizarCliente = new JMenuItem("Atualizar");
		mntmAtualizarCliente.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Cliente/icons8-alterar-usuário-masculino-32.png")));
		mnCliente.add(mntmAtualizarCliente);
		
		mntmListarClientes = new JMenuItem("Listar Clientes");
		mntmListarClientes.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Sobre/icons8-lista-de-características-32.png")));
		mnCliente.add(mntmListarClientes);
		
		//ENDEREÇO
		mnEndereco = new JMenu("Endereço");
		mnEndereco.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Endereco/icons8-casa-32.png")));
		menuBar.add(mnEndereco);
		
		mntmCadastrarEndereco = new JMenuItem("Cadastrar");
		mntmCadastrarEndereco.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Endereco/icons8-mais-32.png")));
		mnEndereco.add(mntmCadastrarEndereco);
		
		mntmExcluirEndereco = new JMenuItem("Excluir");
		mntmExcluirEndereco.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Endereco/icons8-excluir-32.png")));
		mnEndereco.add(mntmExcluirEndereco);
		
		mntmAtualizarEndereco = new JMenuItem("Atualizar");
		mntmAtualizarEndereco.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Endereco/icons8-reiniciar-32.png")));
		mnEndereco.add(mntmAtualizarEndereco);
		
		mntmListarEnderecos = new JMenuItem("Listar Endereços");
		mntmListarEnderecos.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Sobre/icons8-lista-de-características-32.png")));
		mnEndereco.add(mntmListarEnderecos);
		
		//TELEFONE
		mnTelefone = new JMenu("Telefone");
		mnTelefone.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Telefone/icons8-telefone-desligado-32.png")));
		menuBar.add(mnTelefone);
		
		mntmCadastrarTelefone = new JMenuItem("Cadastrar");
		mntmCadastrarTelefone.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Telefone/icons8-adicionar-telefone-32.png")));
		mnTelefone.add(mntmCadastrarTelefone);
		
		mntmExcluirTelefone = new JMenuItem("Excluir");
		mntmExcluirTelefone.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Telefone/icons8-chamada-desligada-32.png")));
		mnTelefone.add(mntmExcluirTelefone);
		
		mntmAtualizarTelefone = new JMenuItem("Atualizar");
		mntmAtualizarTelefone.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Telefone/icons8-transferência-de-chamada-32.png")));
		mnTelefone.add(mntmAtualizarTelefone);
		
		mntmListarTelefones = new JMenuItem("ListarTelefones");
		mntmListarTelefones.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Sobre/icons8-lista-de-características-32.png")));
		mnTelefone.add(mntmListarTelefones);
		
		//SOBRE
		mnSobre = new JMenu("Sobre");
		mnSobre.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Sobre/icons8-manutenção-32.png")));
		menuBar.add(mnSobre);
		
		mntmAjuda = new JMenuItem("Ajuda");
		mntmAjuda.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Sobre/icons8-suporte-ao-cliente-32.png")));
		mnSobre.add(mntmAjuda);
		
		mntmManual = new JMenuItem("Manual");
		mntmManual.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Sobre/icons8-perguntas-32.png")));
		mnSobre.add(mntmManual);
		
		mntmAutores = new JMenuItem("Autores");
		mntmAutores.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/Icones/Sobre/icons8-chamada-em-conferência-32.png")));
		mnSobre.add(mntmAutores);
	}

}
