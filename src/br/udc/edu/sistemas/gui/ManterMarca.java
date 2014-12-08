package br.udc.edu.sistemas.gui;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.udc.edu.sistemas.dao.DaoMarca;
import br.udc.edu.sistemas.entity.Marca;

public class ManterMarca extends ManterGenerico{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField jtfId;
	private JTextField jtfDescricao;

	@Override
	protected void criaCampos() {
		this.jtfId = new JTextField();
		this.jtfDescricao = new JTextField();
		this.painelCampos.setLayout(new GridLayout(0, 3));
		this.painelCampos.add(new JLabel("Id"));
		this.painelCampos.add(this.jtfId);
		this.painelCampos.add(new JLabel(""));
		this.painelCampos.add(new JLabel("Descrição"));
		this.painelCampos.add(this.jtfDescricao);
		this.painelCampos.add(new JLabel(""));
		this.btExcluir.setEnabled(false);
	}

	@Override
	protected void salvar() {
		Marca marca = new Marca();
		marca.setId(Integer.parseInt(this.jtfId.getText()));
		marca.setDescricao(this.jtfDescricao.getText());
		DaoMarca daoMarca = new DaoMarca();
		try {
			daoMarca.save(marca);
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
		return true;
	}

	@Override
	protected void excluir() {
		try {
			Marca marca = new Marca();
			marca.setId(Integer.parseInt(this.jtfId.getText()));
			DaoMarca daoMarca = new DaoMarca();
			daoMarca.delete(marca);
			this.limpar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir!");
		}
	}

	@Override
	protected void limpar() {
		this.jtfId.setText("");
		this.jtfDescricao.setText("");	
		this.jtfId.setEnabled(true);
		this.btExcluir.setEnabled(false);
	}

	@Override
	protected void voltar() {
		this.getInternalFrame().setTitle("Consultar Marca");
		this.getInternalFrame().setContentPane(new ConsultarMarca());
	}
	
	public void setMarca(Marca marca) {
		this.jtfId.setText(String.valueOf(marca.getId()));
		this.jtfDescricao.setText(marca.getDescricao());
		this.jtfId.setEnabled(false);
		this.btExcluir.setEnabled(true);
	}

}
