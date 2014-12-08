package br.udc.edu.sistemas.entity;

public class Entity {
	
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (this.getClass() == obj.getClass())) {
			Entity entityAux = (Entity) obj;
			if (this.id == entityAux.getId()) {
				return true;
			}
		}
		return false;
	}

}
