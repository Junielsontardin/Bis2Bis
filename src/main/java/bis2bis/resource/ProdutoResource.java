package bis2bis.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bis2bis.exception.MensagemException;
import bis2bis.models.Produto;
import bis2bis.models.ProdutoTecnologias;
import bis2bis.produto.dto.ProdutoDTO;
import bis2bis.produto.dto.ProdutoDetalhesDTO;
import bis2bis.repository.ProdutoCustomQueryRepository;
//import bis2bis.repository.ProdutoCustomQueryRepository;
import bis2bis.repository.ProdutoRepository;
import bis2bis.repository.ProdutoTecnologiasRepository;
import bis2bis.repository.TecnologiaRepository;

@RestController
public class ProdutoResource {
	
	private final ProdutoCustomQueryRepository produtoCustomQueryRepository;

	public ProdutoResource(ProdutoCustomQueryRepository produtoCustomQueryRepository) {
		this.produtoCustomQueryRepository = produtoCustomQueryRepository;
	}

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	TecnologiaRepository tecnologiaRepository;
	
	@Autowired
	ProdutoTecnologiasRepository produtoTecnologiasRepository;
	
	@GetMapping("/produto")
	public ResponseEntity<?> getProdutos() {
		List<Produto> produtos = produtoRepository.findAll();	
		List<ProdutoDetalhesDTO> dtos = new ArrayList<>();
		
		for(Produto produto : produtos) {
			dtos.add(new ProdutoDetalhesDTO(produto));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	
	@PostMapping("/produto")
	public ResponseEntity<?> postProdutos(@RequestBody ProdutoDTO dto) throws MensagemException {
		
		try {
			Produto novoProduto = dto.toProduto();
			List<ProdutoTecnologias> produtoTecnologias = dto.toTecnologias(produtoRepository, tecnologiaRepository, produtoTecnologiasRepository, novoProduto);
			novoProduto.setProdutoTecnologias(produtoTecnologias);
			produtoRepository.save(novoProduto);
			return new ResponseEntity<>("O produto foi cadastrado com sucesso!", HttpStatus.OK);
			
		} catch (ConstraintViolationException e) {	
			return new ResponseEntity<>("As informações inseridas são inválidas", HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Já existe um produto com esse nome.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("produto/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		produtoRepository.deleteById(id);
		return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
	}
	
	@GetMapping("produto/tecnologia")
	public ResponseEntity<?> getProdutosTecnologia(@RequestParam(value = "id", required = false) List<Long> ids) {
		
		List<Produto> produtos = produtoCustomQueryRepository.findProdutoTecnologias(ids);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

}
