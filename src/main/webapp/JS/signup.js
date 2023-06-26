var passwordInput = document.getElementById("password");
var confirmPasswordInput = document.getElementById("confirmPassword");
var submitBtn = document.getElementById("submitBtn");

passwordInput.addEventListener("input", function () {
  if (passwordInput.value !== confirmPasswordInput.value) {
    submitBtn.disabled = true;
  } else {
    submitBtn.disabled = false;
  }
});

confirmPasswordInput.addEventListener("input", function () {
  if (passwordInput.value !== confirmPasswordInput.value) {
    submitBtn.disabled = true;
  } else {
    submitBtn.disabled = false;
  }
});
