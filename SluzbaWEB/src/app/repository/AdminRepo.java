package app.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Departman;
import model.Predmet;
import model.Profesor;
import model.Student;

@Repository
@Transactional
public class AdminRepo {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Departman> getDepartmani(){
		try{
			Query q=em.createQuery("from Departman d");
			return q.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Departman getDepartman(int id){
		try{
			Departman k=em.find(Departman.class, id);
			return k;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Profesor savucajProfesora (String ime, String prezime,     Departman dep, 
								    String adresa,  Date datumRodjenja, String zvanje, 
			                        String lozinka, String korisnickoIme) {
		Profesor retp = null;
		try {
			retp = new Profesor();
			retp.setIme(ime);
			retp.setPrezime(prezime);
			retp.setDepartman(dep);
			retp.setAdresa(adresa);
			retp.setDatumrodjenja(datumRodjenja);
			retp.setZvanje(zvanje);
			retp.setLozinka(lozinka);
			retp.setKorisnickoime(korisnickoIme);
		em.persist(retp);
		} catch (Exception e) {
			retp = null;
			e.printStackTrace();
		}
		return retp;
	}
	
	public Predmet sacuvajPredmet(String naziv, Integer semestar, Integer godinaSlusanja,
			  String tip,   Integer espb,      Profesor prof){
		Predmet pred;
		try {
			pred = new Predmet();
			pred.setNaziv(naziv);
			pred.setSemestar(semestar);
			pred.setGodinaslusanja(godinaSlusanja);
			pred.setEspb(espb);
			pred.setTip(tip);
			pred.setProfesor(prof);
			em.persist(pred);
		} catch (Exception e) {
			pred = null;
			e.printStackTrace();
		}
		return pred;
		
	}
	
	public Student sacuvajStudenta(String ime, String prezime, String adresa,
			   Date datumRodj, String lozinka, String brojIndeksa,
			   Departman departman, Integer godinaStudija){
		Student stud;
		try {
			stud = new Student();
			stud.setBrindeksa(brojIndeksa);
			stud.setDepartman(departman);
			stud.setIme(ime);
			stud.setPrezime(prezime);
			stud.setDatumrodjenja(datumRodj);
			stud.setAdresa(adresa);
			stud.setLozinka(lozinka);
			stud.setGodinastudija(godinaStudija);
			em.persist(stud);
		} catch (Exception e) {
			stud = null;
			e.printStackTrace();
		}
		return stud;
	}
	
	 public Departman getDepartmandForID(Integer id){
		 Departman d = em.find(Departman.class,id);
		 return d;
	 }
	

	public Profesor getProfesorForID(Integer id){
		Profesor p = em.find(Profesor.class, id);
		return p;
	}
	
	public List<Departman> getAllDepartmans(){
		TypedQuery<Departman> tq = em.createNamedQuery("Departman.findAll",Departman.class);		
		List<Departman> allDeps = tq.getResultList();
		return allDeps;
	}
	
	public List<Profesor> sviProfesoriSaDepartmana(String departman){
		TypedQuery<Profesor> tq = 
				em.createQuery(
						  "select p from Profesor p "
						+ "where p.departman.naziv =:departman"
						,Profesor.class
				);
		tq.setParameter("departman", departman);
		List<Profesor> profesoriSaDep = tq.getResultList();
		return profesoriSaDep;
	}
	
	
	public List<Profesor> sviProfesori(){
		TypedQuery<Profesor> tq = em.createNamedQuery("Profesor.findAll",Profesor.class);		
		List<Profesor> profesoriSaDep = tq.getResultList();
		return profesoriSaDep;
	}

	public List<String> getKorisnickaImena(){
		TypedQuery<String> tq = 
				em.createQuery("select p.korisnickoime "
							 + "from Profesor p ",String.class);
		List<String> rez = tq.getResultList();
		return rez;
	}
	
	public Profesor getProfesorForKorisnicko(String korisnicko){
		TypedQuery<Profesor> tq = 
				em.createQuery("select p from Profesor p "
							 + "where p.korisnickoime =:korisnicko "
		        ,Profesor.class);
		tq.setParameter("korisnicko", korisnicko);
		Profesor p = tq.getSingleResult();
		return p;
	}

}
