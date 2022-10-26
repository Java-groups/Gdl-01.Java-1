<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>You have requested to reset your password</title>
	<h3>${date}</h3>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0px auto">
	
	<div style="margin: 0 auto; ">
        <h1>You have requested to reset your password</h1>
        <h3>${date}</h3>
    
        <div>
            <strong>Your secret token is : <span style="color: red;">${token}</span></strong>
        </div>

        <div>
            Reset your email password please go to the link and restore it!!
            <a href="${urlForgot}">Restore</a>
        </div>
    </div>

</body>
</html>