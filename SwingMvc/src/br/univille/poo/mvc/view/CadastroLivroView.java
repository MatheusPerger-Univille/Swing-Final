package br.univille.poo.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.univille.poo.mvc.control.CadastroLivroControl;
import br.univille.poo.mvc.model.LivroModel;
import br.univille.poo.mvc.util.Observer;
import br.univille.poo.mvc.util.Subject;

public class CadastroLivroView extends JFrame implements Observer{

	private LivroModel model;
	private CadastroLivroControl control;
	
	private JTextField codigoTextField;
	private JTextField nomeTextField;
	private JTextField CategoriaTextField;
	private JTextField AutorTextField;
	private JButton    salvarButton;
	private JButton    cancelarButton;
	private JButton    novoButton;
	private JButton    deletarButton;
	private JLabel statusLabel;
	
	public CadastroLivroView() {
		setSize(400,280);
		setTitle("Cadastro de Livro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildLayout();
	}
	
	public void setModel(LivroModel model) {
		this.model = model;
	}
	
	public void setControl(CadastroLivroControl control) {
		this.control = control;
	}

	private void buildLayout() {
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(BorderFactory.createCompoundBorder(
				
				  BorderFactory.createEmptyBorder(20, 20, 0, 20)
				, BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Detalhes do Livro")
						,BorderFactory.createEmptyBorder(10, 10, 10, 10))));

		codigoTextField = new JTextField(20);
		codigoTextField.setEnabled(false);
		nomeTextField = new JTextField(20);
		CategoriaTextField = new JTextField(20);
		AutorTextField = new JTextField(20);
		
		salvarButton = new JButton("Salvar");
		salvarButton.setToolTipText("Salvar as alterações");
		salvarButton.addActionListener(e -> salvar());
		
		cancelarButton = new JButton("Cancelar");
		novoButton = new JButton("Novo");
		novoButton.setToolTipText("Criar novo livro");
		novoButton.addActionListener(e -> novo());
		deletarButton = new JButton("Deletar");
		deletarButton.setToolTipText("Excluir livro");
		deletarButton.setEnabled(true);
		deletarButton.addActionListener(e -> deletar());
		
		
		JLabel l = new JLabel("Código",JLabel.RIGHT);
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(codigoTextField);
		
		l = new JLabel("Nome",JLabel.RIGHT);
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(nomeTextField);
		l = new JLabel("Categoria",JLabel.RIGHT);
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(CategoriaTextField);
		l = new JLabel("Autor",JLabel.RIGHT);
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(AutorTextField);
		l = new JLabel();
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(novoButton);
		panel.add(salvarButton);
		panel.add(deletarButton);
		
		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 10));
		statusLabel = new JLabel("Cadastre um novo livro");
		statusPanel.add(statusLabel);
		
		
		root.add(statusPanel,BorderLayout.SOUTH);
		root.add(panel,BorderLayout.CENTER);
		
		add(root);
	}
	
	private void exibirDados() {
		codigoTextField.setText(Long.toString(model.getId()));
		nomeTextField.setText(model.getNome());
		AutorTextField.setText(model.getAutor());
		CategoriaTextField.setText(model.getCategoria());
	}
	
	
	private void salvar() {
		control.salvar();
	}
	
	private void deletar() {
		control.deletar();
	}
	
	private void novo() {
		control.novo();
	}

	public JButton getBotaoDeletar() {
		return deletarButton;
	}

	public void setMensagemStatusBar(String text) {
		statusLabel.setText(text);
	}

	@Override
	public void update(Subject s, Object o) {
		model = (LivroModel) o;
		exibirDados();
	}
	
	public String getCategoria() {
		return CategoriaTextField.getText();
	}
	
	public String getAutor() {
		return AutorTextField.getText();
	}
	
	public String getNome() {
		return nomeTextField.getText();
	}
	
}
