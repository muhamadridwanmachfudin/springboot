package com.basilisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasiliskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasiliskApplication.class, args);
	}

}
/*
	Entity (Entity Model) : adalah kumpulan class java yang digunakan untuk proses sinkronasi relasi
		antara SQL Table dengan Java Class, Table Column dengan Class Field.

	DTO (Data Transfer Object) : adalah kumpulan kelas java yang berisi field field yang menjadi
		tujuan hasil SELECT dari sebuah query. Nantinya DTO akan dikirimkan ke User Interfce (Client - Side)
		atau Front End Programmer, yang dimana bisa saja berupa Web, Mobile, atau desktop.
		Note : Atau... dikirim ke aplikasi server-side.

	DAO (Data Access Object) : dikenal juga sebagai Data Access atau Repository. Kegunaan DAO, adalah
		tempat di dalam spring framework di mana tempat kamu untuk menuliskan SQL Query di dalam Java.
		DAO memiliki kemampuan meng-execute query,menulis query,bisa eksekusi DML, bisa execute SP,
		dan lain sebagainya.

	Terimakasih dengan technology ORM (Object Relation Mapping), kita bisa mengquery dan memetakan
	relasi table dengan class dari sql ke java. JPA(Java Presistance API) dan Hibernate

	Service (Business Logic) : adalah kumpulan class yang setiap classnya mewakili 1 scope aplikasi
		(biasanya 1 menu) dan service class ini adalah yang menggunakan method2 dari class DAO.
		Kalau ada perhitungan atau algoritma java, atau logika business, itu ditulis didalam service.

	Factory Design Pattern : Code Java yang ada didalam satu framework atau library yang di mana memiliki
		method yang gunanya meng-construct atau meng-instantiate sebuah object untuk para programmer lain.
		Sehingga seluruh team programmer, tidak perlu menginstantiate classnya secara langsung
		(Junior Programmer gak boleh dan gak perlu new object, karena framework dari yang senior programmer
		sudah dibuatin buat juniornya)

	DI (Dependency Injection) : konsep dimana field atau parameter di dalam contructor, valuenya dimasukan
		secara otomatis oleh framework atau librarynya.

	IoC (Inversion of Control) : DI dan Factory yang sudah otomatis di handle oleh framework.
		karena itu sering sekali disebut Spring IoC karena DI dan Factory yang sudah ada di dalam
		Spring, develeoper tinggal pakai

	Controller : Sebuah kelas yang berisikan banyak method, yang setiap methodnya bisa dipanggil
		dengan menggunakan URL.(kelas unik yang dipanggil browser)

		1. MVC Controller (Model View Controller) : Web UI (User Interface)
			adalah controller yang methodnya mereturn HTML (View).Dimana HTML adalah code
			yang melukis UI untuk para user(Manusia)
		2. REST Controller (Representational State Transfer) : Web API (Application Program Interface)
			adalah controller yang methodnya mereturn JSON/XML data. Dimana data ini akan consume oleh
			Application Program yang di develop oleh programmer lain.

	===================================================================================================
	View (User Interface) : adalah satu satu nya komponen yang akan dijalankan di Internet Browser,
		atau Client-Side (Kerjaan front-end programmer), atau Web UI.
		HTML, CSS, JS

	XML (Extensible Markup Language) : Data yang akan diconsume aplikasi lain
		dalam format markup(<></>)
		Note : seluruh bahasa markup baik HTML maupun XML memiliki hierearchy yang disebut DOM
		(Document Object Model)

	JSON (JavaScript Object Notation) : technologi dari javascript yang gunanya untuk membawa data
		yang akan di consume oleh aplikasi lain, dalam bentuk object javascript.
*/