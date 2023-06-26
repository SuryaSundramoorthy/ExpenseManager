var recentElement = document.querySelector(".Recent-Transactions");
var totalIncomeElement=document.getElementById("Total-Income");
var totalExpenseElement=document.getElementById("Total-Expense");
var totalBalanceElement=document.getElementById("Total-Balance");
var userNameElement=document.getElementById("userName");

const request = new XMLHttpRequest();
request.open("GET", "http://localhost:8080/ExpenceManagerNew/TransactionsServlet");
request.send();


request.addEventListener("load", function () {
  var jsonData = JSON.parse(this.responseText);
  
  
  var length = Object.keys(jsonData).length;
  console.log(length);
  
  let table = '<table><thead><th></th><th>Amount</th><th>Category</th><th>Date</th></thead><tbody>';
  
  if(!length==0){  
    for (let i = 1; i <length; i++) {
      const transaction = jsonData[i];
      table += '<tr>';
      table += '<td><img src="' + getImageUrl(transaction.type) + '"></td>';
      table += '<td>' + '&#8377;  '+transaction.amount + '</td>';
      table += '<td>' + transaction.category + '</td>';
      table += '<td>' + transaction.date + '</td>';
      table += '</tr>';
      if(i==5){
        break;
      }
    }}
    
    table += '</tbody></table>';
    
    function getImageUrl(name) {
      switch (name) {
        case 'income':
          return 'Images/income.png';
          case 'expense':
            return 'Images/expense.png';
          }
        }
        
	var totalBalance=0;
	var totalIncome=0;
	var totalExpense=0;
	
  if(!length==0){
    for(let j=1;j<length;j++){
      const Transaction=jsonData[j];
      
      if((Transaction.type)=="income"){
        totalIncome +=Transaction.amount;
      }else{
        totalExpense +=Transaction.amount;
      }}}
      
      totalBalance=totalIncome-totalExpense;
      
       var userName=jsonData[0].userName+"  ,";
       
       
       
  recentElement.insertAdjacentHTML('beforeend', table);
  totalBalanceElement.insertAdjacentHTML('beforeend',totalBalance);
  totalIncomeElement.insertAdjacentHTML('beforeend',totalIncome);
  totalExpenseElement.insertAdjacentHTML('beforeend',totalExpense);
  userNameElement.insertAdjacentHTML('beforeend',userName);
});



 
