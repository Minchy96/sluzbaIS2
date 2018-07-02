package app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import app.repository.AdminRepo;
import model.Departman;
import model.Profesor;

public class ProfesorKonverter implements Converter<String,Profesor> {

	
	@Autowired
	AdminRepo ar;
	
	public ProfesorKonverter(AdminRepo ar) {
		this.ar=ar;
	}
	
	@Override
	public Profesor convert(String source) {
		int id=-1;
	       try{
	    	   id=Integer.parseInt(source);
	       }
	       catch(NumberFormatException e){
	    	   throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Profesor.class),id, null);
	       }
	      Profesor p=ar.getProfesorForID(id);
	      return p;
	}

}
