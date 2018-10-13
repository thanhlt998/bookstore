$(function() {

  //Validate form ------------------------------------------------------------
    var registerForm = $("#registerForm");

    validatePasswordRegisterForm(registerForm);

    registerForm.submit(validateSubmitRegisterForm);


    //Add cart ------------------------------------------------------------
    $("#add-button").click(increaseQuantity);
    $("#minus-button").click(decreaseQuantity);


    // Replace img ---------------------------------------------------------
    $(".small-img").click(replaceImg);


    // Add cart -----------------------------------------------------------

    $("#shopCart").hover(function(){
      $("#cartDetail").stop().slideDown();
    }, function(){
      $("#cartDetail").stop().slideUp();
    })


});

// --------------------------------------------------------------------------------------------
// Validate Form 

function validateSubmitRegisterForm(event) {
  var registerForm = $("#registerForm");
  var passwordField = registerForm.find($("#password"));
  var confirmPasswordField = registerForm.find($("#confirmPassword"));

  validatePasswordField(passwordField, event);
  validateConfirmPasswordField(passwordField, confirmPasswordField, event);
}

function validatePasswordRegisterForm(registerForm) {
  var passwordField = registerForm.find($("#password"));
  var confirmPasswordField = registerForm.find($("#confirmPassword"));

  passwordField.blur(function(event) {
    validatePasswordField(passwordField, event);
  });

  confirmPasswordField.blur(function(event) {
    validateConfirmPasswordField(passwordField, confirmPasswordField, event);
  });
}

function validatePasswordField(passwordField, event) {
  var password = passwordField.val();
  if (!password.length >= 8 || !/.*[0-9].*/.test(password)) {
    $("#password-feedback").text(
      "Must not contain any whitespace characters and contain at least 8 characters."
    );
    event.preventDefault();
  } else {
    $("#password-feedback").text("");
  }
}

function validateConfirmPasswordField(
  passwordField,
  confirmPasswordField,
  event
) {
  var password = passwordField.val();
  var confirmPassword = confirmPasswordField.val();
  if (password !== confirmPassword) {
    $("#confirmPasswordAppend").html("<i class='far fa-times-circle text-danger'></i>")
    event.preventDefault();
  } else {
    $("#confirmPasswordAppend").html("<i class='far fa-check-circle text-success'></i>");
  }
}

function validateUsernameField(event) {
    event.preventDefault();
    var username = $(this).val();
    $.ajax({
    	type: "GET",
    	contentType: "application/json",
    	url: event.data.url,
    	data: {
    		username: username
    	},
    	dataType: "json",
    	timeout: 10000,
    	success: function(data) {
        console.log(data);
        if (data === true) {
            $("#usernameAppend").html("<i class='far fa-check-circle text-success'></i>");
            event.preventDefault();
        } else {
            $("#usernameAppend").html("<i class='far fa-times-circle text-danger'></i>");
        }
    },
    error: function(e) {
      console.log(e);
    }
  });
}

// // Register
// function register(event){
//     $.ajax({
//         type: "GET",
//         contentType: "application/json",
//         url: event.data.url,
//         data: {
//             name: $("#name").val(),
//             email: $("#email").val(),
//             username: $("#username").val(),
//             password: $("#password").val(),
//             birthDate: $("#birthDate").val(),
//             gender: $("#gender").val(),
//             address: $("#address").val(),
//             phone: $("#phone").val()
//         },
//         dataType: "json",
//         timeout: 10000,
//         success: function(data){
//             if(data === true){
//                 $("#dismissRegisterButton").click();
//                 alert("Register successfully! Please login.");
//             }
//             else {
//                 alert("Failed to register.");
//             }
//             event.preventDefault();
//         },
//         error: function(error){
//             console.log(error);
//         }
//     })
// }

// ------------------------------------------------------------------------------------------------------------------------

// add cart
function increaseQuantity(){
  var quantityAdd = $("#quantity-add");
  var currentValue = parseInt(quantityAdd.val());
  quantityAdd.val(currentValue + 1);
  $("#add-cart-button").attr("quantity", quantityAdd.val());
}

function decreaseQuantity(){
  var quantityAdd = $("#quantity-add");
  var currentValue = parseInt(quantityAdd.val());
  if(currentValue > 1){
    quantityAdd.val(currentValue - 1);
  }
  $("#add-cart-button").attr("quantity", quantityAdd.val());
}

function addCart(event){
  var bookId = $(this).attr("bookid");
  var quantity = $(this).attr("quantity");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: event.data.url,
    data: {
      bookId: bookId,
      quantity: quantity
    },
    dataType: "json",
    timeout: 10000,
    success: function(data){
      console.log(data);
      if(data === false){
        alert("Cannot add more this book.");
      }
      else {
        console.log(data.cart);
        var cart = data.cart;
        $("#badge").text(data.totalQuantity);

        var tableBody = $("#cartDetail").find("tbody");
        tableBody.empty();
        for(var i = 0; i < cart.length; i++){
          $("<tr>").append($("<td>").text(cart[i].book.bookName)).append($("<td>").text(cart[i].quantity)).appendTo(tableBody);
        }
        $("<tr>").append($("<td>").text("Total Price")).append($("<td>").text(data.totalPrice)).appendTo(tableBody);
      }
    },
    error: function(error){
      console.log(error);
    }
  });
}


// small-picture --------------------------------------------------------------------------------------------
function replaceImg(event){
  var mainImg = $("#main-img");
  mainImg.attr("src", $(this).attr("src"));
}

