package bis2bis.resource;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bis2bis.models.Tecnologia;
import bis2bis.repository.TecnologiaRepository;

@RestController
public class TecnologiaResource {
	
	@Autowired
	TecnologiaRepository tecnologiaRepository;

	@GetMapping("/tecnologia")
	public ResponseEntity<?> getTecnologia() {
		List<Tecnologia> tecnologias = tecnologiaRepository.findAll();
		return new ResponseEntity<>(tecnologias, HttpStatus.OK);
	}
	
	@PostMapping("/tecnologia")
	public ResponseEntity<?> postTecnologia(@RequestBody Tecnologia tecnologia) {
		
		try {
			Tecnologia novaTecnologia = tecnologia;		
			tecnologiaRepository.save(novaTecnologia);
			
			return new ResponseEntity<>("O produto foi cadastrado com sucesso!", HttpStatus.OK);
		} catch (ConstraintViolationException e) {	
			return new ResponseEntity<>("As informações inseridas são inválidas", HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {	
			return new ResponseEntity<>("Já existe uma tecnologia cadastrada com esse nome.", HttpStatus.BAD_REQUEST);
		}
	}
}
