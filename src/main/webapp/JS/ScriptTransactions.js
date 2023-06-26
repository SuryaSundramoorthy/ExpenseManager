
// To get All transaction Table
fetch('http://localhost:8080/ExpenceManagerNew/TransactionsServlet')
.then(response => response.json())
.then(data => {
  jsonData=data;
  console.log(jsonData);
  
  var length = Object.keys(jsonData).length;
  console.log(length);

  if(length !=1 ){
    let table = '<table class="Transactions-Page"><thead><th>Type</th><th>Amount</th><th>Category</th><th>Date</th><th colspan="2">Actions</th></thead><tbody>';
  
    if(!length==0){  
      for (let i = 1; i <length; i++) {
        const transaction = jsonData[i];
        table += `<tr>`;
        table += `<td><img src="${ getImageUrl(transaction.type)}"></td>`;
        table += `<td> &#8377; ${transaction.amount}</td>`;
        table += `<td>${transaction.category}</td>`;
        table += `<td>${transaction.date}</td>`;
        table += `<td><button  onclick="editData(${transaction.id},this)"><img src="Images/edit.svg"></button></td>`;
        table += `<td><button onclick="deleteData(${transaction.id},this)"><img src="Images/delete.svg"></button></td>`;
        table += '</tr>';
        
        
      }
    }
    
      table += '</tbody></table>';
      function getImageUrl(name) {
        switch (name) {
          case 'income':
            return 'Images/income.png';
            case 'expense':
              return 'Images/expense.png';
            }
      }
      document.querySelector(".a").innerHTML=table;

  }else{
    $(".Table").load("NullTransactions.html");
  }
  
  });

  //Function for Delete Transaction 

  function deleteData(id,btn){
    console.log('deletinggg'); 
    console.log(id);
    fetch(`http://localhost:8080/ExpenceManagerNew/TransactionsServlet?id=${id}`, {
                    method: 'DELETE'
                })
                .then(response => {
                    // Remove the deleted record from the table
                    var row = btn.parentNode.parentNode;
                    console.log(row);                  
                    row.parentNode.removeChild(row);
                })
     
  }

  //Function for Delete Transaction 

  function editData(editId,editRow){
  fetch('http://localhost:8080/ExpenceManagerNew/TransactionsServlet')
    .then(response => response.json())
    .then(data => {
      var jsonData=data;
      console.log(jsonData);
      
      var length = Object.keys(jsonData).length;
      console.log(length);  
      if(!length==0){  
        for (let i = 1; i <length; i++) {
          const transaction = jsonData[i]; 
          if(transaction.id==editId){
              var id=transaction.id;
              var amount=transaction.amount;
              var category=transaction.category;
              var date=transaction.date;
              break;
          }     
          }
          console.log(id,amount,category,date);  
      }
  
    // Create a modal element
    var modal = document.createElement("div");
    modal.classList.add("Edit-From");
    modal.style.position = "fixed";
    modal.style.top = 0;
    modal.style.left = 0;
    modal.style.width = "100%";
    modal.style.height = "100%";
    modal.style.backgroundColor = "rgba(0,0,0,0.5)";
    modal.style.display = "flex";
    modal.style.flexDirection="column";
    modal.style.alignItems = "center";
    modal.style.justifyContent = "center";
    modal.style.gap = "20px";
    modal.style.borderRadius="20px";
    
    
    var content=document.createElement("div");

    content.classList.add("Edit-Popup");

    content.style.display = "flex";
    content.style.flexDirection="column";
    content.style.alignItems = "center";
    content.style.justifyContent = "center";
    content.style.gap = "20px";
    content.style.borderRadius="20px";
    content.style.backgroundColor="#f3f3f3";
    content.style.padding="30px";

    var header=document.createElement("div");
    header.setAttribute("id","updateHeader");
    header.innerHTML="Update Transaction"

    var closeIcon=document.createElement("button");
    closeIcon.innerHTML=`&times`;
    closeIcon.setAttribute("id","closeIcon");
    closeIcon.addEventListener("click", function() {
      modal.remove();
    })
    header.appendChild(closeIcon);
      // Creating id Text Box

  var inputId = document.createElement("input");
  inputId.setAttribute("type", "hidden");
  inputId.setAttribute("id","id");
  inputId.setAttribute("value",id );
  inputId.setAttribute("name", "id");
  
  
  // Creating Amount Text Box
  var amountDiv=document.createElement("div");


  var amountLabel=document.createElement("label");
  amountLabel.innerText="Amount: "
  amountLabel.setAttribute("for","updatedAmount" );

 
  
  var inputAmount = document.createElement("input");
  inputAmount.setAttribute("type", "text");
  inputAmount.setAttribute("id","updatedAmount");
  inputAmount.setAttribute("value",amount);
  inputAmount.setAttribute("name", "updatedAmount");
  
  amountDiv.appendChild(amountLabel);
  amountDiv.appendChild(inputAmount);
  
  // Creating Category Text Box

  var categoryDiv=document.createElement("div");

  var categoryLabel=document.createElement("label");
  categoryLabel.innerText="Category: "
  categoryLabel.setAttribute("for","updatedCategory" );

  var inputCategory = document.createElement("input");
  inputCategory.setAttribute("type", "text");
  inputCategory.setAttribute("id","updatedCategory");
  inputCategory.setAttribute("value",category);
  inputCategory.setAttribute("name", "updatedCategory");
  
  categoryDiv.appendChild(categoryLabel);
  categoryDiv.appendChild(inputCategory);
  
  // Creating Date Text Box


  var dateDiv=document.createElement("div");
  var dateLabel=document.createElement("label");
dateLabel.innerText="Date: "
  dateLabel.setAttribute("for","updatedDate" );

  
  var inputDate = document.createElement("input");
  inputDate.setAttribute("type", "text");
  inputDate.setAttribute("id", "updatedDate")
  inputDate.setAttribute("value",date );
  inputDate.setAttribute("name", "updatedDate");
  
  dateDiv.appendChild(dateLabel);
  dateDiv.appendChild(inputDate);
  

  amountLabel.style.marginRight="20px";
  categoryLabel.style.marginRight="20px";
  dateLabel.style.marginRight="50px";

  // Add a update button to the modal
  var updateButton = document.createElement("input");
  updateButton.setAttribute("type", "submit");
  updateButton.setAttribute("id","updateBtn");
  updateButton.setAttribute("value","Update")
  updateButton.style.marginTop = "10px";
  updateButton.addEventListener("click",function(){
  
    console.log('editing');
    
    var updatedCategory=document.getElementById("updatedCategory").value;
    var updatedAmount=document.getElementById("updatedAmount").value;
    var updatedDate=document.getElementById("updatedDate").value;
    console.log(updatedAmount,updatedCategory,updatedDate);
    
  
    var myHeaders = new Headers();
    myHeaders.append("Cookie", "JSESSIONID=F058B6249CC390E53F7D37669AE1C215");
    
    var requestOptions = {
      method: 'PUT',
      headers: myHeaders,
      redirect: 'follow'
    };
    
    fetch(`http://localhost:8080/ExpenceManagerNew/TransactionsServlet?TransactionId=${editId}&amount=${updatedAmount}&category=${updatedCategory}&date=${updatedDate}`, requestOptions)
      .then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));
  modal.remove();
  
  var row = editRow.parentNode.parentNode;
    
    row.cells[1].textContent = "₹ "+updatedAmount;
    row.cells[2].textContent = updatedCategory;
    row.cells[3].textContent = updatedDate;
  
                         
  })
  
  // Add a close button to the modal
  var closeButton = document.createElement("button");
  closeButton.setAttribute("id","closeBtn");
  closeButton.innerHTML = "Close";
  closeButton.style.marginTop = "10px";
  closeButton.addEventListener("click", function() {
  modal.remove();
  });
  
  // Add the content and close button to the modal
  content.appendChild(header);
  content.appendChild(amountDiv);
  content.appendChild(categoryDiv);
  content.appendChild(dateDiv);
  content.appendChild(updateButton);
  content.appendChild(closeButton);
 

  modal.appendChild(content);
    
    // Add the modal to the body
    document.body.appendChild(modal);
    
  console.log('edit button is clicked');
  
  });
  
  }

