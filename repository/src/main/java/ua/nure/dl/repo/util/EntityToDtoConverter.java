package ua.nure.dl.repo.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class EntityToDtoConverter {

	public <Destination, Src> Destination convert(Src src, Class<Destination> target) {
		try {
			Destination dest = target.newInstance();
			System.out.println("Dest is: " + dest);
			Field[] destFields = dest.getClass().getDeclaredFields();
			Field[] srcFields = src.getClass().getDeclaredFields();
			
			 for (Field srcField: srcFields) {
				 for (Field destField: destFields) {
					 if (isSameFields(srcField, destField)) {
						 destField.setAccessible(true);
						 srcField.setAccessible(true);
						 
						 destField.set(dest, srcField.get(src));
					 }
				 }
			 }
			 
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private boolean isSameFields(Field src, Field des) {
		
		if (src.getType() != des.getType()) {
			return false;
		}
		
		String srcFieldName = src.getName().toLowerCase();
		String desFieldName = des.getName().toLowerCase();
		
		return srcFieldName.contains(desFieldName) || desFieldName.contains(srcFieldName);
		
	}

}
