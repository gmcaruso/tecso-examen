#RESPUESTAS:


##CREAR CUENTA
curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"accountNumber": 222,"currency": "PESOS", "balance": 10, "movements": []}' http://localhost:8080/api/account/crear-cuenta

##LISTAR CUENTAS
curl -X GET "http://localhost:8080/api/account/listar-cuentas"

##AGREGAR MOVIMIENTO
curl -X PATCH -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"date": "2019-02-19T14:32:58.587+0000","typeOfMovement": "CREDIT","description": "Movimiento","amount": 0}' "http://localhost:8080/api/movement/agregar-movimiento?accountId=2"

##LISTAR MOVIMIENTOS DE UNA CUENTA (1)
curl -X GET "http://localhost:8080/api/movement/listar-movimientos?accountId=1"

##BORRAR CUENTA (1)
curl -X DELETE "http://localhost:8080/api/account/eliminar-cuenta?accountId=1"