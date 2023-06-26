fetch('http://localhost:8080/ExpenceManagerNew/TransactionsServlet')
.then(response => response.json())
.then(values => {
  jsonData=values;  
  transactions=values;
  console.log(jsonData);
  var length = Object.keys(jsonData).length;
  console.log(length);
  
  var Salary=0;
  var Loan=0;
  var Food=0;
  var Rent=0;
  var Entertainment=0;
  var Dress=0;
  var Personal=0;
var pieChart;
  function calculateTotals(range){
    console.log(range);
    
      // Get current date
  const currentDate = new Date();

  // Get This Month date
 const thisMonthDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
 const lastDayOfThisMonth= new Date(currentDate.getFullYear(), currentDate.getMonth()+1, 1);
 
   // Get Last Month date
  const lastMonthDate = new Date(currentDate.getFullYear(), currentDate.getMonth()-1, 1);
  const lastDayOfPreviousMonth = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
  

  // Calculate date for last year
  const lastYearDate = new Date(currentDate.getFullYear()-1, 0, 1);
  const lastYearEnd=new Date(currentDate.getFullYear(),0,1);

  // Calculate date for This year
  const thisYearDate = new Date(currentDate.getFullYear(), 0, 1);
  const thisYearEnd=new Date(currentDate.getFullYear()+1,0,1);



var sortedTransactions= null;
if(range=='LastYear'){
   // Filter transactions for last Year and last year               2023-03-10            >=  2022-12-01   &&  2023-03-10
  sortedTransactions = transactions.filter(transaction => new Date(transaction.date) >= lastYearDate && new Date(transaction.date) < lastYearEnd);
}if(range=='LastMonth'){
  // Filter transactions for last month and last year               2023-03-10            >=  2023-02-01   &&  2023-03-10                < 2023-02-31
  sortedTransactions = transactions.filter(transaction => new Date(transaction.date) >= lastMonthDate && new Date(transaction.date) < lastDayOfPreviousMonth);
}if(range=='ThisMonth'){
  // Filter transactions for last month and last year               2023-03-10            >=  2023-03-01   &&  2023-03-10                <  2023-03-31
  sortedTransactions = transactions.filter(transaction => new Date(transaction.date) >= thisMonthDate && new Date(transaction.date) < lastDayOfThisMonth);
}if(range=='ThisYear'){
  // Filter transactions for last month and last year               2023-03-10            >=  2023-01-01   &&  2023-03-10                <  2023-12-31
  sortedTransactions = transactions.filter(transaction => new Date(transaction.date) >= thisYearDate && new Date(transaction.date) < thisYearEnd);
}


console.log(sortedTransactions);

const totalByType = sortedTransactions.reduce((result, transaction) => {
    const { category, amount } = transaction;
    result[category] = (result[category] || 0) + amount;
    return result;
  }, {});

  console.log(totalByType);

Salary=totalByType.Salary;
Loan=totalByType.Loan;
Food=totalByType.Food;
Rent=totalByType.Rent;
Entertainment=totalByType.Entertainment;
Dress=totalByType.Dress;
Personal=totalByType.Personal;

  const data = {
  labels: [
    'Salary',
    'Loan',
    'Food',
    'Rent',
    'Entertainment',
    'Dress',
    'Personal'
  ],
  datasets: [{
    label: 'Amount',
    data: [Salary, Loan, Food,Rent,Entertainment,Dress,Personal],
    backgroundColor: [
      'rgb(255, 99, 132)',
      'rgb(54, 162, 235)',
      'rgb(255, 205, 86)',
      'rgb(255,115, 6)',
      'rgb(10, 205, 86)',
      'rgb(255, 20, 86)',
      'rgb(200, 150, 806)',

    ],
    hoverOffset: 4
  }]
};
// </block:setup>

// <block:config:0>
const config = {
  type: 'pie',
  data: data,
};
// </block:config>



const diagram=document.getElementById("myChart").getContext('2d');

pieChart=new Chart(diagram, {
    type: 'pie',
    data,
    config,
  });
  }

var option=document.getElementById("select");
option.addEventListener('change',function(){
    pieChart.destroy();
    calculateTotals(option.value);
    
})
 
calculateTotals('ThisMonth');

});