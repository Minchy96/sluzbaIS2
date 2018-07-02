package app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.ProfesorRepo;
import app.repository.StudentRepo;
import model.Prijava;
import model.Profesor;
import model.Student;

@Controller
@RequestMapping(value="/home")
public class HomeContoller {
	
	@Autowired
	StudentRepo sr;
	
	@Autowired
	ProfesorRepo pr;

	@RequestMapping(value="validacija", method=RequestMethod.POST)
	public String validacija(String korisnickoIme, String sifra,Model m, HttpServletRequest request) {
		if (korisnickoIme.contains("/")) {
			Student s = sr.getStudentForLoginParameters(korisnickoIme, sifra);
			request.getSession().setAttribute("korisnik", s);
			return "Student";
		} else {
			Profesor  p = pr.getProfesorForLoginParameters(korisnickoIme, sifra);
			request.getSession().setAttribute("korisnik", p);
			return "Profesor";
		}
	}

}
