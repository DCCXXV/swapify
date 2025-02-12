package es.ucm.fdi.iw.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ucm.fdi.iw.model.User;

public class UserService {

    public static List<User> getUsers() {
        ArrayList<User> closeUsers = new ArrayList<>();

        // Crear usuarios ficticios
        User usuario1 = new User(
            "Juan Alberto",
            "¡Hola! Soy Juan Alberto, me gustaría aprender alemán o expandir mis conocimientos de programación aprendiendo Java! Tengo amplios conocimientos en Python y soy fluido en Inglés y Francés.",
            "JuanAlberto.jpg",
            Arrays.asList("Inglés", "Francés", "Programación en Python", "Desarrollo web"),
            Arrays.asList("Programación en Java", "Alemán")
        );

        User usuario2 = new User(
            "Ana",
            "¡Hola! Soy Ana, me encantaría aprender edición de fotografía. Soy profesora de yoga con 5 años de experiencia y también puedo enseñar técnicas de meditación. ¡Hablo francés fluidamente!",
            "Ana.jpg",
            Arrays.asList("Francés", "Yoga", "Meditación"),
            Arrays.asList("Edición de fotografía")
        );

        User usuario3 = new User(
            "Luis",
            "Me llamo Luis y estoy buscando mejorar mi italiano, aunque también recientemente he tenido ganas de aprender edición de fotografía. Soy guitarrista con experiencia en música clásica y flamenca, y también puedo enseñar diseño gráfico básico con Adobe Illustrator o inglés ya que estoy certificado con un C1 por Cambridge.",
            "Luis.jpg",
            Arrays.asList("Inglés", "Guitarra", "Diseño gráfico"),
            Arrays.asList("Edición de fotografía", "Italiano")
        );

        User usuario4 = new User(
            "María",
            "Soy María y me gustaría aprender a cocinar. Tengo experiencia dando clases de baile (salsa y bachata) y soy contadora profesional, puedo ayudar con temas de finanzas personales y Excel avanzado. Además, debido a mi experiencia laboral, tengo un alto nivel de inglés.",
            "María.jpg",
            Arrays.asList("Inglés", "Salsa", "Bachata", "Excel"),
            Arrays.asList("Cocina")
        );

        // Añadir usuarios a la lista
        closeUsers.add(usuario1);
        closeUsers.add(usuario2);
        closeUsers.add(usuario3);
        closeUsers.add(usuario4);

        return closeUsers;
    }
}
