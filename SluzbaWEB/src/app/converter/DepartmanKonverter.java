package app.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import app.repository.AdminRepo;
import model.Departman;

public class DepartmanKonverter implements Converter<String, Departman> {
	
	AdminRepo ar;
	
	public DepartmanKonverter(AdminRepo ar) {
		this.ar=ar;
	}
	
	@Override
	public Departman convert(String source) {
		int id=-1;
	       try{
	    	   id=Integer.parseInt(source);
	       }
	       catch(NumberFormatException e){
	    	   throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Departman.class),id, null);
	       }
	      Departman k=ar.getDepartman(id);
	      return k;
	}

}
