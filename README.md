# Swapify
Proyecto para la asignatura Ingeniería Web, curso 24/25

## Descripción del proyecto
![Banner](/src/main/resources/static/img/github/banner.png)

**Swapify** es una aplicación web de intercambio de habilidades. Los usuarios deben seleccionar las habilidades que poseen (que son las que enseñarán a la hora del intercambio/**swap**) y también las que quieran aprender. Una vez finalizado el **swap** se podrá valorar en una reseña al otro usuario, ~~tanto~~ como profesor de dicha habilidad ~~como de estudiante~~. Estas reseñas podrán ser vistas por otros usuarios al visitar el perfil de este. ~~Adicionalmente, hemos creado una funcionalidad de _currency_, las **swapicoins**, que podrán conseguirse cuando un usuario tiene una habilidad que otro quiere pero no busque una habilidad que pueda ofrecerle. En este caso, se le podrá ofrecer un **swap** a cambio de **swapicoins** en vez de habilidad(es) a enseñar.~~

## Tablas en la base de datos (desactualizado)

![Diagrama de la base de datos](/src/main/resources/static/img/github/bd.png)

## Vistas realizadas
Se han realizado 6 vistas, se listarán con su nombre, lugar de acceso en la navbar y template(s) de Thymeleaf que usen:
- Vista principal, Inicio (index.html)
- Vista de intercambios, Swaps (swaps.html)
- ~~Vista de recompensas, Recompensas (rewards.html)~~*
- Vista de búsqueda, Barra de búsqueda (search.html)
- Panel de administración, Foto de perfil o nombre de usuario al estar registrado como administrador (admin.html)
- Perfil de usuario, Foto de perfil o nombre de usuario al estar registrado como usuario normal (user.html)

(*) En el repositorio pero no utilizada.

### Funcionalidad de las vistas
- De la funcionalidad principal (Swaps) está casi todo, se pueden crear swaps, que apareceran como pending hasta que la otra persona lo acepte, chatear a través de swaps activos, y terminar swaps, lo que archiva la conversación y permite a ambos escribir una reseña.
- Falta terminar la búsqueda y administración, mejorar el index y hacer los test karate, que no estan bien hechos todavía.
- Faltaría también añadir la vista de notificaciones.

## Material no desarrollado por nosotros (fuera de la plantilla)
### Librerías y estilos añadidos
  - [Librería de iconos de Bootstrap](https://icons.getbootstrap.com/) usada en todos los botones con iconos
  - [Google fonts](https://fonts.google.com/) usada para la fuente principal [Plus Jakarta Sans](https://fonts.google.com/specimen/Plus+Jakarta+Sans/license)
  - [htmx](https://htmx.org/)  usado en /swaps para cambiar el contenido interno del template sin tener que recargar la página entera en un par de atributos de la etiqueta html.

### Imágenes
  - Todas las imágenes de personas están generadas usando [Mistral](https://mistral.ai)
