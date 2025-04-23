Feature: Flujo de un swap
# Este feature cubre la parte más importante de la funcionalidad de la web,
# incluyendo la creación del perfil, login, gestión de intercambios, modificación
# del horario, envío de mensajes y valoración final del swap.

Background:
  Given driver baseUrl

# Es necesario reiniciar el proyecto cada vez que se corre
Scenario: Flujo principal de intercambio (swap)
  # 1. Isabel entra a la página e inicia sesión
  And call read('login.feature@login_isabel')

  # 2. Isabel empieza un swap nuevo
  And click('button[id=swapButton0]')
  And delay(500)
  And click('#currentSkill0')
  And click('#desiredSkill0')
  And click('button[id=confirmSwap]')
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
  And waitFor('#endSwapButton')
  
  # 7. Juan envía un mensaje
  And input('#chat-message-input', 'Holaaaaaaaaaa' + Key.ENTER)
  And delay(1000)

  # 8. Isabel inicia sesión de nuevo
  And call read('login.feature@login_isabel')

  # 9. Isabel entra en el nuevo swap activo
  And click('#navBarSwap')
  And waitFor('#sideBarTitle')
  And match driver.title == "Intercambios | Swapify"
  # Por defecto la sección de activos está abierta
  # And click('#activos')
  # And waitFor('#activeSwap2')
  And click('#activeSwap0')
  And waitFor('#endSwapButton')

  # 10. Isabel envía un mensaje
  And input('#chat-message-input', 'Adioooooos' + Key.ENTER)
  And delay(1000)

  # 11. Isabel termina el swap
  And click('button[id=endSwapButton]')

  # 12. Isabel navega al swap terminado
  And click('#terminados')
  And waitFor('#finishedSwap1')
  And click('#finishedSwap1')
  And waitFor('#finishedBlock')

  # 13. Isabel envía una reseña
  And click('button[id=reviewButton]')
  And delay(500)
  And input('#reviewRating', '0')
  And input('#reviewText', 'Fatal')
  And click('button[id=submitReview]')
  And delay(1000)

  # 13. Isabel entra al perfil de Juan y comprueba su reseña
  And click('#otheruserUsername')
  And waitFor('#namejuanito03')
  And match text('#namejuanito03') == "Juan Pérez"
