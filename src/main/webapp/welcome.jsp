<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>ExpenseManager</title>
<link rel="stylesheet" href="CSS/style.css" />
<script defer src="JS/ScriptWelcome.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
/* select:invalid { color: gray; } */
</style>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store , must-revalidate");

	Cookie cookies[] = request.getCookies();

	if (cookies != null) {
		for (Cookie c : cookies) {
			if (c.getName().equals("userId")) {
		String sessionId = c.getValue();

		session.setAttribute("sessionId", sessionId);

			}
		}
	}
	session.getAttribute("sessionId");
	if (session.getAttribute("sessionId") == null) {

		response.sendRedirect("login.jsp");
	}
	%>
	<div class="Header">
		<div class="Title">
			<h1>ExpenseManager</h1>
		</div>
			<ul class="Navigation">
				<li><a href="welcome.jsp"><button id="HomeBtn"
						style="background-color: white">Home</button></a></li>
				<li><button id="AllTransactionBtn">get</button></li>
				<li><button id="AnalyticsBtn">Analytics</button></li>
				<li><a href="LogoutServlet"><button >Logout</button></a></li>
			</ul>
			</div>
<script>

var container=document.getElementsByName("Table");
var AllTransactionBtn=document.getElementById("AllTransactionBtn");
var HomeBtn=document.getElementById("HomeBtn");
AllTransactionBtn.addEventListener('click',function(){
	$(".Table").load("AllTransactionPage.html");	
  AllTransactionBtn.style.backgroundColor="white";
  HomeBtn.style.backgroundColor="#8ce99a";
  AnalyticsBtn.style.backgroundColor="#8ce99a";
})

var AnalyticsBtn=document.getElementById("AnalyticsBtn")
AnalyticsBtn.addEventListener('click',function(){
  $(".Table").load("sample.html");
AnalyticsBtn.style.backgroundColor="white";
HomeBtn.style.backgroundColor="#8ce99a";
AllTransactionBtn.style.backgroundColor="#8ce99a";
})
	
</script>


	<div class="Table">
			<div class="Container">
			<div class="Welcome-Message" style="padding-left: 20px;">
				<h3 id="userName">Hello    </h3>
				
			</div>
			<div class="Balance">
				<div class="Total-Balance">
					<h3>Total Balance</h3>
					<h3 id="Total-Balance">&#8377; </h3>
				</div>
				<div class="Income-Expense">
					<div class="Income">
						<h3>Income</h3>
						<h3 id="Total-Income">&#8377; </h3>
					</div>
					<div class="Expense">
						<h3>Expense</h3>
						<h3 id="Total-Expense">&#8377;   </h3>
					</div>
				</div>
			</div>

			<form action="TransactionsServlet" method="post" class="Transactions">
				<div class="amount">
					<input type="number" name="amount" placeholder="Enter The Amount"
					required /> <select name="category" class="type" id="category"
					required>
					<optgroup label="Income:">
						<option value="Salary">Salary</option>
						<option value="Loan">Loan</option>
					</optgroup>
					<optgroup label="Expense:">
						<option value="Food">Food</option>
						<option value="Rent">Rent</option>
						<option value="Entertainment">Entertainment</option>
						<option value="Dress">Dress</option>
						
						<option value="Personal">Personal</option>
					</optgroup>
				</select>
			</div>
			
			<div class="AddTransactions">
				<input type="submit" name="income" class="AddTransactions"
				value="ADD TRANSACTION" />
			</div>
		</form>
		<hr />
		<h3>Recent Transactions</h3>
		<div class="Recent-Transactions">
<!-- 			
		    <table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Amount</th>
						<th>Category</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody id="recordsTable">
				</tbody>
			</table> -->
			
		</div>
	</div>
</div>
</body>
</html>
