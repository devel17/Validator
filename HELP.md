# Getting Started

### Reference Documentation

Сборка приложения : mvn clean install 

Запуск приложения : mvn spring-boot:run

Запуск тестов : mvn test

Генерация отчета по результатам тестирования : mvn surefire-report:report
(сам отчет будет находится  ${basedir}/target/site/surefire-report.html)

Для создания базы данных и организации доступа выполнить в командной строке :
mysql -uroot -p < init_db.sql (файл находится в ресурсах проекта, необходимы права администратора) 

PS К сожалению, не успел до конца реализовать front-end часть (используется React JS), 
проверить работоспособность сервиса можно командой curl из терминала :


curl -X GET -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"12423222931", "validationResult":""}' http://localhost:8088/validate

curl -X GET -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"11165384765", "validationResult":""}' http://localhost:8088/validate

curl -X GET -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"71423423412", "validationResult":""}' http://localhost:8088/validate

curl -X GET -H 'Content-type:application/json; utf-8' -d '{"validationType":"Country", "validationSource":"77112227231", "validationResult":""}' http://localhost:8088/validate


PS2 Некоторые решения могут показаться избыточными для такого проекта, но я хотел показать скорее ход мыслей
по проектированию масштабируемой системы , например, в микросервис способный валидировать произвольные идентифкаторы 
по любым уровням точности (спискам правил)

 

