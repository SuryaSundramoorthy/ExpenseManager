<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" href="CSS/LoginStyle.css" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@48,400,0,0"
    />
    <title>Log in</title>
  </head>
  <body>
  <%
  response.setHeader("Cache-Control", "no-cache, no-store");
 
  %>
    <div class="Login-container">
      <div class="Login-logo">
        <img src="Images/Login-Logo.png" alt="Login-Logo" />
      </div>

      <div class="Login-header">
        <h1>Sign In</h1>
        <p>Please Login to Continue</p>
        <h5 class="Error-Message">${errorMessage}</h5>
      </div>
      <form
        action="LoginServlet"
        method="post"
        class="Login-form"
        style="gap: 30px"
      >
        <div class="From-item">
          <div class="Form-item-icon material-symbols-rounded">mail</div>
          <input
            type="text"
            name="Id"
            placeholder="Enter Your User ID"
            required
          />
        </div>
        <div class="Form-item">
          <div class="Form-item-icon material-symbols-rounded">lock</div>
          <input
            type="password"
            name="Password"
            placeholder="Enter Your password"
            required
          />
        </div>

        <input type="submit" value="SIGN IN" />
        <div class="Signup-signin">
          <p>Don't have an account?</p>
          <a href="signup.jsp">Create a new account</a>
        </div>
      </form>
    </div>
  </body>
</html>
