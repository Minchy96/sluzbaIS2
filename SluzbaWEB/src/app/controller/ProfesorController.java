package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.AdminRepo;
import app.repository.ProfesorRepo;
import model.Prijava;
import model.Profesor;
import model.Student;

@Controller
@RequestMapping(value="/prof")
public class ProfesorController {
	
	@Autowired
	ProfesorRepo pr;
	
	@RequestMapping(value="getPrijave",method=RequestMethod.GET)
	public String getPrijave(Model m, HttpServletRequest request) {
		Profesor p = (Profesor) request.getSession().getAttribute("korisnik");
		List<Prijava> prijave = pr.getSvePrijaveForProfesor(p);
		m.addAttribute("prijave",prijave);
		request.getSession().setAttribute("prijave",prijave);
		return "Profesor";
	}
	
	@RequestMapping(value="upadtePrijave",method=RequestMethod.POST)
	public String updatePrijave(Model m, HttpServletRequest request, @ModelAttribute("prijava") Prijava prijava, BindingResult br) {
		pr.mergePrijava(prijava.getPrijavaid(), prijava.getOcena(), prijava.getBodovi(), prijava.getStatus());
		m.addAttribute("prijava", prijava);
//		Prijava jednaPrijava = pr.getPrijava(prijava.getPrijavaid());
//		List<Prijava> p = (List<Prijava>) request.getSession().getAttribute("prijave");
//		p.remove(jednaPrijava);
//		request.getSession().setAttribute("prijave",p);
		Profesor p = (Profesor) request.getSession().getAttribute("korisnik");
		List<Prijava> prijave = pr.getSvePrijaveForProfesor(p);
		m.addAttribute("prijave",prijave);
		request.getSession().setAttribute("prijave",prijave);
		return "Profesor";
	}
	
	@RequestMapping(value="/prijava", method=RequestMethod.GET)
	public String initializeee(Model m){
		m.addAttribute("prijava", new Prijava());
		return "Profesor";
	}
}
