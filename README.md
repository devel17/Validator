# Validator App

### Инструкция по использованию

technical_task.pdf : техническое задание со списком требований 

Сборка приложения : mvn clean install -Dmaven.test.skip=true 
(Обязательный этап перед запуском, исключительно в целях экономии времени)

Запуск тестов : mvn test

Запуск/остановка приложения : docker-compose up / docker-compose down

Генерация отчета по результатам тестирования : mvn surefire-report:report
(сам отчет будет находится  ${project}/target/site/surefire-report.html)

http://localhost:8080/api/validate?phone=[+0-9]{1-15} : Backend API, порт микросервиса 8088 (модуль api-ms) 
Проверить работоспособность сервиса так же можно командой curl из терминала 
(примеры : src/main/resources/static/curl.txt)

http://localhost:8080/ui/page : Форма валидации, порт микросервиса 9090 (модуль ui-ms (ReactJS))

Для тестирования были использованы Junit 5, Mockito, Testcontainer

В качестве сервера заглушки для локального тестирования использован WireMock

В качестве in-memory базы данных для локального тестирования была использована H2

Для создания окружения и запуска проекта использован docker


http://localhost:8761 - DiscoveryService (модуль discovery-is)
Инфраструктурный сервис регистрации и обнаружения микросервисвов (реализация Netflix Eureka)

http://localhost:8888 - ConfigService (модуль config-is)
Инфраструктурный сервис для хранения настроек микросервисов (реализация Spring Cloud Config , файловая система) 
http://localhost:8888/{discovery|gateway|api|ui}/default

http://localhost:8080 - GatewayService (модуль gateway-is)
Инфраструктурный сервис для обеспечения единой точки входа (реализация Spring Cloud Gateway)
http://localhost:8080/{service_id}/{action}