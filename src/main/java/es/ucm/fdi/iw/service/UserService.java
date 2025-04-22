package es.ucm.fdi.iw.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User.Transfer registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email is already registered");
        }

        user.setDeleted(false);
        user.setRoles("USER");

        User registeredUser = userRepository.save(user);

        return registeredUser.toTransfer();
    }

    public List<User.Transfer> getAllUsers() {
        /**
         * COMENTARIO EXPLICATIVO
         * se realiza una serie de operaciones para obtener una lista de objetos
         * de tipo User.Transfer a partir de los datos almacenados en un repositorio de usuarios.
         *
         * 1. userRepository.findByUsernameNot("a"):
         *    - Este método se utiliza para recuperar todos los usuarios almacenados en el repositorip
         *      que no tiene como nombre de usuario a (el admin).
         *    - Devuelve una lista de objetos de tipo User.
         *
         * 2. .stream():
         *    - Convierte la lista de usuarios en un Stream, lo que permite realizar operaciones
         *      de procesamiento de datos de manera eficiente y legible.
         *
         * 3. .map(User::toTransfer):
         *    - Aplica una función de mapeo a cada elemento del Stream.
         *    - User::toTransfer es una referencia de método que convierte un objeto User en un objeto User.Transfer.
         *    - Esto significa que cada usuario en el Stream se transforma en su representación de transferencia.
         *
         * 4. .collect(Collectors.toList()):
         *    - Recoge los elementos del Stream en una lista.
         *    - Collectors.toList() es un colector que convierte el Stream de nuevo en una lista,
         *      pero ahora contiene objetos de tipo User.Transfer.
         *
         * El resultado final es una lista de objetos User.Transfer que se puede utilizar para
         * transferir datos de usuario en un formato más limpio y flexible.
         */
        
        return userRepository.findByUsernameNot("a")
            .stream()
            .map(User::toTransfer)
            .collect(Collectors.toList());
    }

    public List<User.Transfer> getUsersByKeyword(String keyword) {
        return userRepository.findByUsernameContainingIgnoreCase(keyword)
            .stream()
            .map(User::toTransfer)
            .collect(Collectors.toList());
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User getUsersByID(long id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public Page<User> findUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }
    public Page<User> findUsers(int page, int size, long currentUserId) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByUsernameNotAndIdNot("a", currentUserId, pageable);
    }
    
    
}
