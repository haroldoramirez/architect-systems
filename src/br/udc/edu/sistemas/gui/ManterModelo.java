package br.udc.edu.sistemas.gui;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.udc.edu.sistemas.dao.DaoMarca;
import br.udc.edu.sistemas.dao.DaoModelo;
import br.udc.edu.sistemas.dao.DaoModelo;
import br.udc.edu.sistemas.entity.Marca;
import br.udc.edu.sistemas.entity.Modelo;
import br.udc.edu.sistemas.entity.Modelo;

public class ManterModelo extends ManterGenerico{
	
private static final long serialVersionUID = 1L;
	
	private JTextField jtfId;
	private JTextField jtfDescricao;
	private JComboBox jcmbMarca;

	@Override
	protected void criaCampos() {
		
		this.painelCampos.setLayout(new GridLayout(0, 3));
		this.painelCampos.add(new JLabel("Id"));
		this.painelCampos.add(this.jtfId);
		this.painelCampos.add(new JLabel(""));
		
		this.jtfId = new JTextField();
		this.jtfDescricao = new JTextField();
		this.painelCampos.add(new JLabel("Descrição"));
		this.painelCampos.add(this.jtfDescricao);
		this.painelCampos.add(new JLabel(""));
		this.btExcluir.setEnabled(false);
		
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
	protected void salvar() {
		Modelo modelo = new Modelo();
		modelo.setId(Integer.parseInt(this.jtfId.getText()));
		modelo.setDescricao(this.jtfDescricao.getText());
		modelo.setMarca((Marca) this.jcmbMarca.getSelectedItem());
		DaoModelo daoModelo = new DaoModelo();
		try {
			daoModelo.save(modelo);
			this.limpar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar!");
		}	
	}

	@Override
	protected boolean validar() {
		if ((this.jtfId).getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Preencha o ID!");
			this.jtfId.requestFocus();
			return false;
		}else {
			try {
				Integer.parseInt(this.jtfId.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "ID inválido!");
				this.jtfId.requestFocus();
				return false;
			}
		}
		
		if ((this.jtfDescricao).getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Preencha a Descrição!");
			this.jtfDescricao.requestFocus();
			return false;
			}
		
		if (this.jcmbMarca.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione a Marca");
			this.jtfDescricao.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected void excluir() {
		try {
			Modelo modelo = new Modelo();
			modelo.setId(Integer.parseInt(this.jtfId.getText()));
			DaoModelo daoModelo = new DaoModelo();
			daoModelo.delete(modelo);
			this.limpar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir!");
		}
	}

	@Override
	protected void limpar() {
		this.jtfId.setText("");
		this.jtfDescricao.setText("");	
		this.jcmbMarca.setSelectedIndex(0);
		this.jtfId.setEnabled(true);
		this.btExcluir.setEnabled(false);
	}

	@Override
	protected void voltar() {
		this.getInternalFrame().setTitle("Consultar Modelo");
		this.getInternalFrame().setContentPane(new ConsultarModelo());
	}
	
	public void setModelo(Modelo modelo) {
		this.jtfId.setText(String.valueOf(modelo.getId()));
		this.jtfDescricao.setText(modelo.getDescricao());
		this.jcmbMarca.setSelectedItem(modelo.getMarca());
		this.jtfId.setEnabled(false);
		this.btExcluir.setEnabled(true);
	}

	
}
