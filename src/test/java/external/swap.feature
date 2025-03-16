Feature: Flujo de un swap
# Este feature cubre la parte más importante de la funcionalidad de la web,
# incluyendo la creación del perfil, login, gestión de intercambios, modificación
# del horario, envío de mensajes y valoración final del swap.

Background:
  # La URL base se obtiene desde karate-config.js (config.baseUrl)
  * url baseUrl

Scenario: Flujo principal de intercambio (swap)
  # 1. El usuario crea su perfil y lo que conlleva
  Given path '/signup'
  And request { 
    "username": "usuario1", 
    "password": "password123", 
    "firstName": "Juan", 
    "lastName": "Rodriguez", 
    "email": "juan@example.com" 
  }
  When method post
  Then status 201

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
  And request { 
       "description": "Intercambio: clases de guitarra por clases de inglés",
       "schedule": ["Monday", "Wednesday", "Friday"],
       "targetUser": "usuario2"
  }
  When method post
  Then status 201
  # Comentario: Se crea un nuevo swap y se notifica al usuario objetivo (usuario2).

  # 5. El usuario revisa el listado para verificar la creación
  Given path '/swaps'
  When method get
  Then status 200
  And match response contains "Intercambio: clases de guitarra por clases de inglés"

  # 6. El usuario modifica el horario del swap
  Given path '/swaps/modify'
  And request { 
       "swapId": 1, 
       "schedule": ["Tuesday", "Thursday"] 
  }
  When method put
  Then status 200

  # 7. El usuario envía un mensaje para avisar al otro
  Given path '/swaps/1/msg'
  And request { "message": "He actualizado el horario, revisa los nuevos días." }
  When method post
  Then status 200

  # 8. Swap terminado, valoración del otro
  Given path '/swaps/1/complete'
  And request { "rating": 5, "comment": "Excelente intercambio, muy puntual." }
  When method post
  Then status 200
  # Comentario: Se cierra el swap y se guarda la valoración del otro usuario.
