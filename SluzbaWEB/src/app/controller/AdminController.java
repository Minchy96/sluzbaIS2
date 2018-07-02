package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import app.repository.AdminRepo;
import app.repository.StudentRepo;
import model.Departman;
import model.Predmet;
import model.Profesor;
import model.Student;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	AdminRepo ar;
	
	@Autowired
	StudentRepo sr;
	
	@RequestMapping(value="getDepartmani",method=RequestMethod.GET)
	public String getDepartmani(Model m, HttpServletRequest request) {
		List<Departman> dep = ar.getAllDepartmans();
		m.addAttribute("departmani", dep);
		request.getSession().setAttribute("departmani",dep);
		return "Admin";
	}
	
	@RequestMapping(value="getProfesori",method=RequestMethod.GET)
	public String getProfesori(Model m, HttpServletRequest request) {
		List<Profesor> prof = ar.sviProfesori();
		m.addAttribute("profesori", prof);
		request.getSession().setAttribute("profesori", prof);
		return "Admin";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(value="saveProfesor", method=RequestMethod.POST)
	public String sacuvajProfesora(Model m, @ModelAttribute("profesor") Profesor profesor, HttpServletRequest request) {
		ar.savucajProfesora(profesor.getIme(),profesor.getPrezime(), profesor.getDepartman(), profesor.getAdresa(), profesor.getDatumrodjenja(),profesor.getZvanje(), profesor.getLozinka(), profesor.getKorisnickoime());
		m.addAttribute("profesor",profesor);
		return "Admin";
	}
	
	@RequestMapping(value="savePredmet", method=RequestMethod.POST)
	public String sacuvajPredmet(Model m, @ModelAttribute("predmet") Predmet predmet, HttpServletRequest request) {
		ar.sacuvajPredmet(predmet.getNaziv(), predmet.getSemestar(),predmet.getGodinaslusanja(), predmet.getTip(), predmet.getEspb(), predmet.getProfesor());
		m.addAttribute("predmet", predmet);
		return "Admin";
	}
	
	@RequestMapping(value="saveStudent", method=RequestMethod.POST)
	public String sacuvajStudent(Model m, @ModelAttribute("student") Student student, HttpServletRequest request) {
		ar.sacuvajStudenta(student.getIme(), student.getPrezime(), student.getAdresa(), student.getDatumrodjenja(), student.getLozinka(), student.getBrindeksa(), student.getDepartman(), student.getGodinastudija());
		m.addAttribute("student", student);
		return "Admin";
	}
	
	@ModelAttribute("profesor")
	public Profesor getProfesor(){
		System.out.println("usao!!!!!!!!!!!");
		return new Profesor();
	}

	
//	@RequestMapping(value="/profesor", method=RequestMethod.GET)
//	public String initialize(Model m){
//		m.addAttribute("profesor", new Profesor());
//		return "Admin";
//	}
	
	@RequestMapping(value="/predmet", method=RequestMethod.GET)
	public String initializee(Model m){
		m.addAttribute("predmet", new Predmet());
		return "Admin";
	}
	
	@RequestMapping(value="/student", method=RequestMethod.GET)
	public String initializeee(Model m){
		m.addAttribute("student", new Student());
		return "Admin";
	}
	


}
