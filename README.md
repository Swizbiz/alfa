На вход в виде параметра командной строки java-приложению передаётся
имя XML-файла, в котором задано взаимное положение предметов(Item) и ящиков(Box).
##
- получить список всех Box : `curl -s http://localhost:8080/rest/box/`

- получить список всех Item : `curl -s http://localhost:8080/rest/item/` 

- получить _id_ всех Item, которые хранятся в коробке _box_ и имеют цвет _color_ : `curl -s -X POST -d '{"box":"1","color":"red"}' -H 'Content-Type: application/json' http://localhost:8080/rest/itemsId`
