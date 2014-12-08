package br.udc.edu.sistemas.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class ConsultarGenerico extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	protected JButton btConsultar, btNovo, btLimpar, btImprimir;
	protected JPanel painelCampos;
	protected JList tabelaConsultar;
	
	public ConsultarGenerico() {
		this.setSize(320,240);
		this.setLayout(new BorderLayout());
		
		this.CriarBotoes();
		this.CriarPainelConsultar();
		this.CriarCampos();
	}
	
	private void CriarBotoes() {
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout());
		
		this.btNovo = new JButton("Novo");
		painelBotoes.add(this.btNovo);
		
		this.btConsultar = new JButton("Consultar");
		painelBotoes.add(this.btConsultar);
		
		this.btImprimir = new JButton("Imprimir");
		painelBotoes.add(this.btImprimir);
		
		this.btLimpar = new JButton("Limpar");
		painelBotoes.add(this.btLimpar);
		
		TrataEventoBotao trataEvento = new TrataEventoBotao();
		this.btConsultar.addMouseListener(trataEvento);
		this.btNovo.addMouseListener(trataEvento);
		this.btImprimir.addMouseListener(trataEvento);
		this.btLimpar.addMouseListener(trataEvento);
		
		this.add(painelBotoes,BorderLayout.SOUTH);
	}
	
	private void CriarPainelConsultar() {
		JPanel painelGeral = new JPanel();
		painelGeral.setLayout(new BorderLayout());
		
		this.painelCampos = new JPanel();
		painelGeral.add(this.painelCampos, BorderLayout.NORTH);
		
		JPanel painelTabela = new JPanel();
		painelTabela.setLayout(new FlowLayout());
		
		this.tabelaConsultar = new JList();
		painelTabela.add(new JScrollPane(this.tabelaConsultar));
		painelGeral.add(painelTabela,BorderLayout.CENTER);
		
		TrataEventoBotao trataEvento = new TrataEventoBotao();
		this.tabelaConsultar.addMouseListener(trataEvento);
		
		this.add(painelGeral,BorderLayout.CENTER);
		
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

		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btConsultar){
				consultar();
			}else if (e.getSource() == btNovo){
				novo();
			}else if (e.getSource() == btLimpar){
				limpar();
			}else if (e.getSource() == btImprimir){
				imprimir();
			}else if (e.getSource() == tabelaConsultar){
				detalhe();
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
	protected abstract void consultar();
	protected abstract void novo();
	protected abstract void limpar();
	protected abstract void detalhe();
	protected abstract void imprimir();
}
