package bis2bis.produto.dto;

import bis2bis.models.ProdutoTecnologias;

public class ProdutoTecnologiasDetalhesDTO {
	
	private String tecnologia;
	
	public ProdutoTecnologiasDetalhesDTO (ProdutoTecnologias produtoTecnologias) {
		this.tecnologia = produtoTecnologias.getTecnologia().getTecnologia();
	}

	@Override
	public String toString() {
		return tecnologia;
	}

	public String getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}

}
