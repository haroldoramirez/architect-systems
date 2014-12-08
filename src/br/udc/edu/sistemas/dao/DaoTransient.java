package br.udc.edu.sistemas.dao;

import java.util.ArrayList;
//import java.util.Collection;
import br.udc.edu.sistemas.entity.Entity;

public class DaoTransient {

	private Entity listEntityOld[];
	private ArrayList <Entity> listEntity;
	
	public DaoTransient() {
		this.listEntityOld = new Entity[0];
		this.listEntity = new ArrayList <Entity>();
	}
	
	public void save(Entity entity) {
		if ((entity != null) && (entity.getId() != null)) {
			this.listEntity.remove(entity);
			this.listEntity.add(entity);
		}
	}
	
	public void saveOld(Entity entity) {
		if ((entity != null) && (entity.getId() != null)) {
			if (this.findByPrimaryKeyOld(entity) != null) {
				for (int i = 0; i < this.listEntityOld.length; i++) {
					if (entity.equals(this.listEntityOld[i])) {
						listEntityOld[i] = entity;
						break;
					}
				}
			} else {
				Entity list[] = new Entity[this.listEntityOld.length + 1];
				for (int i = 0; i < this.listEntityOld.length; i++) {
					list[i] = this.listEntityOld[i];
				}
				list[list.length - 1] = entity;
				this.listEntityOld = list;
			}
		}
	}

	public void deleteOld(Entity entity) {
		if (this.findByPrimaryKeyOld(entity) != null) {
			Entity list[] = new Entity[this.listEntityOld.length - 1];
			int j = 0;
			for (int i = 0; i < this.listEntityOld.length; i++) {
				if (!entity.equals(this.listEntityOld[i])) {
					list[j] = listEntityOld[i];
					j++;
				}
			}
			this.listEntityOld = list;
		}
	}
	
	public Entity[] findOld(Entity entity) {
		if (entity == null) {
			return this.listEntityOld;
		} else if (entity.getId() != null) {
			for (int i = 0; i < this.listEntityOld.length; i++) {
				if (entity.equals(this.listEntityOld[i])) {
					Entity result[] = new Entity[1];
					result[0] = this.listEntityOld[i];
					return result;
				}
			}
			return null;
		}
		return this.listEntityOld;
	}
	
	public Entity findByPrimaryKeyOld(Entity entity) {
		if ((entity != null) && (entity.getId() != null)) {
			Entity list[] = this.findOld(entity);
			if (list != null) {
				return list[0];
			}
		}
		return null;
	}
}
