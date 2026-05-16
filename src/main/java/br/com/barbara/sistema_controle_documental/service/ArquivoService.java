package br.com.barbara.sistema_controle_documental.service;

import br.com.barbara.sistema_controle_documental.exceptions.FileNotFoundException;
import br.com.barbara.sistema_controle_documental.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ArquivoService {

    private final Path localArmazenamento;

    public ArquivoService(@Value("${file.upload-dir}") String diretorio) {
        this.localArmazenamento = Paths.get(diretorio).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.localArmazenamento);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o diretório de Upload." + e.getMessage());
        }
    }

    public String salvarArquivo(MultipartFile arquivo){
        if (arquivo.isEmpty()){
            throw new FileStorageException("Não foi possível armazenar arquivo vazio.");
        }
        String nomeArquivo = StringUtils.cleanPath(Objects.requireNonNull(arquivo.getOriginalFilename()));
        try {
            Path destino = this.localArmazenamento.resolve(nomeArquivo);
            Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
            return nomeArquivo;
        } catch (Exception e){
            throw new FileStorageException("Não foi possível salvar arquivo no disco. " + e.getMessage());
        }
    }

    public Resource carregarArquivo(String nomeArquivo){
        try {
            Path caminho = this.localArmazenamento.resolve(nomeArquivo).normalize();
            Resource resouce = new UrlResource(caminho.toUri());
            if (resouce.exists()){
                return resouce;
            } else {
                throw new FileNotFoundException("Erro ao tentar ler o caminho do arquivo: " + nomeArquivo);
            }
        } catch (Exception e) {
            throw new FileStorageException("Não foi possível excluir o arquivo: " + nomeArquivo);
        }
    }

    public void excluirArquivo(String nomeArquivo){
        try {
            Path caminho = this.localArmazenamento.resolve(nomeArquivo).normalize();
            Files.deleteIfExists(caminho);
        } catch (Exception e) {
            throw new FileStorageException("Não foi possível excluir o arquivo: " + nomeArquivo);
        }
    }
}
