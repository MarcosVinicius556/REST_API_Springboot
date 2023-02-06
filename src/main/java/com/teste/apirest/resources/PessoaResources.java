package com.teste.apirest.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.teste.apirest.entities.Usuario;
import com.teste.apirest.services.UserService;

// Controlador, somente fica entre o cliente e as regras de negócio, chama o 
// service layer para depois chamar o repository
@RestController
@RequestMapping(value="/users")
public class UserResources {

	@Autowired
	private UserService service;
	//Endpoint do tipo get
	
	//Retorna todos "/user GET"
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> listUser = service.findAll();
		return ResponseEntity.ok().body(listUser);
	}
	
	// Recebe um id 
	// Estes anotattions servem para indicar para o 
	// Spring que este método recebe algo
	@GetMapping(value="/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	//Inserindo novo recurso
	//POST
	//RequestBody para pegar o json vindo do método post
	//Por conveção retornar código 201, código para dizer que inseriu com sucesso
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj){
		obj = service.insert(obj);
		//Create por padrão exige que você passe uma "location"
		//como parâmetro, que seria como um caminho para 
		//o recurso inserido 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")//Passa o "caminho" do id
											 .buildAndExpand(obj.getId()) //Id inserido
											 .toUri(); //Converte para uma URI
		//Passa o caminho e objeto inserido no banco
		return ResponseEntity.created(uri).body(obj);
	}
	
	//Delete de usuario
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.delete(id);
		//Código do http para uma requisição que não tem retorno é 204
		return ResponseEntity.noContent().build();
	}
	
	//Atualização de usuário
	@PutMapping(value="/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
