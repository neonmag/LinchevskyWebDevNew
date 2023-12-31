<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String charsetName = (String) request.getAttribute("charsetName");
%>
<h1>Сервлетні фільтри</h1>
<p>
    Фільтри (сервлетні фільтри) - класи для мережних задач, які
    утворюють формалізм "Middleware" - активності "проміжного рівня"
    Ця активність передує роботі сервлетів і може виконуватись
    до маршрутизації, тобто для всіх запитів (на всі адреси, довільним
    методом: GET, POST, ...). Більш того, додаткові фільтри можуть
    "вбудовуватись" у вже наявний ланцюг викликів (вставлятись у
    середину, у "проміжний рівень")
</p>
<p>
    На прикладі CharsetFilter реалізуємо задачу встановлення кодування
    для request та response. Ця процедура може буди здійснена ДО
    моменту першого читання/запису, оскільки підтримує мультибайт
    кодування.
</p>
<p>
    charsetName =<%=charsetName%>
</p>