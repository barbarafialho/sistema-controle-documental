package br.com.barbara.sistema_controle_documental.utils.test;

import br.com.barbara.sistema_controle_documental.task.DocumentoSchedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
public class TesteEmailController {

    private final DocumentoSchedule documentoSchedule;

    public TesteEmailController(DocumentoSchedule documentoSchedule) {
        this.documentoSchedule = documentoSchedule;
    }

    @GetMapping("/disparar-agendamento")
    public ResponseEntity<String> testarSchedule() {
        documentoSchedule.checkDocumentos();
        return ResponseEntity.ok("O método de verificação foi executado! Olhe o console do Java.");
    }
}

