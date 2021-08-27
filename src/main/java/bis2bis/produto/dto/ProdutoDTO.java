package bis2bis.produto.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import bis2bis.exception.MensagemException;
import bis2bis.models.Produto;
import bis2bis.models.ProdutoTecnologias;
import bis2bis.models.Tecnologia;
import bis2bis.repository.ProdutoRepository;
import bis2bis.repository.ProdutoTecnologiasRepository;
import bis2bis.repository.TecnologiaRepository;

public class ProdutoDTO {
	
	@NotNull
	@NotBlank
	private String nomeProduto;
	
	@NotNull
	@NotBlank
	private String descricao;
	
	@NotNull
	@NotBlank
	private String mercadoAlvo;
	
	@NotNull
	@NotBlank
	private ArrayList<String> tecnologias = new ArrayList<>();
	
	public Produto toProduto() {
			
		Produto produto = new Produto();	
		produto.setNomeProduto(this.nomeProduto);
		produto.setDescricao(this.descricao);
		produto.setMercadoAlvo(this.mercadoAlvo);		
				
		return produto;
	}

	public List<ProdutoTecnologias> toTecnologias(ProdutoRepository produtoRepository, TecnologiaRepository tecnologiaRepository, ProdutoTecnologiasRepository produtoTecnologiasRepository, Produto produto) throws MensagemException {
			
		List<ProdutoTecnologias> pt = new ArrayList<>();
		
		for(String t : this.tecnologias) {
			ProdutoTecnologias produtoTecnologias = new ProdutoTecnologias();
			Optional<Tecnologia> tecno = tecnologiaRepository.findByTecnologia(t);
			
			if(tecno.isEmpty()) {
				Tecnologia tecnologia = new Tecnologia();
				tecnologia.setTecnologia(t);
				produtoTecnologias.setProduto(produto);
				produtoTecnologias.setTecnologia(tecnologia);
				pt.add(produtoTecnologias);
				tecnologiaRepository.save(tecnologia);
			} else {
				produtoTecnologias.setProduto(produto);
				produtoTecnologias.setTecnologia(tecno.get());
				pt.add(produtoTecnologias);
			}
			
		produtoTecnologiasRepository.save(produtoTecnologias);
		}
		
		return pt;
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
	public ArrayList<String> getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(ArrayList<String> tecnologias) {
		this.tecnologias = tecnologias;
	}
	
}
