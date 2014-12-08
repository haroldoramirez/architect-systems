package br.udc.edu.sistemas.gui;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.udc.edu.sistemas.dao.DaoMarca;
import br.udc.edu.sistemas.entity.Marca;

public class ConsultarMarca extends ConsultarGenerico {
	
private static final long serialVersionUID = 1L;
	
	private JTextField jtfDescricao;
	

	@Override
	protected void criaCampos() {
		this.jtfDescricao = new JTextField();
		this.painelCampos.setLayout(new GridLayout(0,3));
		this.painelCampos.add(new JLabel("Descrição:"));
		this.painelCampos.add(this.jtfDescricao);
		
		
	}

	@Override
	protected void consultar() {
		
		Marca marca = new Marca();
		marca.setDescricao(this.jtfDescricao.getText());
		DaoMarca daoMarca = new DaoMarca();
		try {
			this.tabelaConsultar.setListData(daoMarca.find(marca));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro na consulta!");
		}
		
	}

	@Override
	protected void novo() {
		this.getInternalFrame().setTitle("Manter Marca");
		this.getInternalFrame().setContentPane(new ManterMarca());
		
	}

	@Override
	protected void limpar() {
		this.jtfDescricao.setText("");
		this.tabelaConsultar.setListData(new Object[0]);
	}

	@Override
	protected void detalhe() {
		Marca marca = (Marca) this.tabelaConsultar.getSelectedValue();
		ManterMarca manterMarca = new ManterMarca();
		manterMarca.setMarca(marca);
		this.getInternalFrame().setTitle("Manter Marca");
		this.getInternalFrame().setContentPane(manterMarca);
		
		
	}

	@Override
	protected void imprimir() {
		
	}
	
	
}
