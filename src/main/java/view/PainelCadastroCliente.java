package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PainelCadastroCliente extends JPanel {
	private JTextField txtNome;
	private JTextField txtCpf;
	private JLabel lblTitulo;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblEndereco;
	private JComboBox cbxEndereco;
	private JButton btnVoltar;
	private JButton btnSalvar;

	/**
	 * Create the panel.
	 */
	public PainelCadastroCliente() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(37dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblTitulo = new JLabel("NOVO CLIENTE");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, "4, 2, 5, 1");
		
		lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblNome, "4, 4");
		
		txtNome = new JTextField();
		add(txtNome, "6, 4, 3, 1, fill, default");
		txtNome.setColumns(10);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCpf, "4, 6");
		
		txtCpf = new JTextField();
		add(txtCpf, "6, 6, 3, 1, fill, default");
		txtCpf.setColumns(10);
		
		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblEndereco, "4, 8");
		
		cbxEndereco = new JComboBox();
		add(cbxEndereco, "6, 8, 3, 1, fill, default");
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 11));
		add(btnVoltar, "6, 10");
		
		btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 11));
		add(btnSalvar, "8, 10");

	}

}
