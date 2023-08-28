# Validator App

### Инструкция по использованию

technical_task.pdf : техническое задание со списком требований 

Сборка приложения : mvn clean install 

Запуск тестов : mvn test

Запуск/остановка приложения : docker-compose up -d / docker-compose down

Генерация отчета по результатам тестирования : mvn surefire-report:report
(сам отчет будет находится  ${project}/target/site/surefire-report.html)

http://localhost:8088 : Backend API (post/get)

http://localhost:3000 : Форма валидации (frontend часть реализована на ReactJS)

Проверить работоспособность сервиса так же можно командой curl из терминала 
(примеры : src/main/resources/static/curl.txt)

Для тестирования были использованы Junit 5, Mockito, Testcontainer

В качестве сервера заглушки для локального тестирования использован WireMock

В качестве in-memory базы данных для локального тестирования была использована H2

Для создания окружения и запуска проекта использован docker



 

