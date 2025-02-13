package es.ucm.fdi.iw.service;

import java.util.Arrays;
import java.util.List;

public class SkillService {

    // static solo para ahorrar tiempo ahora, luego quitar
    public static List<String> getCommonSkills() {
        return Arrays.asList(
            "Inglés",
            "Programación",
            "Diseño gráfico",
            "Chino mandarín",
            "Español",
            "Gestión de proyectos",
            "Desarrollo web",
            "Redacción",
            "Microsoft Office",
            "Alemán",
            "Redes sociales",
            "Photoshop",
            "Videoedición",
            "Excel avanzado",
            "Presentación",
            "Gestión de redes sociales",
            "Fotografía",
            "Cocina",
            "Música",
            "Escritura creativa",
            "Ilustración",
            "Animación",
            "Análisis de datos",
            "Diseño de interiores",
            "Jardinería"
        );
    }

    public static List<String> getRequestedSkills() {
        return Arrays.asList(
            "Alemán",
            "Francés",
            "Español",
            "Chino mandarín",
            "Italiano",
            "Japonés",
            "Ruso",
            "Portugués",
            "Árabe",
            "Coreano",
            "Yoga",
            "Medición",
            "Repostería",
            "Costura",
            "Carpintería",
            "Mecánica básica",
            "Electrónica",
            "Programación en Python",
            "Desarrollo de videojuegos",
            "Modelado 3D",
            "Edición de video",
            "Producción musical",
            "Diseño de moda",
            "Fitness",
            "Nutrición"
        );
    }
}
