package br.udc.edu.sistemas.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class ManterGenerico extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	protected JButton btSalvar, btExcluir, btLimpar, btVoltar;
	protected JPanel painelCampos;

	
	public ManterGenerico() {
		this.setSize(320,240);
		this.setLayout(new BorderLayout());
		this.painelCampos = new JPanel();
		this.add(this.painelCampos,BorderLayout.NORTH);
		this.CriarBotoes();
		
		this.CriarCampos();
	}
	
	private void CriarBotoes() {
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout());
		
		this.btSalvar = new JButton("Salvar");
		painelBotoes.add(this.btSalvar);
		
		this.btExcluir = new JButton("Excluir");
		this.btExcluir.setEnabled(false);
		painelBotoes.add(this.btExcluir);
		
		this.btLimpar = new JButton("Limpar");
		painelBotoes.add(this.btLimpar);
		
		this.btVoltar = new JButton("Voltar");
		painelBotoes.add(this.btVoltar);
		
		TrataEventoBotao trataEvento = new TrataEventoBotao(this);
		this.btSalvar.addMouseListener(trataEvento);
		this.btExcluir.addMouseListener(trataEvento);
		this.btLimpar.addMouseListener(trataEvento);
		this.btVoltar.addMouseListener(trataEvento);
		
		this.add(painelBotoes,BorderLayout.SOUTH);
	}
		
	public JInternalFrame getInternalFrame() {
		try {
			return (JInternalFrame) this.getParent().getParent().getParent();
		} catch (Exception e) {
			return null;
		}
	}
	
	private void CriarCampos() {
		
	}
	
	
	
	public class TrataEventoBotao implements MouseListener {
		
		private JPanel parent;
		
		public TrataEventoBotao(JPanel parent) {
			this.parent = parent;
		}

		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btSalvar){
				if (validar()){
				  salvar();
				} 
			}else if ((e.getSource() == btExcluir) && (btExcluir.isEnabled())){
				if (JOptionPane.showConfirmDialog(this.parent, "Desejar Excluir?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				  excluir();
				}
			}else if (e.getSource() == btLimpar){
				limpar();
			}else if (e.getSource() == btVoltar){
				voltar();
			}			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) { }

		@Override
		public void mouseExited(MouseEvent arg0) { }

		@Override
		public void mousePressed(MouseEvent arg0) { }

		@Override
		public void mouseReleased(MouseEvent arg0) { }
		
	}

	protected abstract void criaCampos();
	protected abstract void salvar();
	protected abstract boolean validar();
	protected abstract void excluir();
	protected abstract void limpar();
	protected abstract void voltar();
}
