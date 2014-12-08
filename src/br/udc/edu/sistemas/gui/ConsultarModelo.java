package br.udc.edu.sistemas.gui;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.udc.edu.sistemas.dao.DaoMarca;
import br.udc.edu.sistemas.dao.DaoModelo;
import br.udc.edu.sistemas.entity.Marca;
import br.udc.edu.sistemas.entity.Modelo;

public class ConsultarModelo extends ConsultarGenerico {

private static final long serialVersionUID = 1L;
	
	private JTextField jtfDescricao;
	private JComboBox jcmbMarca;
	

	@Override
	protected void criaCampos() {
		this.jtfDescricao = new JTextField();
		this.painelCampos.setLayout(new GridLayout(0,3));
		this.painelCampos.add(new JLabel("Descrição:"));
		this.painelCampos.add(this.jtfDescricao);
		this.painelCampos.add(new JLabel(""));
		
		this.jcmbMarca = new JComboBox ();
		this.painelCampos.add(new JLabel("Marca:"));
		this.painelCampos.add(this.jcmbMarca);
		this.painelCampos.add(new JLabel(""));
		
		DaoMarca daoMarca = new DaoMarca();
		try {
			Marca listaMarca[] = daoMarca.find(null);
			this.jcmbMarca.addItem("");
			for (int i = 0; i < listaMarca.length; i++) {
				this.jcmbMarca.addItem(listaMarca[0]);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar a lista de Marcas!");
		}
		
	}

	@Override
	protected void consultar() {
		
		Modelo modelo = new Modelo();
		modelo.setDescricao(this.jtfDescricao.getText());
		if (this.jcmbMarca.getSelectedIndex() > 0) {
			modelo.setMarca((Marca) this.jcmbMarca.getSelectedItem());
		}
		DaoModelo daoModelo = new DaoModelo();
		try {
			this.tabelaConsultar.setListData(daoModelo.find(modelo));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro na consulta!");
		}
		
	}

	@Override
	protected void novo() {
		this.getInternalFrame().setTitle("Manter Modelo");
		this.getInternalFrame().setContentPane(new ManterModelo());
		
	}

	@Override
	protected void limpar() {
		this.jtfDescricao.setText("");
		this.jcmbMarca.setSelectedItem(0);
		this.tabelaConsultar.setListData(new Object[0]);
	}

	@Override
	protected void detalhe() {
		Modelo modelo = (Modelo) this.tabelaConsultar.getSelectedValue();
		ManterModelo manterModelo = new ManterModelo();
		manterModelo.setModelo(modelo);
		this.getInternalFrame().setTitle("Manter Modelo");
		this.getInternalFrame().setContentPane(manterModelo);
		
		
	}

	@Override
	protected void imprimir() {
		
	}
	
}
