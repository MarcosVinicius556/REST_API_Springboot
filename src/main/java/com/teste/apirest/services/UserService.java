package com.teste.apirest.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.teste.apirest.entities.Usuario;
import com.teste.apirest.repositories.UserRepository;
import com.teste.apirest.services.exceptions.DataBaseException;
import com.teste.apirest.services.exceptions.ResourceNotFoundException;

//Precisa ser registrado como componente do Spring
//Poder usar o AUTOWIRED para fazer a injeção de dependencia em outras classes
//Annotattion que registra como um serviço
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<Usuario> findAll(){
		return userRepository.findAll();
	}
	
	public Usuario findById(Long id) {
		//Adicionado no java 8, melhor de se tratar do que lançar execessão
		//Algo opcional, com varias funcões para fazer o controle
		//Tendo como forte suporte a programação funcional
		Optional<Usuario> obj = userRepository.findById(id);
		//Se não tiver nada presente estoura uma exceção
		//Trata a excessão com a que foi criada(Resource not found)
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//Vai salvar e retornar o usuário salvo
	public Usuario insert(Usuario user) {
		return userRepository.save(user);
	}
	
	//Deleção de usuário
	public void delete(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { //Qualquer erro de execucão
			//e.printStackTrace(); // somente para descobrir a exceção lançada
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			//Trata o segundo tipo de exceção
			//Trata o tipo de requisição do serviço
			throw new DataBaseException(e.getMessage());
		}
	}
	
	//Atualização de usuário
	//Retorna o usuário atualizado
	//Passa como parametro o id
	//e os dados do usuário a 
	//serem atualizados
	public Usuario update(Long id, Usuario obj) {
		//Entitdade monitorada pelo hibernate
		//getOne não vai no banco, só deixa um objeto "Monitorado"
		//Para só depois de manipulado alterar
		//Apenas prepara o objeto, para depois alterar
		try {
			Usuario usuario = userRepository.getReferenceById(id);
			updateData(usuario, obj);
			return userRepository.save(usuario);
		} catch (EntityNotFoundException e) { //Tratamento caso não haja usuario com esse id
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Usuario usuario, Usuario usuarioRecebido) {
		usuario.setName(usuarioRecebido.getName());
		usuario.setMail(usuarioRecebido.getMail());
		usuario.setPhone(usuarioRecebido.getPhone());
		
	}
}
