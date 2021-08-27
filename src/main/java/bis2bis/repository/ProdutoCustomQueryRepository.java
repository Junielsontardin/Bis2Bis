package bis2bis.repository;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import bis2bis.models.Produto;


@Repository
public class ProdutoCustomQueryRepository {

	private final EntityManager em;

	public ProdutoCustomQueryRepository(EntityManager em) {
		this.em = em;
	}
	
	public List<Produto> findProdutoTecnologias(List<Long> ids) {
		String query = "SELECT Produto.id, Produto.descricao, Produto.mercadoAlvo, Produto.nomeProduto FROM Produto p, ProdutoTecnologias pt WHERE";
		String condicao = "WHERE";
		
		for(Long id : ids) {
			query += condicao + " p.id = pt.produto.id and (" + id + " is null or pt.tecnologia.id = " + id + ")";
			condicao = "AND";
		}
		
		var q = em.createQuery(query, Produto.class);
		
		//q.setParameter("id", id);
		
		for(Long id : ids) {
			q.setParameter("id", id);
		}
		
		return q.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}
}
