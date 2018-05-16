# RNGS (Random Number Generation Service)
Сервис генерации случайного пятизначного числа. Предоставляет REST API с методами Generate и Check: \
**Generate** - генерирует случайное пятизначное число для документа (documentId). \
**Check** -  проверяет генерировал ли он данное число для указанного documentId.\
Использовано: Spring Framework,Hibernate ORM,Postgres, Tomcat, Gradle.
###Пример использования:
**Генерация** \
<http://localhost:8080/RNGS/getNumber> принимает POST запросы в формате JSON, на генерацию рандомного числа для определенного documentId. \
**Пример запроса:**
```json
{
    "messageName" : "generateRq",
    "context" : {
	        "documentId" : "13"
    }
}
``` 
Для полученного документа генерируется случайное пятизначное число *(для каждого уникального documentId может быть сгенерировано неограниченное число чисел)* и формирует ответ в формате JSON.\
**Пример ответа:**
```json
{
    "messageName": "generateRs",
    "context": {
        "uniqNum": 29389
    }
}
``` 
**Проверка** \
<http://localhost:8080/RNGS/check> принимает POST запросы в формате JSON, для проверки было ли данное число сгенерировано системой для documentId.
**Пример запроса:** \
```json
{
    "messageName":"checkRq",
    "context":{
        "uniqNum":"33333",
        "documentId":"13"
    }
}
``` 
В зависимости от результата проверки, формируется ответ в JSON.
**Пример позитива:**
```json
{
    "messageName": "checkRs",
    "context": {
        "generateDate": "2018-05-16 20:17:36.139",
        "Result": "true"
    }
}
``` 
**Пример негатива:**
```json
{
    "messageName": "checkRs",
    "context": {
        "Result": "false"
    }
}
``` 
 P.S для тестирования удобнее использовать Postman, Curl и т.д.
###Установка
Для запуска необходимо ввести в терминале :
```bash
./gradlew appRun
```
Сервер запуститься по пути <http://localhost:8080/RNGS> . \
<http://localhost:8080/RNGS/getNumber> - для генерации случайного числа\
<http://localhost:8080/RNGS/check> -  для проверки принадлежности данного числа определенному documentId.\

P.S в проекте используется бд Postgres.
SQL на создание таблицы лежит в папке sql.\
Настройки для Postgres доступны в src/webapp/WEB-INF/servletContext.xml.\

