package es.ucm.fdi.iw.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.Skill;
import es.ucm.fdi.iw.repository.SkillRepository;

@Service
public class SkillService {
    
    @Autowired
    private SkillRepository skillRepository;
    
    public List<Skill.Transfer> getSkillsByKeyword(String keyword) {
        return skillRepository.findByNameContainingIgnoreCase(keyword)
            .stream()
            .map(Skill::toTransfer)
            .collect(Collectors.toList());
    }
    
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
