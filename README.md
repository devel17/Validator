# Validator App

### Инструкция по использованию

technical_task.pdf : техническое задание со списком требований 

Сборка приложения : mvn clean install 

Запуск тестов : mvn test

Запуск/остановка приложения : docker-compose up -d / docker-compose down

Генерация отчета по результатам тестирования : mvn surefire-report:report
(сам отчет будет находится  ${basedir}/target/site/surefire-report.html)

http://localhost:8088 : форма для валидации (frontend часть реализована на ReactJS)

Проверить работоспособность сервиса так же можно командой curl из терминала 
(примеры : src/main/resources/static/curl.txt)

В качестве сервера для заглушек для локального тестирования использован WireMock

В качестве in-memory базы данных для локального тестирования была использована H2



 

