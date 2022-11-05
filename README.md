
curl --location --request GET 'http://localhost:8080/orders'

curl --location --request POST 'http://localhost:8080/order' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "harry potter 1"
}'

curl --location --request PUT 'http://localhost:8080/order' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderId": 1,
    "name" : "Harry Potter 1"
}'

curl --location --request DELETE 'http://localhost:8080/order?id=1'



// h2-console
http://localhost:8080/h2-console
with url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
user=sa, password=(empty)