package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.AdminRepo;
import app.repository.ProfesorRepo;
import model.Prijava;
import model.Profesor;

@Controller
@RequestMapping(value="/prof")
public class ProfesorController {
	
	@Autowired
	ProfesorRepo pr;
	
	@RequestMapping(value="getPrijave",method=RequestMethod.GET)
	public String getPrijave(Model m, HttpServletRequest request) {
		Profesor p = (Profesor) request.getSession().getAttribute("korisnik");
		List<Prijava> prijave = pr.getSvePrijaveForProfesor(p);
		System.out.println(prijave.size());
		m.addAttribute("prijave",prijave);
		request.getSession().setAttribute("prijave",prijave);
		return "Profesor";
	}

}
