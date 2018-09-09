package br.com.casadocodigo.jsfjpa.controllers;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.casadocodigo.jsfjpa.entities.Automovel;
import br.com.casadocodigo.jsfjpa.entities.Marca;
import br.com.casadocodigo.jsfjpa.persistence.JPAUtil;

@ManagedBean(name="automovelBean")
public class AutomovelBean {
	private Automovel automovel = new Automovel();
	private List<Automovel> automoveis;
	private Marca marca;

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

	public String salvar(Automovel auto) {
		EntityManager em = JPAUtil.getEntityManager();
		em.persist(auto);
		
		JPAUtil.evictCache(em, Automovel.LISTAR_DESTAQUES);
		
		FacesMessage msg = new FacesMessage("Automovel salvo com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return "listar";
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
