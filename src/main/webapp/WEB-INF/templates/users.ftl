<html>
<head>
    <title>Users</title>
</head>
<body>
    <ul>
    <#list users as user>
    <li>${user.name} ${user.surName} ${user.email}</li>
    </#list>
    </ul>
</body>
</html>
