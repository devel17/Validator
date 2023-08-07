# Validator App

### Инструкция по использованию

technical_task.pdf Техническое задание со списком требований 

Для создания базы данных и организации доступа выполнить в командной строке :
mysql -uroot -p < init_db.sql (файл находится в ресурсах проекта, необходимы права администратора) 

Сборка приложения : mvn clean install 

Запуск приложения : mvn spring-boot:run

Запуск тестов : mvn test

Генерация отчета по результатам тестирования : mvn surefire-report:report
(сам отчет будет находится  ${basedir}/target/site/surefire-report.html)

http://localhost:8088 : форма для валидации (front-end часть реализована на ReactJS)

проверить работоспособность сервиса так же можно командой curl из терминала :

curl -X POST -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"12423222931"}' http://localhost:8088/validate

curl -X POST -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"11165384765"}' http://localhost:8088/validate

curl -X POST -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"71423423412"}' http://localhost:8088/validate

curl -X POST -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"77112227231"}' http://localhost:8088/validate

curl -X POST -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"000000000000"}' http://localhost:8088/validate

curl -X POST -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"ssadsadsaasa"}' http://localhost:8088/validate

curl -X POST -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"+71423423412"}' http://localhost:8088/validate


PS Некоторые решения могут показаться избыточными для такого проекта, но я хотел показать скорее ход мыслей
по проектированию масштабируемой системы , например, в микросервис способный валидировать произвольные идентифкаторы 
по любым уровням точности (спискам правил)

 

