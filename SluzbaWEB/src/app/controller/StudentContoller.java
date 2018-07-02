package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.StudentRepo;
import model.Predmet;
import model.Prijava;
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
		return "Student";
	}
	
	@RequestMapping(value="savePrijava", method=RequestMethod.POST)
	public String savePrijava(@ModelAttribute("prijava") Prijava prijava,BindingResult r, Model m, HttpServletRequest request, String predmet) {
		Student s  = (Student) request.getSession().getAttribute("korisnik");
		Predmet p = sr.getPredmetForID(predmet);
		sr.prijaviPredmet(s.getBrindeksa(), prijava.getDatumprijave(), prijava.getIspitnirok(), prijava.getBrojizlazaka(), p);
		m.addAttribute("prijava",prijava);
		return "Student";
	}
	
//	@RequestMapping(value="savePrijava", method=RequestMethod.POST)
//	public String savePrijava(Model m, Date datumprijave, String ispitnirok, Integer brojizlazaka, Integer predmet , HttpServletRequest request) {
//		Student s  = (Student) request.getSession().getAttribute("korisnik");
//		System.out.println("usao sam");
//		if (s==null)
//			System.out.println("ovde gresim.");
//		Predmet p = sr.getPredmetForID(predmet);
//		Prijava prijava = sr.prijaviPredmet(s.getBrindeksa(), datumprijave,ispitnirok, brojizlazaka, p);
//		m.addAttribute("prijava",prijava);
//		return "Student";
//	}
	
	@RequestMapping(value="/prijava", method=RequestMethod.GET)
	public String initialize(Model m){
		m.addAttribute("prijava", new Prijava());
		System.out.println("bio");
		return "Student";
	}
	
//	@ModelAttribute("prijava")
//	public Prijava getPrijava(){
//		System.out.println("usao!!!!!!!!!!!");
//		return new Prijava();
//	}
	
	
	@InitBinder void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
}
