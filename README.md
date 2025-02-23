# Swapify
Proyecto para la asignatura Ingeniería Web, curso 24/25

## Descripción del proyecto
![Banner](/src/main/resources/static/img/github/banner.png)

**Swapify** es una aplicación web de intercambio de habilidades. Los usuarios deben seleccionar las habilidades que poseen (que son las que enseñarán a la hora del intercambio/**swap**) y también las que quieran aprender. Una vez finalizado el **swap** se podrá valorar en una reseña al otro usuçario, tanto como profesor de dicha habilidad como de estudiante. Estas reseñas podrán ser vistas por otros usuarios al visitar el perfil de este. Adicionalmente, hemos creado una funcionalidad de _currency_, las **swapicoins**, que podrán conseguirse de diferentes maneras, principalmente cuando un usuario tiene una habilidad que quieres pero no busca una habilidad que puedas ofrecerle. En este caso, se le podrá ofrecer un **swap** a cambio de **swapicoins** en vez de habilidad(es) a enseñar.

## Entidades

![Diagrama de la base de datos](/src/main/resources/static/img/github/bd.png)

## Vistas realizadas
Se han realizado 6 vistas, se listarán con su nombre, lugar de acceso en la navbar y template(s) de Thymeleaf que usen:
- Vista principal, Inicio (index.html)
- Vista de intercambios, Swaps (swap.html y subvista swapinfo.html)
- ~~Vista de recompensas, Recompensas (rewards.html)~~*
- Vista de búsqueda, Barra de búsqueda (search.html)
- Panel de administración, Foto de perfil o nombre de usuario al estar registrado como administrador (admin.html)
- Perfil de usuario, Foto de perfil o nombre de usuario al estar registrado como usuario normal (user.html)

(*) En el repositorio pero no utilizada.

## Material no desarrollado por nosotros (fuera de la plantilla)
### Librerías añadidas
  - [Librería de iconos de Bootstrap](https://icons.getbootstrap.com/) usada en todos los botones con iconos
  - [htmx](https://htmx.org/)  usado en /swaps -> /swaps/info para cambiar contenido interno del template sin tener que cambiar la página entera o escribir JavaScript.

### Imágenes
  - Todas las imágenes de personas están generadas usando [Mistral](https://mistral.ai)
  - El resto de imágenes viene o está compuesto por imágenes de [Wikimedia](https://commons.wikimedia.org/)
