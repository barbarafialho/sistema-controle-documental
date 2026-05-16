package br.com.barbara.sistema_controle_documental.service.component;

import br.com.barbara.sistema_controle_documental.exceptions.ResourceNotFoundException;
import br.com.barbara.sistema_controle_documental.model.Usuario;
import br.com.barbara.sistema_controle_documental.model.enuns.Role;
import br.com.barbara.sistema_controle_documental.model.validate.UsuarioInterface;
import org.springframework.stereotype.Component;

@Component
public class ValidarPermissao {

    public void validar(UsuarioInterface usuarioInterface, Usuario logado) {
        Long usuarioOwner = usuarioInterface.getUsuarioID();
        if (logado.getRole().equals(Role.ADMIN)){
            return;
        }
        if (usuarioOwner == null || !usuarioOwner.equals(logado.getId())){
            throw new ResourceNotFoundException("Nenhum registro encontrado para esse ID");
        }
    }
}
