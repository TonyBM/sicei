package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
////import com.google.api.services.gmail.Gmail;

import javax.transaction.Transactional;
import mx.uady.sicei.exception.NotFoundException;

import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.TutoriaRepository;
import mx.uady.sicei.repository.UsuarioRepository;
import org.springframework.scheduling.annotation.Async;
import javax.mail.internet.MimeMessage;
import mx.uady.sicei.config.TestEmail;

@Service
public class CorreoService {

    private TestEmail email;

    private final String EMAIL_SERVICIO = "javquijano@gmail.com";

    private final String SUBJECT = "BIENVENIDO AL SICEI";

    private final String id_correo = "dvhuwmlfoktvmmzm";

    public CorreoService() {
        email = new TestEmail();
    }

    @Async
    public void enviarCorreoDeRegistro(Usuario usuario) {
        try {
            email.send(EMAIL_SERVICIO, id_correo, EMAIL_SERVICIO, SUBJECT, usuario.getUsuario());
            
        } catch (Exception e) {
            System.out.println("aaaaaaaaaaaaaaaaaa");
        }
    }

    private String generarMensajeUsuarioCreado(String nombre) {
        return String.format("BIENVENDIO AL SICEN USUARIO %s", nombre);
    }
    
}
