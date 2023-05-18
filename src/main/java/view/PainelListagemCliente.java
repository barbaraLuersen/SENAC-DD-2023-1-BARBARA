package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

public class PainelListagemCliente extends JPanel {
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtDataNascimento;
	private JTable tblClientes;

	/**
	 * Create the panel.
	 */
	public PainelListagemCliente() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(79dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(91dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(18dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(88dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNome, "4, 4");
		
		txtNome = new JTextField();
		add(txtNome, "6, 4, fill, default");
		txtNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCpf, "8, 4, right, default");
		
		txtCpf = new JTextField();
		add(txtCpf, "10, 4, fill, top");
		txtCpf.setColumns(10);
		
		JLabel lblDataNascimento = new JLabel("Data de nascimento:");
		lblDataNascimento.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblDataNascimento, "4, 6, right, default");
		
		txtDataNascimento = new JTextField();
		add(txtDataNascimento, "6, 6, fill, default");
		txtDataNascimento.setColumns(10);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 11));
		add(btnBuscar, "4, 8, 7, 1");
		
		JButton btnBuscarTodos = new JButton("BUSCAR TODOS");
		btnBuscarTodos.setFont(new Font("Arial", Font.PLAIN, 11));
		add(btnBuscarTodos, "4, 10, 7, 1");
		
		tblClientes = new JTable();
		tblClientes.setFont(new Font("Arial", Font.PLAIN, 11));
		add(tblClientes, "4, 12, 7, 1, fill, fill");

	}

}
