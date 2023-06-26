function displayDetails() {
          // Create a modal element
          var modal = document.createElement("div");
          modal.style.position = "fixed";
          modal.style.top = 0;
          modal.style.left = 0;
          modal.style.width = "100%";
          modal.style.height = "100%";
          modal.style.backgroundColor = "rgba(0,0,0,0.5)";
          modal.style.display = "flex";
          modal.style.alignItems = "center";
          modal.style.justifyContent = "center";
          
          // Add some content to the modal
          var content = document.createElement("div");

          var form=`<form action="TransactionsServlet" method="put">
 <h3>Update Transaction</h3>
<input type="text" name="amount" placeholder="Amount"><br>
<input type="text" name="category"  placeholder="Category"><br>
<input type="text" name="type" placeholder="Type"><br>
<input type="text" name="" placeholder="Date"><br>
<input type="submit" value="Update">
</form>`;
          content.innerHTML = form;
          content.style.backgroundColor = "white";
          content.style.padding = "20px";
          
        // Add a close button to the modal
    var closeButton = document.createElement("button");
    closeButton.innerHTML = "Close";
    closeButton.style.marginTop = "10px";
    closeButton.addEventListener("click", function() {
      modal.remove();
    });
    
    // Add the content and close button to the modal
    content.appendChild(closeButton);
          
          // Add the content to the modal
          modal.appendChild(content);
          
          // Add the modal to the body
          document.body.appendChild(modal);
        }
