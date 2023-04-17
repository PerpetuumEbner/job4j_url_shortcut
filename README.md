# Сервис - UrlShortCut
[![Java CI](https://github.com/PerpetuumEbner/job4j_url_shortcut/actions/workflows/maven.yml/badge.svg)](https://github.com/PerpetuumEbner/job4j_url_shortcut/actions/workflows/maven.yml)
## Общее описание:

Генерация коротких ссылок для зарегистрированных сайтов. 

***

## Реализовано:
* Регистрация сайта.
* Авторизация.
* Регистрация URL.
* Переадресация. Выполняется без авторизации.
* Статистика.

***

## Технологии:
[![java](https://img.shields.io/badge/java-17-red)](https://www.java.com/)
[![maven](https://img.shields.io/badge/apache--maven-3.8.3-blue)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.4-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgresSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.26-red)](https://projectlombok.org/)

***

## Запуск проекта:
* создать базу данных `url_shortcut`
* `maven install`
* `java -jar target/job4j_url_shortcut-0.0.1-SNAPSHOT.jar`

***

## Структура проекта:

### Регистрация сайта.
Сайту выдаётся логин и пароль. Отправляем POST запрос с телом JSON объекта. Флаг registration указывает, что регистрация выполнена или нет, то есть сайт уже есть в системе.

![1](img/1.png)

### Авторизация
Авторизация через JWT, пользователь отправляет POST запрос логина и раннее полученного пароля. В блоке HEAD в ключе Authorization генерируется уникальное значение.

![2](img/2.png)

### Регистрация URL.
Отправляется POST запрос, генерируется короткая ссылка.

![3](img/3.png)

В блок HEAD указывается ранее полученный идентификатор с ключом Authorization.

![4](img/4.png)

Если ссылка уже была зарегистрирована, то выйдет уведомление.

![5](img/5.png)

### Переадресация.
Выполняется GET запрос без авторизации с указанием короткой ссылки. Происходит переадресация на полную ссылку.

![6](img/6.png)

При запросе счётчик обращений увеличивается на единицу.

![7](img/7.png)

### Статистика.
Статистика всех адресов и количество вызовов этого адреса. Необходима авторизация.

![8](img/8.png)