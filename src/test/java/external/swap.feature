Feature: Flujo de un swap
# Este feature cubre la parte más importante de la funcionalidad de la web,
# incluyendo la creación del perfil, login, gestión de intercambios, modificación
# del horario, envío de mensajes y valoración final del swap.

Background:
  * url baseUrl
  # Definición de los payloads para cada paso
  * def signupPayload =
    """
    {
      "username": "usuario1",
      "password": "password123",
      "firstName": "Juan",
      "lastName": "Rodriguez",
      "email": "juan@example.com"
    }
    """
  * def newSwapPayload =
    """
    {
      "description": "Intercambio: clases de guitarra por clases de inglés",
      "schedule": ["Monday", "Wednesday", "Friday"],
      "targetUser": "usuario2"
    }
    """
  * def modifySwapPayload =
    """
    {
      "swapId": 1,
      "schedule": ["Tuesday", "Thursday"]
    }
    """
  * def msgPayload =
    """
    { "message": "He actualizado el horario, revisa los nuevos días." }
    """
  * def completeSwapPayload =
    """
    { "rating": 5, "comment": "Excelente intercambio, muy puntual." }
    """

Scenario: Flujo principal de intercambio (swap)
  # 1. El usuario crea su perfil
  Given path '/signup'
  And request signupPayload
  When method post
  Then status 200

  # 2. El usuario inicia sesión
  Given path '/login'
  And form field username = 'usuario1'
  And form field password = 'password123'
  When method post
  Then status 200

  # 3. El usuario accede al panel de swaps
  Given path '/swaps'
  When method get
  Then status 200

  # 4. El usuario inicia un nuevo swap
  Given path '/swaps/new'
  And request newSwapPayload
  When method post
  Then status 200

  # 5. El usuario revisa el listado para verificar la creación
  Given path '/swaps'
  When method get
  Then status 200
  And match response contains "Intercambio: clases de guitarra por clases de inglés"

  # 6. El usuario modifica el horario del swap
  Given path '/swaps/modify'
  And request modifySwapPayload
  When method put
  Then status 200

  # 7. El usuario envía un mensaje para avisar al otro
  Given path '/swaps/1/msg'
  And request msgPayload
  When method post
  Then status 200

  # 8. Swap terminado, valoración del otro
  Given path '/swaps/1/complete'
  And request completeSwapPayload
  When method post
  Then status 200
