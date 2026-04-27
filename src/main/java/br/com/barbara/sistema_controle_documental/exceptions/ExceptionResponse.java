package br.com.barbara.sistema_controle_documental.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
