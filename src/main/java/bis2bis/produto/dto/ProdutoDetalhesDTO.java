package bis2bis.produto.dto;

import java.util.ArrayList;
import java.util.List;

import bis2bis.models.Produto;
import bis2bis.models.ProdutoTecnologias;

public class ProdutoDetalhesDTO {

	private String nomeProduto;
	private String descricao;
	private String mercadoAlvo;
	private List<String> tecnologias;
	
	public ProdutoDetalhesDTO (Produto produto) {
		this.nomeProduto = produto.getNomeProduto();
		this.descricao = produto.getDescricao();
		this.mercadoAlvo = produto.getMercadoAlvo();
		this.tecnologias = new ArrayList<>();
		
		for(ProdutoTecnologias pt : produto.getProdutoTecnologias()) {
			this.tecnologias.add(new ProdutoTecnologiasDetalhesDTO(pt).toString());
		}
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMercadoAlvo() {
		return mercadoAlvo;
	}

	public void setMercadoAlvo(String mercadoAlvo) {
		this.mercadoAlvo = mercadoAlvo;
	}

	public List<String> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(List<String> tecnologias) {
		this.tecnologias = tecnologias;
	}


	
}
