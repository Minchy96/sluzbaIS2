package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.StudentRepo;
import model.Predmet;
import model.Prijava;
import model.Profesor;
import model.Student;

@Controller
@RequestMapping(value="/stud")
public class StudentContoller {

	@Autowired
	StudentRepo sr;
	
	@RequestMapping(value="getPredmeti",method=RequestMethod.GET)
	public String getProfesori(Model m, HttpServletRequest request) {
		Student s  = (Student) request.getSession().getAttribute("korisnik");
		List<Predmet> pred = sr.getPredmetsForDepartman(s.getDepartman());
		m.addAttribute("predmeti", pred);
		request.getSession().setAttribute("predmeti", pred);
		System.out.println("ovo sam uradio");
		return "Student";
	}
	
	@RequestMapping(value="savePrijava", method=RequestMethod.POST)
	public String savePrijava(Model m, @ModelAttribute("prijava") Prijava prijava, HttpServletRequest request) {
		Student s  = (Student) request.getSession().getAttribute("korisnik");
		System.out.println("usao sam");
		if (s==null)
			System.out.println("ovde gresim.");
		sr.prijaviPredmet(s.getBrindeksa(), prijava.getDatumprijave(), prijava.getIspitnirok(), prijava.getBrojizlazaka(), prijava.getPredmet());
		m.addAttribute("prijava",prijava);
		return "Student";
	}
	
	@RequestMapping(value="/prijava", method=RequestMethod.GET)
	public String initializeee(Model m){
		m.addAttribute("prijava", new Prijava());
		System.out.println("bio");
		return "Student";
	}
	
}
