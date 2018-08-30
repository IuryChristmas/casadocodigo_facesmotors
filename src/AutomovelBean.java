import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

@ManagedBean(name="automovelBean")
public class AutomovelBean {
	private Automovel automovel = new Automovel();
	private List<Automovel> automoveis;

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}
	
	public List<Automovel> getAutomoveis() {
		if(this.automoveis == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select a from Automovel a", Automovel.class);
			
			this.automoveis = q.getResultList();
			
			em.close();	
		}
		
		return this.automoveis;
	}

	public void salva(Automovel automovel) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(automovel);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public void excluir(Automovel automovel) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		automovel = em.merge(automovel);
		em.remove(automovel);
		
		tx.commit();
		em.close();
	}
}
