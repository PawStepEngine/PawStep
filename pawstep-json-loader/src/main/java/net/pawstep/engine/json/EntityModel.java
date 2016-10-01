package net.pawstep.engine.json;

import java.util.ArrayList;
import java.util.List;

public class EntityModel {
	
	public String name;
	
	public List<EntityModel> children = new ArrayList<>();
	public List<ComponentModel> components = new ArrayList<>();
	
	public EntityModel(String name) {
		this.name = name;
	}
	
}
