
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


// swagger 
https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
swagger with springfox is not working for springboot 2.6.4(version used in this project)


// https://stackoverflow.com/a/71344965/11930483
commented springfox dependencies and removed SpringFoxConfig class 
and used sprindoc-openapi-ui maven dependency 
(uncomment spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER in case of path error) 

Now try http://localhost:8080/swagger-ui/index.html //or http://localhost:8080/{BASE_PATH}/swagger-ui/index.html


//Documentation using swagger UI
https://www.baeldung.com/spring-rest-openapi-documentation