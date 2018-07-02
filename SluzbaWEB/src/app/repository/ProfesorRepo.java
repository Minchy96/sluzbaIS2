package app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import model.Prijava;
import model.Profesor;
import model.Student;

@Repository
@Transactional
public class ProfesorRepo {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Prijava> getSvePrijaveForProfesor(Profesor p){
		TypedQuery<Prijava> tq = em.createQuery("select p from Prijava p "
											  + "where p.predmet.profesor =:p and p.ocena=:o",Prijava.class);
		tq.setParameter("p", p);
		tq.setParameter("o", 0);
		List<Prijava> svePrijave = tq.getResultList();
		if (svePrijave == null)
			System.out.println("eto greske");
		else
			System.out.println("ipak ne");
		return svePrijave;
	}
	
	public Profesor getProfesorForLoginParameters(String korisnickoime, String lozinka) {
		Profesor p;
		try {
			TypedQuery<Profesor> tq = em.createQuery(
							"select p from Profesor p " + 
							"where p.korisnickoime =:ki " + 
							"and p.lozinka =:lozinka ",
					Profesor.class);
			tq.setParameter("ki", korisnickoime);
			tq.setParameter("lozinka", lozinka);
			p = tq.getSingleResult();
		} catch (Exception e) {
			p = new Profesor();
			e.printStackTrace();
		}
		return p;
	}
	
	public Prijava mergePrijava(Integer prijavaId, Integer ocena, Integer bodovi, String status){
		Prijava editPrj = em.find(Prijava.class, prijavaId);
		editPrj.setOcena(ocena);
		editPrj.setBodovi(bodovi);
		editPrj.setStatus(status);
		em.merge(editPrj);
		return editPrj;
	}

}
