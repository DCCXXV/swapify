Feature: Flujo de un swap
# Este feature cubre la parte más importante de la funcionalidad de la web,
# incluyendo la creación del perfil, login, gestión de intercambios, modificación
# del horario, envío de mensajes y valoración final del swap.

Background:
  Given driver baseUrl

Scenario: Flujo principal de intercambio (swap)
  # 1. El usuario entra a la página e inicia sesión
  And input('#usernameInput', 'Isabel')
  And input('#passwordInput', 'aa')
  And click('button[id=loginButton]')
  # verificamos
  And waitFor('button[id=logoutButton]')
  And match driver.title == "Inicio | Swapify"

  # 2. El usuario empieza un swap nuevo
  And click('button[id=swapButton0]')
  And delay(500)
  And click('#currentSkill0')
  And click('#desiredSkill0')
  And click('button[id=confirmSwap]')
  # verificamos
  And waitFor('#sideBarTitle')
  And match driver.title == "Intercambios | Swapify"