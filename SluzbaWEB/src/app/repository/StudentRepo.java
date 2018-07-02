package app.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Departman;
import model.Predmet;
import model.Prijava;
import model.Student;

@Repository
@Transactional
public class StudentRepo {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Prijava> prijaviPredmete(String brIndeksa, Date datumPrijave, String ispitniRok, Integer brojIzlazaka, List<Predmet> predmeti){
		Student stud =getStudentForBrIndeksa(brIndeksa);
		//profesorove aktivnosti
		String status = "nepolozen";
		Integer ocena = 0;
		Integer bodovi = 0;
		List<Prijava> retValPrijave = new ArrayList<>();
		try {
			for(Predmet pred: predmeti){
				Prijava prij = new Prijava();
				prij.setDatumprijave(datumPrijave);
				prij.setIspitnirok(ispitniRok);
				prij.setBrojizlazaka(brojIzlazaka);
				prij.setStatus(status);
				prij.setOcena(ocena);
				prij.setBodovi(bodovi);
				prij.setPredmet(pred);
				prij.setStudent(stud);
				em.persist(prij);
				retValPrijave.add(prij);
			}
			em.flush();
		}catch (Exception e) {
			retValPrijave = null;
			e.printStackTrace();
		}
		return retValPrijave;
	}
	
	public Prijava prijaviPredmet(String brIndeksa, Date datumPrijave, String ispitniRok, Integer brojIzlazaka, Predmet pred){
		Student stud =getStudentForBrIndeksa(brIndeksa);
		//profesorove aktivnosti
		String status = "nepolozen";
		Integer ocena = 0;
		Integer bodovi = 0;
		Prijava prij=new Prijava();
		try {
				prij.setDatumprijave(datumPrijave);
				prij.setIspitnirok(ispitniRok);
				prij.setBrojizlazaka(brojIzlazaka);
				prij.setStatus(status);
				prij.setOcena(ocena);
				prij.setBodovi(bodovi);
				prij.setPredmet(pred);
				prij.setStudent(stud);
				em.persist(prij);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return prij;
	}
	
		
	
	public List<Predmet> getPredmetsForDepartman(Departman dep) {
		TypedQuery<Predmet> tq = em.createQuery("select p from Predmet p " + "where p.profesor.departman =:dep",
				Predmet.class);
		tq.setParameter("dep", dep);
		List<Predmet> predmetiSaDep = tq.getResultList();
		return predmetiSaDep;
	}
	
	public Student getStudentForBrIndeksa(String brIndeksa) {
		TypedQuery<Student> tq = em.createQuery("select s from Student s " + "where s.brindeksa =:brIndeksa ",
				Student.class);
		tq.setParameter("brIndeksa", brIndeksa);
		Student s;
		try {
			s = tq.getSingleResult();
		} catch (Exception e) {
			s = null;
			e.printStackTrace();
		}
		return s;
	}
	
	public Student getStudentForLoginParameters(String brIndeksa, String lozinka) {
		Student s;
		try {
			TypedQuery<Student> tq = em.createQuery(
							"select s from Student s " + 
							"where s.brindeksa =:brIndeksa " + 
							"and s.lozinka =:lozinka ",
					Student.class);
			tq.setParameter("brIndeksa", brIndeksa);
			tq.setParameter("lozinka", lozinka);
			s = tq.getSingleResult();
			if (s == null)
				System.out.println("zasto");
			return s;
		} catch (Exception e) {
			s = new Student();
			e.printStackTrace();
		}
		return null;
	}

}
