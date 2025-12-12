<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<div class="container">
    <h1>Welcome to the login page!</h1>
    <pre>${errorMessage}</pre>
    <form method="post">
        Name: <input type="text" name="name" required="required">
        <br/>
        Password: <input type="password" name="password" required="required">
        <br/>
        <input type="submit">
    </form>
</div>
<%@include file="common/footer.jspf" %>