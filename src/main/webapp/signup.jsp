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
    <title>Sign Up</title>
  </head>
  <body>
  <%
  
  %>
    <div class="Login-container">
      <div class="Login-logo">
        <img src="Images/Login-Logo.png" alt="Login-Logo" />
      </div>

      <div class="Login-header">
        <h1>Sign Up</h1>
        <p>Please Sign up to Continue</p>
        <h5 class="Error-Message">${errorMessage}</h5>
      </div>
      <form action="SignUpServlet" method="post" class="Login-form">
        <div class="From-item">
          <span class="Form-item-icon material-symbols-rounded"> person </span>
          <input
            type="text"
            name="Name"
            placeholder="Enter Your Name"
            required
          />
        </div>
        <div class="From-item">
          <span class="Form-item-icon material-symbols-rounded"> mail </span>
          <input
            type="text"
            name="Id"
            placeholder="Enter Your User ID"
            required
          />
        </div>
        <div class="Form-item">
          <span class="Form-item-icon material-symbols-rounded">key</span>
          <input
            type="password"
            id="password"
            name="Password"
            placeholder="Create Your password"
            required
          />
        </div>

        <div class="Form-item">
          <span class="Form-item-icon material-symbols-rounded">key</span>

          <input
            type="password"
            id="confirmPassword"
            name="CPassword"
            placeholder="Re-Enter the password"
            required
          />
        </div>
        <div class="checkbox">
          <input type="checkbox" class="checkbox" required />
          <p style="padding-left: 10px">I aggree the terms and conditions</p>
        </div>

        <input type="submit" id="submitBtn" value="SIGN UP" />
        <div class="Signup-signin">
          <p>already have an accunt?</p>
          <a href="login.jsp">Log in</a>
        </div>
      </form>
    </div>
    <script src="JS/signup.js"></script>
  </body>
</html>
