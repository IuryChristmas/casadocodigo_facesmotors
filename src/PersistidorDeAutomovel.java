import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityTransaction;

public class PersistidorDeAutomovel {
	
	public static void main(String[] args) {
	
		EntityManager em = JPAUtil.getEntityManager();
		
		/*Automovel auto = new Automovel();
		auto.setAnoFabricacao(2014);
		auto.setMarca("Ferrari");
		auto.setModelo("La Ferrari");
		auto.setObservacoes("Batido várias vezes");
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(auto);
		tx.commit();*/
		
		/*Query q = em.createQuery("select a from Automovel a", Automovel.class);
		List<Automovel> autos = q.getResultList();
		
		for(Automovel a : autos) {
			System.out.println(a.getMarca());
		}*/
		
		Automovel auto = em.getReference(Automovel.class, 1L);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(auto);
		tx.commit();
		
		em.close();
		JPAUtil.closeEntityManager();
		//emf.close();
	}

}
