package br.udc.edu.sistemas.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FormPrincipal extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar barraMenu;
	private JMenu menuSair;
	private JMenu menuCadastro;
	private JMenuItem itemMarca;
	private JMenuItem itemModelo;
	private JMenuItem itemVeiculo;
	
	private JDesktopPane mainFrame;
	private JInternalFrame internal;
		
	public class TrataEvento implements ActionListener,MouseListener{
		
		private JFrame parent;
		
		public TrataEvento(JFrame parent) {
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == itemMarca){
				internal.setTitle("Consultar Marcas");
				internal.setContentPane(new ConsultarMarca());
			}else if (e.getSource() == itemModelo){
				internal.setTitle("Consultar Modelos");
				internal.setContentPane(new ConsultarModelo());
			}else if (e.getSource() == itemVeiculo){
				internal.setTitle("Consultar Ve�culos");
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == menuSair){
				if (JOptionPane.showConfirmDialog(parent, "Desejar Sair?", "Confirma��o", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
				}
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
	

	public FormPrincipal(){
		super("Architect Systems");
		this.montarMenu();
		
		this.mainFrame = new JDesktopPane();
		
		this.internal = new JInternalFrame("Bem Vindo!", true,false,true,true);
		this.internal.pack();
		
		//efetuo o controle excess�es para tratamento caso n�o possa ser maximizado
		try{
			this.internal.setMaximum(true);
		}catch (Exception e){	
		}
		
		this.internal.setVisible(true);
		this.mainFrame.add(internal);
		
		this.getContentPane().add(this.mainFrame);
		this.setSize(new Dimension(400,300));
		this.setVisible(true);
		try{
			this.internal.setSelected(true);
		}catch (Exception e){
			
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void montarMenu(){
		this.barraMenu = new JMenuBar();
		this.menuCadastro = new JMenu("Cadastro");
		this.menuCadastro.setMnemonic('C');
		this.menuSair = new JMenu("Sair");
		this.menuSair.setMnemonic('S');
		
		//Adiciono os menus na barra de Menu	
		this.barraMenu.add(this.menuCadastro);
		this.barraMenu.add(this.menuSair);
		
		//Adiciono os itensMenu ao Menu
		this.itemMarca = new JMenuItem("Marca");
		this.itemMarca.setMnemonic('a');
		this.itemModelo = new JMenuItem("Modelo");
		this.itemModelo.setMnemonic('e');
		this.itemVeiculo = new JMenuItem("Veiculo");
		this.itemVeiculo.setMnemonic('o');
		
		this.menuCadastro.add(this.itemMarca);
		this.menuCadastro.add(this.itemModelo);
		this.menuCadastro.add(this.itemVeiculo);
		
		//Adiciono o menu no JFRAME
		this.setJMenuBar(this.barraMenu);
		
		TrataEvento trataEvento = new TrataEvento(this);
		this.itemMarca.addActionListener(trataEvento);
		this.itemModelo.addActionListener(trataEvento);
		this.itemVeiculo.addActionListener(trataEvento);
		this.menuSair.addMouseListener(trataEvento);
		
		
		
		//classe serializ�vel - quando a classe antes de passar pela rede � transformada em uma string, passa pela rede 
		//e na outra m�quina remonta ele como um objeto.
	}
}
