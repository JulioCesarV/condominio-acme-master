# condominio-acme-master

Link do banco: http://localhost:8080/h2
Nome do banco: jdbc:h2:file:~/juliocesarjava2
Username: sa
Password:

Para cadastrar um condomínio, usar: http://localhost:8081/api/condominios
Após cadastrar um condomínio, é possível cadastastrar Unidades, Avisos ou Multas:
http://localhost:8080/api/condominios/1/unidades
http://localhost:8080/api/condominios/1/avisos
http://localhost:8080/api/condominios/1/multas
Sabendo que o ID do condomínio é 1.

Para editar ou deletar Unidades, Avisos, ou Multas, usar:
http://localhost:8081/api/unidades/{#}
http://localhost:8081/api/avisos/{#}
http://localhost:8081/api/multas/{#}
Onde o "#", seria o ID passado por parâmetro.
