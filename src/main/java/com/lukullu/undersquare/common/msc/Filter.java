package com.lukullu.undersquare.common.msc;

import com.lukullu.undersquare.game.entity.Entity;
import com.lukullu.undersquare.game.entity.projectile.Projectile;

import java.util.ArrayList;

public class Filter {
	
	public static ArrayList<Entity> filterEntities(ArrayList<Entity> list, Entity e){
		
		for(int i = 0; i < list.size(); i++){
			
			if(list.get(i).getClass().getSuperclass() == e.getClass()){ list.remove(list.get(i)); }
			
		}
		
		return list;
		
	}
	
	public static ArrayList<Entity> filterOwnPrj(ArrayList<Entity> list, Entity e){
		
		ArrayList<Entity> output = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++){
			
			if(list.get(i) instanceof Projectile) { Projectile temp = (Projectile) list.get(i); if(temp.origin != e) output.add(list.get(i)); } else output.add(list.get(i));
			
		}
		
		return output;
	}

	public static ArrayList<Entity> filterBrotherPrj(ArrayList<Entity> list, Projectile e){

		ArrayList<Entity> output = new ArrayList<>();

		for(int i = 0; i < list.size(); i++){

			if(list.get(i) instanceof Projectile) { Projectile temp = (Projectile) list.get(i); if(temp.origin != e.origin) output.add(list.get(i)); };

		}

		return output;
	}


}
