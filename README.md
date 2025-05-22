# Swapify
Proyecto para la asignatura Ingeniería Web, curso 24/25

## Descripción del proyecto
![Banner](/src/main/resources/static/img/github/banner.png)

**Swapify** es una aplicación web de intercambio de habilidades. Los usuarios deben seleccionar las habilidades que poseen (que son las que enseñarán a la hora del intercambio/**swap**) y también las que quieran aprender. Una vez finalizado el **swap** se podrá valorar en una reseña al otro usuario, tanto como profesor de dicha habilidad como de estudiante. Estas reseñas podrán ser vistas por otros usuarios al visitar el perfil de este. Adicionalmente, hemos creado una funcionalidad de _currency_, las **swapicoins**, que podrán conseguirse cuando un usuario tiene una habilidad que otro quiere pero no busque una habilidad que pueda ofrecerle. En este caso, se le podrá ofrecer un **swap** a cambio de **swapicoins** en vez de habilidad(es) a enseñar.

## Tablas en la base de datos

![Diagrama de la base de datos](/src/main/resources/static/img/github/bd.png)

## Vistas realizadas
Se han realizado 6 vistas, se listarán con su nombre, lugar de acceso en la navbar y template(s) de Thymeleaf que usen:
- Vista principal, Inicio (index.html)
- Vista de intercambios, Swaps (swap.html y subvista swapinfo.html)
- ~~Vista de recompensas, Recompensas (rewards.html)~~*
- Vista de búsqueda, Barra de búsqueda (search.html)
- Panel de administración, Foto de perfil o nombre de usuario al estar registrado como administrador (admin.html)
- Perfil de usuario, Foto de perfil o nombre de usuario al estar registrado como usuario normal (user.html)

(*) En el repositorio pero no utilizada porque no tenía sentido.

## Cambios desde la última corrección
### Correcciones
- ~~Mucho peligro en swaps.html, con envío de mensajes directamente por WebSocket: os habéis complicado mucho para gestionarlo, habría sido mejor usar POST con ajax para comunicación  navegador->servidor, con WS sólo para servidor->navegador(es).~~ RESUELTO
- ~~hay varios errores de inserción SQL por clave primaria duplicada (skills de Juan Alberto & Juan Pérez).~~ RESUELTO
- ~~Errores deberían afectar status - no está bien devolver un error con status 200.~~ RESUELTO
- Puntos por @RequestBody con clase interna.
- Si recibo un mensaje de alguien cuando no estoy mirando la pantalla de mensajes con esa persona, ¿cómo me puedo enterar?. Deberíais usar el icono del sobre para que a) cambie si tengo mensajes no leídos, y b) pulsar sobre él me permita saltar a las convesaciones con mensajes no leídos. A MEDIAS (solo hasta el a) y un poco regulero porque si actualizas la página desaparece)
- ~~puedo seguir enviando mensajes a un swap finalizado.~~ RESUELTO
- ~~todavía hay mucha vista que no saca datos de la base de datos (user.html, por ejemplo)~~ RESUELTO
- ~~deberíais poder manejar gente que no ha recibido valoraciones todavía (y por tanto donde la media es imposible de calcular)~~ RESUELTO
- el README está bastante bien escrito, y detalla lo que queda por delante, que es mucho todavía. Contactad conmigo si necesitáis tutorías para avanzar.

### Funcionalidades nuevas
- Perfil dinámico con reviews y puntuación dinámicas
- Admin completo
- Filtros de búsqueda (más o menos)
- Notificaciones al recibir mensajes (más menos que más)

### Funcionalidades que no se llegaron a implementar
- Poder hacer swaps con puntos en vez de a cambio de una habilidad
- Darle algun tipo de uso a esos puntos luego

## Material no desarrollado por nosotros (fuera de la plantilla)
### Librerías y estilos añadidos
  - [Librería de iconos de Bootstrap](https://icons.getbootstrap.com/) usada en todos los botones con iconos
  - [Google fonts](https://fonts.google.com/) usada para la fuente principal [Plus Jakarta Sans](https://fonts.google.com/specimen/Plus+Jakarta+Sans/license)
  - [htmx](https://htmx.org/)  usado en /swaps -> /swaps/info para cambiar contenido interno del template sin tener que cambiar la página entera o escribir JavaScript.

### Imágenes
  - Todas las imágenes de personas están generadas usando [Mistral](https://mistral.ai)
