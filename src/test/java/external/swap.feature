Feature: Flujo de un swap
# Este feature cubre la parte más importante de la funcionalidad de la web,
# incluyendo la creación del perfil, login, gestión de intercambios, modificación
# del horario, envío de mensajes y valoración final del swap.

Background:
  Given driver baseUrl

Scenario: Flujo principal de intercambio (swap)
  # 1. Isabel entra a la página e inicia sesión
  And call read('login.feature@login_isabel')

  # 2. Isabel empieza un swap nuevo
  And click('button[id=swapButton0]')
  And delay(500)
  And click('#currentSkill0')
  And click('#desiredSkill0')
  And click('button[id=confirmSwap]')
  # verificamos
  And waitFor('#sideBarTitle')
  And match driver.title == "Intercambios | Swapify"

  # 3. Isabel comprueba el nuevo swap pendiente
  And click('#pendientes')
  And waitFor('#pendingSwap0')
  And click('#pendingSwap0')
  And waitFor('#pendingBlock')
  And match text('#pendingBlock') contains "¿Esperando a que el usuario lo acepte?"

  # 4. Juan (el que recibe el Swap) inicia sesión
  And call read('login.feature@login_juan')
  
  # 5. Juan comprueba el nuevo swap pendiente
  And click('#navBarSwap')
  And waitFor('#sideBarTitle')
  And match driver.title == "Intercambios | Swapify"
  And click('#pendientes')
  And waitFor('#pendingSwap2')
  And click('#pendingSwap2')
  And waitFor('#pendingBlock')
  And click('#AcceptSwapButton')

  # 6. Juan abre el nuevo swap activo
  And click('#activos')
  And waitFor('#activeSwap0')
  And click('#activeSwap0')
  # verificamos
  And waitFor('#endSwapButton')
  
  # 7. Juan envía un mensaje
  And input('#chat-message-input', 'Holaaaaaaaaaa' + Key.ENTER)
  And delay(10000)