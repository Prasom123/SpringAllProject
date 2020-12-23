<html>
<head>
<title>OTP Generation</title>
</head>
<body>
  <div> Dear <strong> ${name} </strong> </div>
  <ul>
  <#list products as product>
    <li>
      <a href=${product.url}>   ${product.pname} </a> 
    </li>
  </#list>
  </ul>
</body>
</html>