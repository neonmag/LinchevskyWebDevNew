<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Інверсія управління у веб-застосунку</h1>
<ul class="collection">
    <li class="collection-item">
        Додаємо залежності
        <a href="https://mvnrepository.com/artifact/com.google.inject/guice/6.0.0">Guice Core</a>
    та
        <a href="https://mvnrepository.com/artifact/com.google.inject.extensions/guice-servlet/6.0.0">Guice Servlet</a>
    </li>
    <li class="collection-item">
        Створюємо пакет ioc, у якому оголошуємо клас - слухач події
        утворення контексту (IocContextListener), cпадковуємо від
        GuiceServletContextListener, імплементуємо метод getInjector()
    </li>
    <li class="collection-item">
        Реєструємо цей клас у якості слухача (обробника) події
        створення контексту у web.xml, тут же реєструємо фільтр, але
        не наш, а Guice (com.google.inject.servlet.GuiceFilter)
    </li>
    <li class="collection-item">
        Створюємо конфігураційні модулі для інжектора (у пакеті ioc):
        RouterModule (для налаштування сервлетів та фільтрів),
        ServicesModule (для налаштування служб).
        У класі IocContextListener зазначаємо об'єкти цих класів
    </li>
    <li class="collection-item">
        Переносимо конфігурацію (роутінг) фільтрів та фсервлетів
        у RouterModule: видаляємо з web.xml всі записи (окрім нових),
        а також знімаємо анотації @WebFilter та @WebServlet
    </li>
    <li class="collection-item">
        !!! Додаємо анотацію @Singleton
    </li>
</ul>