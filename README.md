# SpringWebLab
Задание к лабораторной работе (цикл работ, 1 работа – создание слоя работы с данными)
В рамках цикла лабораторных работ создается веб-приложение для работы с каталогом автомобилей / либо других сущностей.
Описание предметной области:
## Brand
*	id – uuid либо число 
*	name – наименование бренда 
*	created – дата и время 
*	modified – дата и время 

## Model
*	id – uuid либо число
*	name – имя модели
*	category – enum (Car, Buss, Truck, Motorcycle)
*	imageUrl – URL строка 
*	startYear – число
*	endYear – число 
*	created – дата и время
*	modified – дата и время
*	brand – бренд модели

## Offer
*	id – uuid либо число
*	description – описание
*	engine – enum (GASOLINE, DIESEL, ELECTRIC, HYBRID)
*	imageUrl – URL строка 
*	mileage – число 
*	price – стоимость (decimal или число) 
*	transmission – enum (MANUAL, AUTOMATIC)
*	year – год выпуска 
*	created – дата и время
*	modified – дата и время
*	model – модель автомобиля 
*	seller – продавец

## User
*	id – uuid либо число
*	username –  имя пользователя
*	password – пароль
*	firstName –  имя
*	lastName –  фамилия
*	isActive – активный (bool)
*	role –  роль пользователя (User или Admin)
*	imageUrl – URL строка
*	created – дата и время
*	modified – дата и время



## UserRole
*	id – uuid либо число
*	role –  enum (User или Admin)

![image](https://github.com/Recwayer/SpringWebLab/assets/95271934/79a20134-2c8e-4f6f-accd-efa91991fff7)

